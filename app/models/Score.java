package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @author andreas
 */
@Entity
public class Score extends Model {
    public int numberOfAnswers;
    public int numberOfCorrectAnswers;
    public int numberOfIncorrectAnswers;
    public int numberOfPoints;

    @ManyToOne
    public GameRound gameRound;
    @ManyToOne
    public Player player;
        
}
