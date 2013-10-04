using System;
using System.Collections.Generic;

namespace tiglib
{
	public class Branch
	{
		private String name;
		private Commit head;

		public Branch(String name, Commit initialHead) {
			this.head = initialHead;
			this.name = name;
		}

		public override string ToString()
		{
			string output = this.Name;

			if (this.Head == null) 
			{
				return output;
			}

			return String.Format("{0}=>{1}", output, StringifyCommit(head));
		}

		public string Name
		{ 
			get { return this.name; }
		}

		public Commit Head
		{
			get { return this.head; }
		}

		private string StringifyCommit(Commit node)
		{
			if (node == null) 
			{
				return "";
			}

			IEnumerator<Commit> parentEnumerator = node.Parents.GetEnumerator ();
			bool hasParents = parentEnumerator.MoveNext();
	
			return String.Format("{0}{1}{2}", node.Value,
			                     hasParents ? "-->" : "",
			                     StringifyCommit(hasParents ? parentEnumerator.Current : null));
		}


		public void Commit(Commit node)
		{
			if (this.head != null)
			{
				node.AddParent(this.head);
			}

			this.head = node;
		}

	}
}

