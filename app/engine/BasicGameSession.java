package engine;

import models.GameRound;
import models.Player;
import models.Question;
import models.Score;
import play.db.jpa.JPA;

import java.util.*;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicGameSession implements GameSession {

    private final Set<PlayerSession> playerSessions = new LinkedHashSet<PlayerSession>();
    private GameRound gameRound;
    private int numberOfPlayersPerRound = 2;

    public BasicGameSession(List<Question> questions) {
        gameRound = new GameRound(new LinkedHashSet<Question>(questions), null, 180);
        gameRound.startTime = new Date();
        GameRound.em().persist(gameRound);
        play.Logger.info("Started BasicGameSession with " + questions.size() + " number of questions");
    }

    @Override
    public Long getId() {
        return gameRound.getId();
    }

    @Override
    public void addPlayer(final Player player) {
        Score score = createNewScore(player);
        PlayerSession newPlayerSession = new BasicPlayerSession(player, score);
        newPlayerSession.setQuestions(gameRound.questions.iterator());
        playerSessions.add(newPlayerSession);
        play.Logger.info("Added player " + player.id + " to game session: " + newPlayerSession.getId());
    }

    private Score createNewScore(final Player player) {
        Score score = new Score();
        score.player = player;
        score.gameRound = gameRound;
        if (gameRound.scores == null) {
            gameRound.scores = new HashSet<Score>();
        }
        gameRound.scores.add(score);
        JPA.em().persist(score);
        return score;
    }

    public List<Player> getPlayers() {
        List<Player> playerList = new LinkedList<Player>();
        for (PlayerSession playerSession : playerSessions) {
            playerList.add(playerSession.getPlayer());
        }
        return playerList;
    }

    @Override
    public void start() {
        gameRound.start();
        for(PlayerSession ps : playerSessions){
            ps.start();
        }
    }

    @Override
    public void handleAnswer(String playerId, Question question, boolean correct) {
        PlayerSession playerSession = getPlayerSession(playerId);
        if (!playerSession.isDone()) {
            if (correct) {
                playerSession.addCorrectAnswer();
            } else {
                playerSession.addErroneousAnswer();
            }
        }
    }

    @Override
    public List<Score> getScores() {
        gameRound = GameRound.findById(gameRound.getId());
        return new ArrayList<Score>(gameRound.scores);
    }

    public Question nextQuestion(String playerId) {
        play.Logger.info("Getting next question for player: " + playerId);
        PlayerSession session = getPlayerSession(playerId);
        Question question = null;
        if (session != null && !session.isDone()) {
            question = session.getNextQuestion();
        }
        play.Logger.info("Next question is: " + (question == null ? "null" : question.getId() + " = " + question.text));
        return question;
    }

    public PlayerSession getPlayerSession(String playerId) {
        if (playerSessions != null && playerId != null) {
            for (PlayerSession session : playerSessions) {
                if (session.getPlayer().id.equals(Long.parseLong(playerId))) {
                    return session;
                }
            }
        }
        return null;
    }

    public void stop(String playerId) {
        gameRound = GameRound.findById(gameRound.getId());
        PlayerSession playerSession = getPlayerSession(playerId);
        if (playerSession != null) {
            playerSession.stop();
        }
        gameRound.stopTime = new Date();
        gameRound.save();
    }

    public boolean waitingForMorePlayers() {
        return playerSessions.size() < numberOfPlayersPerRound;
    }

    public boolean isDone(){
        for(PlayerSession playerSession : playerSessions){
            if(!playerSession.isDone()) return false;
        }
        return true;
    }
}
