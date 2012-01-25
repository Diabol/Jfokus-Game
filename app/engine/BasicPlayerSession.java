package engine;

import java.util.Iterator;
import models.Question;
import models.Player;
import models.Score;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicPlayerSession implements PlayerSession {
    
    private Player player;
    private Score score;
    private Iterator<Question> questionsIterator;
    private Question currentQuestion;

    public BasicPlayerSession(final Player player, final Score score) {
        this.player = player;
        this.score =  score;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void addCorrectAnswer() {
        this.score.numberOfAnswers++;
        this.score.numberOfCorrectAnswers++;
        this.score.numberOfPoints++;
    }

    @Override
    public void addErroneousAnswer() {
        this.score.numberOfAnswers++;
        this.score.numberOfIncorrectAnswers++;
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

    public Score getScore() {
        return score;
    }

    public void setQuestions(Iterator<Question> questionIterator) {
        this.questionsIterator = questionIterator;
    }
    
}
