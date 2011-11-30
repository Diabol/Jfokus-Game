package models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

/**
 *
 * @author andreas
 */
@Entity
class Question extends Model {
    public String text;
    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    public Set<Answer> answers;

    public Question(String text, Set<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }
    
}
