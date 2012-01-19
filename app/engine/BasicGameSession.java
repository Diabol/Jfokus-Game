package engine;

import java.util.ArrayList;
import java.util.HashSet;
import models.Question;
import models.Player;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import models.GameRound;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicGameSession implements GameSession {

    private static final Logger LOG = Logger.getLogger("GameSession");
    private final Set<PlayerSession> playerSessions = new LinkedHashSet<PlayerSession>();
    private GameRound gameRound;

    public BasicGameSession(List<Question> questions) {
        gameRound = new GameRound(new HashSet<Question>(questions), null, 180);
        GameRound.em().persist(gameRound);
        LOG.info("Started BasicGameSession with " + questions.size()+" number of questions");
    }

    @Override
    public Long getId() {
        return gameRound.getId();
    }

    

    @Override
    public void addPlayer(final Player player) {
        PlayerSession newPlayerSession = new BasicPlayerSession(player);
        newPlayerSession.setQuestions(gameRound.questions.iterator());
        playerSessions.add(newPlayerSession);
        LOG.info("Successfully add player: " + player.id + " with session: " + newPlayerSession);
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
    public void handleAnswer(final Integer questionId, final Integer answerId) {
    }

    @Override
    public Scoreboard getScore() {
        return null;
    }

    public Question nextQuestion(String playerId) {
        LOG.info("Getting next question for player: "+playerId);
        PlayerSession session = getPlayerSession(playerId);
        Question question = null;
        if (session!=null) {
            question = session.getNextQuestion();
        }
        LOG.info("Next question is: "+(question==null?"null":question.text));
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

}
