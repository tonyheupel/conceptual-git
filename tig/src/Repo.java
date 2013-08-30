import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Repo {
	private Branch currentBranch;
	private Map<String, Branch> branches;
	private Object staged;

	private Repo() {
		this.branches = new HashMap<String, Branch>();
		Branch master = new Branch("master", null);
		this.branches.put(master.getName(), master);
		this.currentBranch = master;
	}


	@Override
	public String toString() {
		return this.currentBranch.toString();
	}

	public static Repo init() {
		return new Repo();
	}

	public String log() {
		return log(Integer.MAX_VALUE);
	}

	public String log(int depth) {
		return Repo.log(this.getHead(), depth);
	}

	public static String log(Commit start, int depth) {
		int currentDepth = 0;
		Commit currentCommit = start;
		StringBuilder log = new StringBuilder();

		while (currentDepth < depth && currentCommit != null) {
			log.append(String.format("%s\n", currentCommit.getValue()));

			currentDepth++;
			currentCommit = Repo.getParent(currentCommit);
			// TODO: Figure out what to do with multiple parents			
		}

		return log.toString();
	}

	private static Commit getParent(Commit commit) {
		Iterable<Commit> parents = commit.getParents();

		Iterator<Commit> parentsIterator = parents.iterator();

		if (parentsIterator.hasNext()) {
			return parentsIterator.next();
		} else {
			return null;
		}
	}


	public void commit() {
		this.currentBranch.commit(new Commit(this.staged));
	}

	public void add(Object toStage) {
		this.staged = toStage;
	}

	public Branch getCurrentBranch() {
		return this.currentBranch;
	}

	public Commit getHead() {
		return this.getCurrentBranch().getHead();
	}

	public Map<String, Branch> getBranches() {
		return this.branches;
	}

	public Branch getBranch(String name) {
		return this.branches.get(name);
	}
}
