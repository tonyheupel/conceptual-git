public class Branch {
	public String name;
	public Commit head;

	public Branch(String name, Commit initialHead) {
		this.head = initialHead;
		this.name = name;
	}


	@Override
	public String toString() {
		String output  = "HEAD";
		
		if (head == null) {
			return output;
		}

		return String.format("HEAD=>%s", stringifyCommit(head));
	}

	private String stringifyCommit(Commit node) {
		if (node == null) {
			return "";
		}

		return String.format("%s%s%s", node.value.toString(), node.next == null ? "" : "-->", stringifyCommit(node.next));
	}


	public void commit(Commit node) {
		node.next = head;
		head = node;
	}
}
