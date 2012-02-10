package engine;

import java.util.Date;
import java.util.Iterator;
import models.Question;
import models.Player;
import models.Score;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicPlayerSession implements PlayerSession {

    private static long TIME_THRESHOLD_MS = 65 * 1000;

    private Player player;
    private Score score;
    private Date start;
    private Date stop;
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
        Score s = Score.findById(score.getId());
        s.numberOfCorrectAnswers++;
        s.numberOfPoints++;
        update(s);
    }

    @Override
    public void addErroneousAnswer() {
        Score s = Score.findById(score.getId());
        s.numberOfIncorrectAnswers++;
        update(s);
    }

    private void update(final Score score) {
        score.numberOfAnswers++;
        score.save();
        this.score = score;
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

    public void start() {
        start = new Date();
    }

    public void stop() {
        stop = new Date();
    }
    
    public boolean isDone(){
        Long timeElapsed = getSecondsLeft();
        play.Logger.info("BasicPlayerSession.isDone: Time left for player " + player.id + ": " + timeElapsed + "s");
        if (timeElapsed <= 0) {
            stop();
            play.Logger.info("Stopping session for player " + getId());
        }
        return (stop != null);
    }

    public Long getSecondsLeft() {
        Long timeElapsed = System.currentTimeMillis() - getStartTime().getTime();
        return (TIME_THRESHOLD_MS - timeElapsed) / 1000L;
    }

    public Date getStartTime() {
        return start;
    }

    public Date getStoppedTime() {
        return stop;
    }
}
