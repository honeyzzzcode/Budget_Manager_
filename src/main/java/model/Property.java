package model;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Properties;

public class Property {

Properties prop = new Properties();
    public void PropertiesLoad() throws FileNotFoundException {
        try {
            InputStream in = new FileInputStream("userID.properties");
            prop.load(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void setProperty( String userID, String value){
        prop.setProperty(userID, value);
    }

    public void saveProp() throws FileNotFoundException, IOException {
        try (final OutputStream outputstream
                     = new FileOutputStream("userID.properties");) {
            prop.store(outputstream,"File Updated");
            outputstream.close();
        }
    }
}
