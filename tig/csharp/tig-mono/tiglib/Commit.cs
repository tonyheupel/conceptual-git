using System;
using System.Collections.Generic;

namespace tiglib
{
	public class Commit
	{
		private CommitValue value;
		private List<Commit> parents = new List<Commit>();


		public Commit(Object value) : this((CommitValue)value)
		{
		}

		public Commit(CommitValue value) : this(value, null)
		{
		}

		public Commit(CommitValue value, Commit parent) 
		{
			this.value = value;

			if (parent != null)
			{
				this.AddParent(parent);
			}

		}

		public IEnumerable<Commit> Parents
		{
			get { return this.parents; }
		}

		public CommitValue Value
		{
			get { return this.value; }
		}

		public void AddParent(Commit parent)
		{
			if (parent == null) 
			{
				throw new ArgumentNullException("parent must be a non-null Commit");
			}

			this.parents.Add(parent);
		}

		protected void AddParents(IEnumerable<Commit> parents)
		{
			if (parents == null)
			{
				throw new ArgumentNullException("parents must be a non-null argument set");
			}

			this.parents.AddRange(parents);
		}

	}
}

