package engine;

import controllers.Question;
import models.Player;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicGameSession implements GameSession {

    private static final Logger LOG = Logger.getLogger("GameSession");
    private final Set<PlayerSession> playerSessions = new LinkedHashSet<PlayerSession>();

    public BasicGameSession() {
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public List<Question> loadQuestions() {
        return null;
    }

    @Override
    public void registerPlayer(final Player player) {
        LOG.info("Registering player: " + player);

        Player.em().persist(player);
        LOG.info("Persisted player: " + player);

        PlayerSession newPlayerSession = new BasicPlayerSession(player);
        playerSessions.add(newPlayerSession);
        LOG.info("Successfully registered player: " + player + " with session: " + newPlayerSession);
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
}
