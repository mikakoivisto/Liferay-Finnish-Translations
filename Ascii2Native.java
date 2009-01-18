import java.util.*;
import java.io.*;

public class Ascii2Native {
	
  /**
   * 
   */
  public static void main(String[] argv) throws Exception {
        if (argv.length < 2) {
		System.out.println("Usage: java Ascii2Native <basefile> <translation file>");
		System.exit(1);
	}

	Properties props = new Properties();
	FileInputStream fin = new java.io.FileInputStream(new File(argv[1]));
	props.load(fin);
	BufferedReader br = new BufferedReader(new FileReader(new File(argv[0])));
	FileOutputStream fos = new FileOutputStream(new File(argv[1] + ".native"));
	OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

	String line = null;

	while ((line = br.readLine()) != null) {
		line = line.trim();
		if (line.startsWith("#!")) {
			continue;
		}

		int pos = line.indexOf("=");

		if (pos != -1) {
			String key = line.substring(0, pos);
			String value = line.substring(pos + 1, line.length());

			String translatedText = props.getProperty(key);

			osw.write(key + "=" + translatedText.trim() + "\r\n");
			osw.flush();

		}
		else {
			osw.write(line + "\r\n");

			osw.flush();
		}
	}	
	
	br.close();
	osw.close();
	fos.close();
  }
}
