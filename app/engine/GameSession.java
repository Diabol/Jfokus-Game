package engine;

import models.Question;
import models.Player;

import java.util.List;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface GameSession {

    /**
     * Get the session id
     * @return the session id
     */
    public Integer getId();

    public List<Question> loadQuestions();
    
    public void registerPlayer(final Player player);
    
    public void start();

    public void handleAnswer(final Integer questionId, final Integer answerId);

    public Scoreboard getScore();

}
