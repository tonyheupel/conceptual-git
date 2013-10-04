using System;

namespace tiglib
{
	public class CommitValue
	{
		private Object value;

		public CommitValue(Object value) {
			this.value = value;
		}

		public override string ToString ()
		{
			return this.value.ToString();
		}
	}
}

