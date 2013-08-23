import java.lang.Iterable;


public class MergeCommit extends Commit {

	public MergeCommit(Object value, Commit... parents) {
		this((Integer)value, parents);
	}

	public MergeCommit(Integer value, Commit... parents) {
		super(value);

		if (parents == null || parents.length < 2) {
			throw new IllegalArgumentException("A MergeCommit must have multiple parents");
		}

		this.addParents(parents);
	}
}
