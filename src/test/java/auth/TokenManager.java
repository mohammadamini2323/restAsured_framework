package auth;

import utils.ConfigUtils;

import java.time.Instant;

public class TokenManager {


    private static String superVisorToken;
    private static String generalUserToken;

    private static Instant superVisorTokenGeneratedAt;
    private static Instant generalUserTokenGeneratedAt;
    private static final long  TokenExpirationInMinutes= ConfigUtils.getTokEnExpirationInMinutes();

    public static String getSuperVisorToken() {
        if ( superVisorToken == null || isTokenExpired(superVisorTokenGeneratedAt) ) {
            superVisorToken = TokenGenerator.getSupervisorToken();
            superVisorTokenGeneratedAt = Instant.now();
        }
        return superVisorToken;
    }

    public static String getGeneralUserToken() {
        if ( generalUserToken == null || isTokenExpired(generalUserTokenGeneratedAt) ) {
            generalUserToken = TokenGenerator.getSupervisorToken();
            generalUserTokenGeneratedAt = Instant.now();
        }
        return generalUserToken;
    }



    private static boolean isTokenExpired(Instant generatedAt) {
        if (generatedAt == null) return true;
        Instant expirationTime = generatedAt.plusSeconds(TokenExpirationInMinutes * 60);
        return Instant.now().isAfter(expirationTime);
    }
    public static void tearDown() {
        superVisorToken = null;
        generalUserToken = null;
        superVisorTokenGeneratedAt = null;
        generalUserTokenGeneratedAt = null;
    }






}
