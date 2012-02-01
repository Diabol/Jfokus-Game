package controllers;

import engine.GameEngine;
import engine.GameSession;
import java.util.List;
import javax.inject.Inject;
import models.Player;
import play.mvc.Controller;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class Game extends Controller {
    
    @Inject
    static GameEngine gameEngine;

    public static void waitForStart() {
        String gameSessionId = session.get("gameSessionId");
        render(gameSessionId);
    }

    public static void waitForOtherPlayer() {
        String gameSessionId = session.get("gameSessionId");
        render(gameSessionId);
    }
    
    public static void hasStarted() {
        String gameSessionId = session.get("gameSessionId");
        renderText(gameEngine.hasSessionStarted(gameSessionId));
    }
    
    public static void isDone() {
        String gameSessionId = session.get("gameSessionId");
        renderText(gameEngine.getGameSession(gameSessionId).isDone());
    }
    public static void start() {
        String gameSessionId = session.get("gameSessionId");
        GameSession gameSession = gameEngine.getGameSession(gameSessionId);
        List<Player> players = gameSession.getPlayers();
        play.Logger.info("Numbers of players: "+players.size());
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        gameSession.start();
        render(player1,player2);
    }
    
    public static void stop() {
        String gameSessionId = session.get("gameSessionId");
        String playerId = session.get("playerId");
        GameSession gameSession = gameEngine.getGameSession(gameSessionId);
        gameSession.stop(playerId);
        if(gameSession.isDone()){
            redirect("/score"); 
        } else {
            redirect("/Game/WaitForOtherPlayer");             
        }
    }
}
