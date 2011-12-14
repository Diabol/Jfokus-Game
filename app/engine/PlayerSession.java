package engine;

import controllers.Question;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface PlayerSession {
    
    public String getId();

    public void addCorrectAnswer();

    public void addErroneousAnswer();
    
    public Question getCurrentQuestion();
    
    public Question getNextQuestion();

}
