package controllers;

import engine.GameEngine;
import javax.inject.Inject;
import play.data.validation.Required;
import play.mvc.Controller;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class Game extends Controller {
    
    @Inject
    static GameEngine gameEngine;

    public static void hasStarted(@Required final String gameSessionId) {
        long timeStamp = System.currentTimeMillis();
        if (gameEngine.hasSessionStarted(gameSessionId)) {
            render("@question", gameSessionId);
        } else {
            render("@watiToStart", gameSessionId, timeStamp);
        }
        
    }
    
}
