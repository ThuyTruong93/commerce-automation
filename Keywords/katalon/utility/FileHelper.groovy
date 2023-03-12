package katalon.utility

import java.nio.charset.StandardCharsets

import org.apache.commons.io.FileUtils


public class FileHelper {
	String path;

	public FileHelper(String path) {
		this.path = path;
	}

	private static String readFile(String path) {
		try {
			return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
