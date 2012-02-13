/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.*;
import javax.mail.Session;

import models.Question;
import models.Player;
import models.Score;

/**
 *
 * @author andreas
 */
public class SimpleGameEngine implements GameEngine {

    public static final int NUMBER_OF_QUESTIONS = 10;
    private Map<String, GameSession> gameSessions = new HashMap<String, GameSession>();

    public String registerPlayer(Player player) {
        for (GameSession session : gameSessions.values()) {
            if (session.waitingForMorePlayers()) {
                return addPlayerToSession(session, player);
            }
        }
        play.Logger.info("Creating new GameSession");
        GameSession session = new BasicGameSession(loadQuestions());
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
        if (gameSessionId == null || gameSessions.get(gameSessionId) == null) {
            return Collections.emptyList();
        }
        return gameSessions.get(gameSessionId).getScores();
    }

    private List<Question> loadQuestions() {
        play.Logger.info("Loading questions");
        List<Question> allQuestions = Question.findAll();
        play.Logger.info("Total number of questions in DB: " + allQuestions.size());
        List<Question> questions = new ArrayList<Question>(allQuestions);
        Collections.shuffle(questions, new Random(System.currentTimeMillis()));
        return Collections.unmodifiableList(questions);
    }

    public GameSession getGameSession(String gameSessionId){
        return gameSessions.get(gameSessionId);
    }

    public Collection<GameSession> getAllGameSessions() {
        return gameSessions.values();
    }

    public void stopGameSession(String gameSessionId) {
        GameSession gameSession = gameSessions.get(gameSessionId);
        if (gameSession!=null) {
            for (Player player : gameSession.getPlayers()) {
                gameSession.stop(player.getId().toString());
            }
            gameSessions.remove(gameSessionId);
        }
    }
}
