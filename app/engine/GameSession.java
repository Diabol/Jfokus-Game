package engine;

import controllers.Question;

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
    
    public void registerPlayer();
    
    public void start();

    public void handleAnswer(final Integer questionId, final Integer answerId);

    public Scoreboard getScore();

}
