import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Commit {
	private CommitValue value;
	private List<Commit> parents = new ArrayList<Commit>();


	public Commit(Object value) {
		this((CommitValue)value);
	}

	public Commit(CommitValue value) {
		this(value, null);
	}

	public Commit(CommitValue value, Commit parent) {
		this.value = value;

		if (parent != null) {
			addParent(parent);
		}

	}

	public Iterable<Commit> getParents() {
		return (Iterable<Commit>)this.parents;
	}

	public CommitValue getValue() {
		return this.value;
	}

	public void addParent(Commit parent) {
		if (parent == null) {
			throw new IllegalArgumentException("parent must be a non-null Commit");
		}

		this.parents.add(parent);
	}

	protected void addParents(Commit... parents) {
		if (parents == null) {
			throw new IllegalArgumentException("parents must be a non-null argument set");
		}

		this.parents.addAll(Arrays.asList(parents));
	}
}
