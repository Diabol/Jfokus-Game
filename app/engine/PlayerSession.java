package engine;

import controllers.Question;
import models.Player;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public interface PlayerSession {
    
    public String getId();

    public void addCorrectAnswer();

    public void addErroneousAnswer();
    
    public Question getCurrentQuestion();
    
    public Question getNextQuestion();
    
    public Player getPlayer();

}
