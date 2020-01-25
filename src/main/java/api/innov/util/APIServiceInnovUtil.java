package api.innov.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



import org.apache.commons.codec.binary.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class APIServiceInnovUtil {
	
    private static final int iterations = Integer.parseInt(SystemParameters.getInstance().getProperty("iterations"));
    private static final int saltLen = Integer.parseInt(SystemParameters.getInstance().getProperty("saltLen"));
    private static final int desiredKeyLen = Integer.parseInt(SystemParameters.getInstance().getProperty("desiredKeyLen"));;;
	
	 /**
     * Computes a salted PBKDF2 hash of given plain text password suitable for storing in a database. Empty passwords are not supported.
     */
    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /**
     * Checks whether given plain text password corresponds to a stored salted hash of the password.
     */
    public static boolean check(String password, String stored) throws Exception {        
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            return false;
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Empty passwords are not supported.");
        }
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.encodeBase64String(key.getEncoded());
    }
    
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final java.util.Base64.Encoder base64Encoder = java.util.Base64.getUrlEncoder(); //threadsafe

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
    
    public static String generateNewToken(String follow) {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return generateNewToken()+"::"+follow;
    }
    
    
    private static String SECRET_KEY = SystemParameters.getInstance().getProperty("SECRET_KEY");
    		
    
    public static String createJWT(String id, String issuer, String subject, long ttlMillis, String IP ) {
    	//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> header = new HashMap<>();
        header.put("IP", IP);
                
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
        		.setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .setHeader(header)  
                .claim("IP",IP)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    
    
    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();        
        return claims;
    }

}
