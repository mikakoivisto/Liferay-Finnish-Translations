import java.util.*;
import java.io.*;

public class Ascii2Native {
	
  public static void main(String[] argv) throws Exception {
	Properties props = new Properties();
	FileInputStream fin = new java.io.FileInputStream(new File(argv[0]));
	props.load(fin);
	BufferedReader br = new BufferedReader(new FileReader(new File(argv[0])));
	FileOutputStream fos = new FileOutputStream(new File(argv[0] + ".native"));
	OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
	
	//Enumeration enu = props.keys();
	//List<String> keys = new ArrayList<String>();
//	while (enu.hasMoreElements()) {
//		String key = (String)enu.nextElement();
//		keys.add(key);
//	}
//	Collections.sort(keys);
//	Iterator<String> iter = keys.iterator();
//	osw.write("#\r\n" 
//			+ "# Translations by Mika Koivisto (mika@javaguru.fi)\r\n" 
//			+ "#\r\n");
//	while (iter.hasNext()) {
//		String key = iter.next();
//		osw.write(key + "=" + props.getProperty(key) + "\r\n");
//	}

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

			osw.write(key + "=" + translatedText + "\r\n");
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