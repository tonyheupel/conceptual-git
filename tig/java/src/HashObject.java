import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HashObject {
	public static void main(String[] args) {
		String message = getInput(args);
		String shaString = sha1(message);
		System.out.println(shaString);
	}


	private static String getInput(String[] commandLineArgs) {
		Scanner sc = initializeScanner(commandLineArgs);

		StringBuffer buffer = new StringBuffer();
		while(sc.hasNextLine()) {
			buffer.append(sc.nextLine());
		}

		return buffer.toString();
	}

	private static Scanner initializeScanner(String[] commandLineArgs) {
		if (commandLineArgs.length > 0) {
			String filename = join(commandLineArgs, " ");

			try {
				File file = new File(filename);
				return new Scanner(file);
			} catch (FileNotFoundException ex) {
				System.err.println(String.format("File %s not found!", filename));
				System.exit(1);
			}
		}

		return new Scanner(System.in);
	}

	private static String join(String[] strings, String separator) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			if (i > 0) buffer.append(separator);

			buffer.append(strings[i]);
		}

		return buffer.toString();
	}

	private static String sha1(String input) {
		return gitSHA1("blob", input);
		//return plainSHA1(input);
	}

	private static String plainSHA1(String input) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException ex) {
			System.err.println("Could not create a SHA-1 MessageDigest");
			System.exit(1);
		}

		messageDigest.update(input.getBytes());

		byte byteData[] = messageDigest.digest();

		return convertBytesToHexString(byteData);
	}

	private static String gitSHA1(String type, String input) {
		long size = input.length();

		String gitValueToHash =  String.format("%s %d\0%s", type, size, input);

		return plainSHA1(gitValueToHash);
	}


	private static String convertBytesToHexString(byte[] data) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			buffer.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
		}

		return buffer.toString();
	}


}
