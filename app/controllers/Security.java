package controllers;

/**
 *
 * @author andreas
 */
public class Security extends Secure.Security {
     
     private static final String ADMIN_USER = "diabol";
     private static final String ADMIN_PWD = "jF0kus2012";
    
     static boolean authenticate(String username, String password) {
        boolean ok = ADMIN_USER.equals(username) && ADMIN_PWD.equals(password);
        play.Logger.info("Auth request for user: %s %s", username, (ok?"granted":"denied"));
        return ok;
     } 
}
