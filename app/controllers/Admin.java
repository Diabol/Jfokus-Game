package controllers;

import engine.GameEngine;
import engine.GameSession;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import models.GameRound;
import models.Player;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author andreas
 */
@With(Secure.class)
public class Admin extends Controller {
    
    @Inject
    static GameEngine gameEngine;
    
    public static void index() {
        Collection<GameSession> sessions = gameEngine.getAllGameSessions();
        int numberOfSessions = sessions.size();
        
        long numberOfPlayers = Player.count();
        long numberOfGameRounds = GameRound.count();
        
        render(numberOfSessions, numberOfPlayers, numberOfGameRounds);
    }
    
    public static void clearGameSessions() {
        Collection<GameSession> onGoingGameSessions = gameEngine.getAllGameSessions();
        for (GameSession session : onGoingGameSessions) {
            gameEngine.stopGameSession(session.getId().toString());
        }
        index();
    }
    
    public static void listPlayers() {
        List<Player> players = Player.findAll();
        render(players);
    }
    
    public static void listGameRounds() {
        List<GameRound> games = GameRound.findAll();
        render(games);
    }
}
