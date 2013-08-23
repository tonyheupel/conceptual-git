public class App {
	public static void main(String[] args) {
		createRepo();
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
