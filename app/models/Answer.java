package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @author andreas
 */
@Entity
class Answer extends Model {
    public String text;
    public boolean correct;
    @ManyToOne
    public Question question;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }
    
}
