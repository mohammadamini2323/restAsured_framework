package utils;

import config.ConfigLoader;

public class ConfigUtils {
    public static String getEnvironment(){
        return ConfigLoader.getProperty("Environment").trim().toUpperCase();
    }
    public static String getEmailDomain(){
        return ConfigLoader.getProperty("email.domain");

    }
    public static int getTokEnExpirationInMinutes(){
      return ConfigLoader.getIntProperty("token.expiration.minutes");


    }
     public static int getTimeOuts(){
    return ConfigLoader.getIntProperty("timeouts");
      }
}
