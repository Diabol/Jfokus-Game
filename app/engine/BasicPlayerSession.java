package engine;

import java.util.Iterator;
import models.Question;
import models.Player;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicPlayerSession implements PlayerSession {
    
    private Player player;
    private Iterator<Question> questionsIterator;
    private Question currentQuestion;

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
        return currentQuestion;
    }

    @Override
    public Question getNextQuestion() {
        if (questionsIterator.hasNext()) {
            currentQuestion = questionsIterator.next();
        } else {
            currentQuestion = null;
        }
        return currentQuestion;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setQuestions(Iterator<Question> questionIterator) {
        this.questionsIterator = questionIterator;
    }
    
}
