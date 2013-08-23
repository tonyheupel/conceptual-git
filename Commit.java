public class Commit {
	public Integer value;
	public Commit next;

	public Commit(Object value) {
		this((Integer)value);
	}

	public Commit(Integer value) {
		this.value = value;
		this.next = null;
	}
}
