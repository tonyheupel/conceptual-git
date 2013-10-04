using System;
using tiglib;
using System.IO;
using System.Collections.Generic;

namespace tig
{
	class MainClass
	{
		public static void Main(string[] args)
		{
			var command = args.Length > 0 ? args[0] : String.Empty;
			switch(command)
			{
				case "hash-object":
					HashObjectHelper (args);
					break;
				default:
					Console.WriteLine("'{0}' is not a known command", command);
					break;
			}
		}

		public static void HashObjectHelper(string[] args)
		{
			Console.WriteLine (HashObject.Hash (GetHashInputStream(args)));
		}

		private static Stream GetHashInputStream(string[] args)
		{
			if (args.Length > 1)
			{
				return GetFilesContentStream(GetFilenamesFromCommandLine(args));
			}

			return GetStandardInStream ();
		}

		private static IEnumerable<string> GetFilenamesFromCommandLine(IEnumerable<string> args)
		{
			List<string> filenames = new List<string>(args);
			filenames.RemoveAt (0);
			return filenames;
		}

		private static Stream GetFilesContentStream(IEnumerable<string> filenames)
		{
			var fileNameEnumerator = filenames.GetEnumerator ();
			fileNameEnumerator.MoveNext ();
			return GetFileContentStream (fileNameEnumerator.Current);
		}

		private static Stream GetFileContentStream(string filename)
		{
			FileStream fileStream = new FileStream (filename, FileMode.Open);
			return fileStream;
		}

		private static Stream GetStandardInStream ()
		{
			return Console.OpenStandardInput ();
		}
	}
}