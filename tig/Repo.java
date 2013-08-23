import java.util.Map;
import java.util.HashMap;

public class Repo {
	public Branch currentBranch;
	public Map<String, Branch> branches;
	public Object staged;

	private Repo() {
		this.branches = new HashMap<String, Branch>();
		Branch master = new Branch("master", null);
		this.branches.put(master.name, master);
		this.currentBranch = master;
	}


	@Override
	public String toString() {
		return this.currentBranch.toString();
	}

	public static Repo init() {
		return new Repo();
	}

	public void commit() {
		this.currentBranch.commit(new Commit(this.staged));
	}

	public void add(Object toStage) {
		this.staged = toStage;
	}
}
