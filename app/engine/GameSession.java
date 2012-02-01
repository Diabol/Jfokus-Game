package engine;

import models.Question;
import models.Player;

import java.util.List;
import java.util.Set;
import models.Score;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface GameSession {

    /**
     * Get the session id
     * @return the session id
     */
    public Long getId();
    
    public void addPlayer(final Player player);
    
    public List<Player> getPlayers();
    
    public void start();
    
    public boolean waitingForMorePlayers();

    public void handleAnswer(String playerId, Question question, boolean correct);
    
    public Question nextQuestion(String playerId);
    
    public List<Score> getScores();

    public void stop(String playerId);
    
    public boolean isDone();

}
