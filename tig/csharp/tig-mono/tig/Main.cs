using System;
using System.IO;
using System.Collections.Generic;
using tiglib;
using tiglib.Commands;

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

				case "demo":
					DemoHelper();
					break;
				default:
					Console.WriteLine("'{0}' is not a known command", command);
					break;
			}
		}

		static void DemoHelper ()
		{
			var repo = Repo.Init ();
			AddAndCommit (repo, 1);
			AddAndCommit (repo, 2);
			AddAndCommit (repo, 3);
			Console.WriteLine ("Repo => {0}", repo);
		}

		private static void AddAndCommit(Repo repo, object value)
		{
			CommitValue commitValue = CreateCommitValue(value);
			repo.Add(commitValue);
			repo.Commit();
		}

		private static CommitValue CreateCommitValue(object value)
		{
			return new CommitValue (value);
		}

		private static void HashObjectHelper(string[] args)
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