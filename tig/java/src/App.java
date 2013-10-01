public class App {
	public static void main(String[] args) {
		Repo repo = createRepo();

		System.out.println();
		showRepo(repo);
		System.out.println();
		logRepo(repo);
	}
	
	private static Repo createRepo() {
		Repo repo = init();

		stage(repo, 1);
		commit(repo);

		stage(repo, 2);
		commit(repo);

		stage(repo, 3);
		commit(repo);

		return repo;
	}

	private static Repo init() {
		System.out.println("$ tig init");
		return Repo.init();
	}

	private static void stage(Repo repo, Object workingDirectory) {
		System.out.println(String.format("$ tig add %s", workingDirectory));
		repo.add(new CommitValue(workingDirectory));
	}

	private static void commit(Repo repo) {
		System.out.println("$ tig commit");
		repo.commit();
	}

	private static void showRepo(Repo repo) {
		System.out.println(String.format("Current repo state: %s", repo));
	}

	private static void logRepo(Repo repo) {
		System.out.println("$ tig log");

		System.out.println(repo.log());
	}
}
