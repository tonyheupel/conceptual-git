public class LinkedList<T> {
	public Node<T> head;

	public LinkedList(Node<T> head) {
		this.head = head;
	}


	@Override
	public String toString() {
		String output  = "HEAD";
		
		if (head == null) {
			return output;
		}

		return String.format("HEAD=>%s", stringifyNode(head));
	}

	private String stringifyNode(Node<T> node) {
		if (node == null) {
			return "";
		}

		return String.format("%s%s%s", node.value.toString(), node.next == null ? "" : "-->", stringifyNode(node.next));

	}


	public void append(Node<T> node) {
		Node<T> current = head;

		while (current.next != null) {
			current = current.next;
		}

		current.next = node;
	}

	public void insert(Node<T> node) {
		node.next = head;
		head = node;
	}
}
