package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;
	
	
	public Properties initialize(){
		
		properties=new Properties();
		
		try {
			FileInputStream fileInputStream=new FileInputStream("./src/test/resources/config.properties");
			properties.load(fileInputStream);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
}
