package controllers;

import play.data.validation.Required;
import play.mvc.Controller;

/**
 * @author <a href="mailto:tynjae@gmail.com">Tommy Tynj&auml;</a>
 */
public class Game extends Controller {

    public static void hasStarted(@Required final String playerId) {
        // TODO: Get game session data (non if non-started)
    }
    
}
