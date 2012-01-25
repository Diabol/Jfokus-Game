/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.ArrayList;
import java.util.HashMap;
import models.Question;
import java.util.List;
import java.util.Map;
import java.util.Random;
import models.Player;
import models.Score;

/**
 *
 * @author andreas
 */
public class SimpleGameEngine implements GameEngine {

    private Map<String, GameSession> gameSessions = new HashMap<String, GameSession>();

    public String registerPlayer(Player player) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        for (GameSession session : gameSessions.values()) {
            if (session.waitingForMorePlayers()) {
                return addPlayerToSession(session, player);
            }
        }
        play.Logger.info("Creating new GameSession");
        GameSession session = new BasicGameSession(loadQuestions(10));
        gameSessions.put(session.getId().toString(), session);
        return addPlayerToSession(session, player);
    }

    private String addPlayerToSession(GameSession session, Player player) {
        session.addPlayer(player);
        play.Logger.info("Registred player: " + player.name);
        return session.getId().toString();
    }

    public boolean hasSessionStarted(String gameSessionId) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        GameSession session = gameSessions.get(gameSessionId);
        return !session.waitingForMorePlayers();
    }

    public List<Player> getPlayers(String gameSessionId) {
        return gameSessions.get(gameSessionId).getPlayers();
    }

    public Question getNextQuestion(String gameSessionId, String playerId) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        return gameSessions.get(gameSessionId).nextQuestion(playerId);
    }

    public boolean answerQuestion(String gameSessionId, String playerId, Long questionId, Long answerId) {
        play.Logger.info("SimpleGameEnging: " + this.hashCode());
        models.Question question = models.Question.findById(questionId);
        models.Answer answer = models.Answer.findById(answerId);
        boolean correct = answer.equals(question.getCorrectAnswer());
        gameSessions.get(gameSessionId).handleAnswer(playerId, question, correct);
        return correct;
    }

    public List<Score> getScores(String gameSessionId) {
        return gameSessions.get(gameSessionId).getScores();
    }

    private List<Question> loadQuestions(int numberOfQuestions) {
        play.Logger.info("Loading " + numberOfQuestions + " questions");
        List<Question> allQuestions = Question.findAll();
        play.Logger.info("Total number of quetions in DB: " + allQuestions.size());
        List<Question> questions = null;
        if (allQuestions.size() <= numberOfQuestions) {
            questions = allQuestions;
        } else {
            Random rnd = new Random(System.currentTimeMillis());
            List<Question> randomList = new ArrayList<Question>(Math.min(numberOfQuestions, allQuestions.size()));
            for (int i = 0; i < randomList.size(); i++) {
                randomList.add(allQuestions.get(rnd.nextInt(randomList.size())));
            }
            questions = randomList;
        }
        return questions;
    }

    public void stopGameSession(String gameSessionId) {
        gameSessions.get(gameSessionId).stop();
    }
}
