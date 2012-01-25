package engine;

import java.util.Iterator;
import models.Question;
import models.Player;
import models.Score;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface PlayerSession {
    
    public String getId();

    public void addCorrectAnswer();

    public void addErroneousAnswer();
    
    public Question getCurrentQuestion();
    
    public Question getNextQuestion();
    
    public void setQuestions(Iterator<Question> questionIterator);
    
    public Player getPlayer();
    
    public Score getScore();

}
