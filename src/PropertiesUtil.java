import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class PropertiesUtil {
	private final static String keyOptScanFld = "opt.scan.folders";
	private final static String keyOptScanArch = "opt.scan.archives";
	private final static String keyScanIPR = "opt.scan.archives";
	
	private static File configFile = new File("properties.xml");
	private static Properties configProps = null;
	
	private PropertiesUtil() { }
	
	public static Properties loadProperties() throws IOException {
		if(configProps == null) {
			Properties defaultProps = new Properties();
			
			defaultProps.setProperty(keyOptScanFld, "true");
			defaultProps.setProperty(keyOptScanArch, "true");
			defaultProps.setProperty(keyScanIPR, "true");
			
			configProps = new Properties(defaultProps);
			
			if(configFile.exists()) {
				InputStream inStream = new FileInputStream(configFile);
				configProps.loadFromXML(inStream);
				inStream.close();
			}
		}
		
		return configProps;
	}
	
	public static void setBooleanPropertyByKey(String propKey, Boolean propVal) {
		if(propVal)	{ configProps.setProperty(propKey, "true"); 	}
		else		{ configProps.setProperty(propKey, "false");	}
	}
	
	public static void setScanOptProperties(boolean scanFlds, boolean scanArch, boolean scanIPR) throws IOException {
		//configProps.setProperty(keyOptScanFld, value)
		setBooleanPropertyByKey(keyOptScanFld, scanFlds);
		setBooleanPropertyByKey(keyOptScanArch, scanArch);
		setBooleanPropertyByKey(keyScanIPR, scanIPR);
		
//		OutputStream outStream = new FileOutputStream(configFile);
//		configProps.store(outStream, "");
	}
	
//	public static void saveProperties(boolean scanFlds, boolean scanArch, boolean scanIPR) throws IOException {
//		
//	}
}
