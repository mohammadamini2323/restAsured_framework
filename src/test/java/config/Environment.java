package config;

import utils.ConfigUtils;

public enum Environment {
    DEV("https://bookstore.demoqa.com"),
    QA("https://bookstore.demoqa.com"),
    PROD("https://bookstore.demoqa.com");
    private final String Base_url;

    Environment(String base_url){
     this.Base_url=base_url;
    }
    public String getBaseUrl(){
        return this.Base_url;
    }
    public static Environment getCurrentEnvironment(){
        return Environment.valueOf(ConfigUtils.getEnvironment());

    }

}
