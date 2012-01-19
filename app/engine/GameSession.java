package engine;

import models.Question;
import models.Player;

import java.util.List;
import java.util.Set;

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

    public void handleAnswer(final Integer questionId, final Integer answerId);
    
    public Question nextQuestion(String playerId);
    
    public Scoreboard getScore();
    

}
