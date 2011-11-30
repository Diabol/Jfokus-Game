package models;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import play.db.jpa.Model;

/**
 *
 * @author andreas
 */
@Entity
public class GameRound extends Model {
    public Date startTime;
    public Date stopTime;
    public long lengthInSeconds;
    @Transient
    public Set<Question> questions;
    @OneToMany(mappedBy="gameRound", cascade=CascadeType.ALL)
    public Set<Score> scores;
    
    public GameRound(Set<Question> questions, Set<Score> scores, long lengthInSeconds) {
        this.questions = questions;
        this.lengthInSeconds = lengthInSeconds;
    }
    
    public void start() {
        this.startTime = new Date();
    }
    
    public void stop() {
        this.stopTime = new Date();
    }
}
