package engine;

import java.util.Collection;
import models.Question;
import java.util.List;
import java.util.Set;
import models.Player;
import models.Score;

/**
 * Interface for a typical game engine core
 *
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface GameEngine {

    public String registerPlayer(final Player player);
    public boolean hasSessionStarted(String gameSessionId);
    public Question getNextQuestion(String gameSessionId, String playerId);
    public boolean answerQuestion(String gameSessionId, String playerId, Long questionId, Long answerId);
    public List<Score> getScores(String gameSessionId);
    public GameSession getGameSession(String gameSessionId);
    public void stopGameSession(String gameSessionId);
    public Collection<GameSession> getAllGameSessions();
    public void flushAllGameSessions();
}