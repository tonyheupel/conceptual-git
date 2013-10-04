using System;
using System.IO;
using System.Text;
namespace tiglib
{
	public class Utilities
	{
		public static Stream CreateStreamFromString(String input)
		{
			MemoryStream stream = new MemoryStream();
			StreamWriter writer = new StreamWriter(stream);
			writer.Write(input);
			writer.Flush();
			stream.Position = 0;
			return stream;
		}

		public static string CreateStringFromStream(Stream input)
		{
			StreamReader reader = new StreamReader (input);
			return reader.ReadToEnd ();
		}
	}
}

