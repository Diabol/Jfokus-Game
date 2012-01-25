package engine;

import java.util.ArrayList;
import java.util.HashSet;
import models.Question;
import models.Player;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import models.GameRound;
import models.Score;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicGameSession implements GameSession {

    private final Set<PlayerSession> playerSessions = new LinkedHashSet<PlayerSession>();
    private GameRound gameRound;
    private int numberOfPlayersPerRound = 2;

    public BasicGameSession(List<Question> questions) {
        gameRound = new GameRound(new HashSet<Question>(questions), null, 180);
        GameRound.em().persist(gameRound);
        play.Logger.info("Started BasicGameSession with " + questions.size()+" number of questions");
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
        play.Logger.info("Successfully add player: " + player.id + " with session: " + newPlayerSession);
    }

    private Score createNewScore(final Player player) {
        Score score = new Score();
        score.player = player;
        score.gameRound = gameRound;
        if (gameRound.scores==null) {
            gameRound.scores = new HashSet<Score>();
        }
        gameRound.scores.add(score);
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
    }

    @Override
    public void handleAnswer(String playerId, Question question, boolean correct) {
        PlayerSession playerSession = getPlayerSession(playerId);
        if (correct) {
            playerSession.addCorrectAnswer();
        } else {
            playerSession.addErroneousAnswer();
        }
    }

    @Override
    public List<Score> getScores() {
        return new ArrayList(gameRound.scores);
    }

    public Question nextQuestion(String playerId) {
        play.Logger.info("Getting next question for player: "+playerId);
        PlayerSession session = getPlayerSession(playerId);
        Question question = null;
        if (session!=null) {
            question = session.getNextQuestion();
        }
        play.Logger.info("Next question is: "+(question==null?"null":question.text));
        return question;
    }
    
    private PlayerSession getPlayerSession(String playerId) {
        for (PlayerSession session : playerSessions) {
            if (session.getPlayer().id.equals(Long.parseLong(playerId))) {
                return session;
            }
        }
        return null;
    }

    public void stop() {
        // TODO this fails because GameRound is not attached to the hibernate session anymore...
        //gameRound.save();
    }

    public boolean waitingForMorePlayers() {
        return playerSessions.size() < numberOfPlayersPerRound;
    }

}
