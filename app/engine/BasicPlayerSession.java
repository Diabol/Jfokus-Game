package engine;

import controllers.Question;
import models.Player;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicPlayerSession implements PlayerSession {
    
    private Player player;

    public BasicPlayerSession(final Player player) {
        this.player = player;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void addCorrectAnswer() {
    }

    @Override
    public void addErroneousAnswer() {
    }

    @Override
    public Question getCurrentQuestion() {
        return null;
    }

    @Override
    public Question getNextQuestion() {
        return null;
    }

    public Player getPlayer() {
        return this.player;
    }
    
}
