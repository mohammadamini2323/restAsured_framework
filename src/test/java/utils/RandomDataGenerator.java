package utils;

public class RandomDataGenerator {
    public static String generateRandomEmail(){
        int random =(int) (Math.random()*1000);
        return "thisIsRE"+System.currentTimeMillis()+random;
    }
    public static String generateRandomUserName(){
        int random =(int) (Math.random()*1000);
        return "user@"+System.currentTimeMillis()+random+"time";
    }
    public static String generateRandomPassword(){
        int random =(int) (Math.random()*1000);
        return "Password@"+System.currentTimeMillis()+random+"time!";
    }
}
