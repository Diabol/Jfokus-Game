package engine;

import models.Score;

import java.util.Map;

/**
 * @author <a href="mailto:tynjae@gmail.com">Tommy Tynj&auml;</a>
 */
public interface Scoreboard {

    public Map<PlayerSession, Score> getPlayerScores();

}
