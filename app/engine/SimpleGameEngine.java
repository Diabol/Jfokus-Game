/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.ArrayList;
import java.util.HashSet;
import models.Question;
import java.util.List;
import java.util.Random;
import java.util.Set;
import models.Player;
import org.apache.log4j.Logger;

/**
 *
 * @author andreas
 */
public class SimpleGameEngine implements GameEngine {
    private static final Logger LOG = Logger.getLogger("Game");
    
    private GameSession gameSession;

    public String registerPlayer(Player player) {
        if (gameSession==null) {
            LOG.info("Creating new GameSession");
            gameSession = new BasicGameSession(loadQuestions(10));
        }
        gameSession.addPlayer(player);
        LOG.info("Registred player: "+player.name);
        return ""+gameSession.getId().longValue();
    }

    public boolean hasSessionStarted(String gameSessionId) {
        boolean doStart = new Random(System.currentTimeMillis()).nextBoolean();
        if (doStart && gameSession.getPlayers().size()<2) {
            gameSession.addPlayer(Player.getComputer());
        }
        return doStart;
    }

    public List<Player> getPlayers(String gameSessionId) {
        return gameSession.getPlayers();
    }

    public Question getNextQuestion(String gameSessionId, String playerId) {
        return gameSession.nextQuestion(playerId);
    }
    
    private List<Question> loadQuestions(int numberOfQuestions) {
        LOG.info("Loading " + numberOfQuestions + " questions");
        List<Question> allQuestions  = Question.findAll();
        LOG.info("Total number of quetions in DB: "+allQuestions.size());
        Random rnd = new Random(System.currentTimeMillis());
        List<Question> randomList = new ArrayList<Question>(Math.min(numberOfQuestions, allQuestions.size()));
        for (int i = 0; i < randomList.size(); i++){
            randomList.add(allQuestions.get(rnd.nextInt(randomList.size())));
        }
        return randomList;
    }
    
}
