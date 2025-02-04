package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties=new Properties();



    static {
        try (InputStream input =ConfigLoader.class.getClassLoader().getResourceAsStream("config/config.properties"))
        {if (input == null){throw new RuntimeException("your config file is empty or dose not exist");}
            properties.load(input);}catch (IOException e){throw new RuntimeException("cold not load config file");}
    }
   public static String getProperty(String key){
        return properties.getProperty(key);
    }

    private static boolean checkIfPropertyIsEmptyOrNull(String key){
        return (getProperty(key)==null ||getProperty(key).equals(" ") );
    }
    public static int getIntProperty(String key){
        return(checkIfPropertyIsEmptyOrNull(key))?5000: Integer.parseInt(getProperty(key));
    }
    public static Properties getProperties(){
        return properties;
    }
    public static boolean getBooleanProperties(String key){
        return (checkIfPropertyIsEmptyOrNull(key))?false:Boolean.parseBoolean(getProperty(key));
    }




}
