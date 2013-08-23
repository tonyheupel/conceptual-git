public class App {
	public static void main(String[] args) {
		appendLinkedList();
		insertLinkedList();
		createRepo();
	}
	

	private static void appendLinkedList() {
		LinkedList<Integer> list = new LinkedList<Integer>(new Node<Integer>(1));
		list.append(new Node<Integer>(2));
		list.append(new Node<Integer>(3));
		System.out.println(list);
	}

	private static void insertLinkedList() {
		LinkedList<Integer> list = new LinkedList<Integer>(new Node<Integer>(1));
		list.insert(new Node<Integer>(2));
		list.insert(new Node<Integer>(3));

		System.out.println(list);
	}

	private static void createRepo() {
		Repo r = Repo.init();
		r.add(1);
		r.commit();

		r.add(2);
		r.commit();

		r.add(3);
		r.commit();

		System.out.println(r);
	}
}
