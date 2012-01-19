package engine;

import models.Question;
import java.util.List;
import models.Player;

/**
 * Some kind of bootstrapping features for GameSessions
 *
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface GameEngine {

    public String registerPlayer(final Player player);
    public boolean hasSessionStarted(String gameSessionId);
    public List<Player> getPlayers(String gameSessionId);
    public Question getNextQuestion(String gameSessionId, String playerId);
}