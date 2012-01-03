package engine;

import controllers.Question;
import models.Player;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicPlayerSession implements PlayerSession {

    public BasicPlayerSession(final Player player) {
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
}
