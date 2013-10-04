using System;
using System.IO;
using System.Security.Cryptography;
using System.Text;

namespace tiglib.Commands
{
	public class HashObject
	{
		public static string Hash(Stream inputStream)
		{
			return GitHash("blob", inputStream);
		}

		private static string GitHash (string type, Stream inputStream)
		{
			Stream typedInputStream = GenerateGitInputStream(type, inputStream);
			using (SHA1Managed sha1 = new SHA1Managed())
			{
				byte[] hash = sha1.ComputeHash(typedInputStream);
				StringBuilder hexStringHash = new StringBuilder(2 * hash.Length);
				foreach (byte b in hash)
				{
					hexStringHash.AppendFormat("{0:X2}", b);
				}
				
				return hexStringHash.ToString().ToLower();
			}
		}

		private static Stream GenerateGitInputStream (string type, Stream inputStream)
		{
			string streamContents = Utilities.CreateStringFromStream (inputStream);
			string gitInputString = String.Format("{0} {1}\0{2}", type, streamContents.Length, streamContents);

			return Utilities.CreateStreamFromString(gitInputString);
		}
	}
}

