package controllers;

import engine.GameEngine;
import engine.GameSession;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import models.Configuration;
import models.GameRound;
import models.Player;
import models.Question;
import play.mvc.Controller;
import play.mvc.With;
import util.ConfigManager;

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
        long numberOfQuestions = Question.count();
        
        render(numberOfSessions, numberOfPlayers, numberOfGameRounds, numberOfQuestions);
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
    
    public static void deleteGameRound(Long id) { 
        if (id != null) {
            GameRound gameRound = GameRound.findById(id);
            if (gameRound!=null) {
                gameRound.delete();
            }
        }
        listGameRounds();
    }
    
    public static void listQuestions() {
        List<Question> questions = Question.findAll();
        render(questions);
    }
    
    public static void editConfiguration() {
        Configuration config = ConfigManager.get();
        render(config);
    }
    
    public static void saveConfiguration(Configuration config) {
        play.Logger.info("Saving configuration: %s", config.toString());
        config.save();
        ConfigManager.reload();
        editConfiguration();
    }
}
