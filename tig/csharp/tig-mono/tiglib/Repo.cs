using System;
using System.Collections.Generic;
using System.Text;

namespace tiglib
{
	public class Repo
	{
		private Branch currentBranch;
		private Dictionary<String, Branch> branches;
		private Object staged;

		private Repo()
		{
			this.branches = new Dictionary<String, Branch>();
			Branch master = new Branch("master", null);
			this.branches.Add(master.Name, master);
			this.currentBranch = master;
		}


		public override string ToString()
		{
			return this.currentBranch.ToString();
		}

		public static Repo Init()
		{
			return new Repo();
		}

		public string Log()
		{
			return Log(int.MaxValue);
		}

		public string Log(int depth)
		{
			return Repo.Log(this.Head, depth);
		}

		public static string Log(Commit start, int depth)
		{
			int currentDepth = 0;
			Commit currentCommit = start;
			StringBuilder log = new StringBuilder();

			while (currentDepth < depth && currentCommit != null)
			{
				log.Append(String.Format("{0}\n", currentCommit.Value));

				currentDepth++;
				currentCommit = Repo.Parent(currentCommit);
				// TODO: Figure out what to do with multiple parents			
			}

			return log.ToString();
		}

		private static Commit Parent(Commit commit) 
		{
			IEnumerable<Commit> parents = commit.Parents;

			IEnumerator<Commit> parentsEnumerator = parents.GetEnumerator();

			if (parentsEnumerator.MoveNext())
			{
				return parentsEnumerator.Current;
			} 
			else 
			{
				return null;
			}
		}


		public void Commit() 
		{
			this.currentBranch.Commit(new Commit(this.staged));
		}

		public void Add(Object toStage)
		{
			this.staged = toStage;
		}

		public Branch CurrentBranch
		{
			get { return this.currentBranch; }
		}

		public Commit Head
		{
			get { return this.CurrentBranch.Head; }
		}

		public IDictionary<String, Branch> Branches
		{
			get { return this.branches; }
		}

		public Branch GetBranch(string name)
		{
			return this.branches[name];
		}
	}
}

