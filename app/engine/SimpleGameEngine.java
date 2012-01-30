/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.*;

import models.Question;
import models.Player;
import models.Score;

/**
 *
 * @author andreas
 */
public class SimpleGameEngine implements GameEngine {

    private Map<String, GameSession> gameSessions = new HashMap<String, GameSession>();

    public String registerPlayer(Player player) {
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
        GameSession session = gameSessions.get(gameSessionId);
        return !session.waitingForMorePlayers();
    }

    public List<Player> getPlayers(String gameSessionId) {
        return gameSessions.get(gameSessionId).getPlayers();
    }

    public Question getNextQuestion(String gameSessionId, String playerId) {
        play.Logger.info("Getting next question for player: " + playerId + ", game session: " + gameSessionId);
        return gameSessions.get(gameSessionId).nextQuestion(playerId);
    }

    public boolean answerQuestion(String gameSessionId, String playerId, Long questionId, Long answerId) {
        play.Logger.info("Player " + playerId + " answered question " + questionId + " with " + answerId + ", game session: ", gameSessionId);
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
        if (allQuestions.size() > numberOfQuestions) {
            int amount = Math.min(numberOfQuestions, allQuestions.size());
            questions = new ArrayList<Question>();
            Collections.shuffle(allQuestions);
            for (int i = 0; i < amount; i++) {
                questions.add(allQuestions.get(i));
            }
        } else {
            questions = allQuestions;
        }
        return questions;
    }

    public void stopGameSession(String gameSessionId) {
        gameSessions.get(gameSessionId).stop();
    }
}
