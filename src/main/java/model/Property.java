package model;

import java.io.*;
import java.util.Properties;

public class Property {
    private final Properties configProp = new Properties();

    private void PropertiesCache()
    {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("userID.properties");
        System.out.println("Reading all properties from the file");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setProperty(String userID, String value){
        configProp.setProperty(userID, value);
    }

    public void flush() throws FileNotFoundException, IOException {
        try (final OutputStream outputstream
                     = new FileOutputStream("userID.properties");) {
            configProp.store(outputstream,"File Updated");
            outputstream.close();
        }
    }
}
