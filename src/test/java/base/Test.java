package base;

import config.ConfigLoader;

public class Test {
    public static void main(String[] args) {
        System.out.println(ConfigLoader.getProperties());
        System.out.println("*******");
        System.out.println(ConfigLoader.getIntProperty("timeouts"));
        System.out.println(ConfigLoader.getProperty("test.suite"));
        int newNumber=ConfigLoader.getIntProperty("timeouts")+1000;
        System.out.println(newNumber);
        boolean executeAllTests =Boolean.parseBoolean(ConfigLoader.getProperty("executeAllTests"));
        System.out.println(executeAllTests);
    }
}
