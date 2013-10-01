public class CommitValue {
	private Object value;

	public CommitValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
