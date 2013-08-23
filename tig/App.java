public class App {
	public static void main(String[] args) {
		createRepo();
	}
	
	private static void createRepo() {
		Repo r = Repo.init();
		r.add(new CommitValue(1));
		r.commit();

		r.add(new CommitValue(2));
		r.commit();

		r.add(new CommitValue(3));
		r.commit();

		System.out.println(r);
	}
}
