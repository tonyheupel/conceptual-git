public class Branch {
	private String name;
	private Commit head;

	public Branch(String name, Commit initialHead) {
		this.head = initialHead;
		this.name = name;
	}


	@Override
	public String toString() {
		String output  = this.getName();

		if (this.head == null) {
			return output;
		}

		return String.format("%s=>%s", output, stringifyCommit(head));
	}

	public String getName() { 
		return this.name;
	}

	public Commit getHead() {
		return this.head;
	}

	private String stringifyCommit(Commit node) {
		if (node == null) {
			return "";
		}

		return String.format("%s%s%s", node.getValue().toString(),
			node.getParents().iterator().hasNext() ? "-->" : "",
		   	stringifyCommit(node.getParents().iterator().hasNext() ? node.getParents().iterator().next() : null));
	}


	public void commit(Commit node) {
		if (this.head != null) {
			node.addParent(this.head);
		}

		this.head = node;
	}
}
