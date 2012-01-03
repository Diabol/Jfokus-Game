package engine;

import models.Score;
import play.db.jpa.JPA;

import javax.persistence.Query;
import java.util.List;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class ArenaScoreboard implements Scoreboard {

    public static final Integer MAX_RESULTS = 100;

    @Override
    @SuppressWarnings("unchecked")
    public List<Score> getScore() {
        Query query = JPA.em().createQuery("select s from Score s order by s.numberOfPoints");
        List<Score> scores = query.setMaxResults(MAX_RESULTS).getResultList();
        return scores;
    }

}
