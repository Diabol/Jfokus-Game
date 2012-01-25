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
import models.Score;

/**
 *
 * @author andreas
 */
public class SimpleGameEngine implements GameEngine {
    
    private GameSession gameSession;
    
    public String registerPlayer(Player player) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        if (gameSession==null) {
            play.Logger.info("Creating new GameSession");
            gameSession = new BasicGameSession(loadQuestions(10));
        }
        gameSession.addPlayer(player);
        play.Logger.info("Registred player: "+player.name);
        return ""+gameSession.getId().longValue();
    }

    public boolean hasSessionStarted(String gameSessionId) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
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
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        return gameSession.nextQuestion(playerId);
    }
    
    public boolean answerQuestion(String gameSessionId, String playerId, Long questionId, Long answerId) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        models.Question question = models.Question.findById(questionId);
        models.Answer answer = models.Answer.findById(answerId);
        boolean correct = answer.equals(question.getCorrectAnswer());
        gameSession.handleAnswer(playerId, question, correct);
        return correct;
    }
    
    public List<Score> getScores(String gameSessionId) {
        return gameSession.getScores();
    }

    
    private List<Question> loadQuestions(int numberOfQuestions) {
        play.Logger.info("Loading " + numberOfQuestions + " questions");
        List<Question> allQuestions  = Question.findAll();
        play.Logger.info("Total number of quetions in DB: "+allQuestions.size());
        List<Question> questions = null;
        if (allQuestions.size() <= numberOfQuestions) {
            questions = allQuestions;
        } else {
            Random rnd = new Random(System.currentTimeMillis());
            List<Question> randomList = new ArrayList<Question>(Math.min(numberOfQuestions, allQuestions.size()));
            for (int i = 0; i < randomList.size(); i++){
                randomList.add(allQuestions.get(rnd.nextInt(randomList.size())));
            }
            questions = randomList;
        }
        return questions;
    }

    public void stopGameSession(String gameSessionId) {
        gameSession.stop();
    }
    
}
