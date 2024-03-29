package models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

/**
 *
 * @author andreas
 */
@Entity
public class Question extends Model {
    public String text;
    @OneToMany(mappedBy="question", cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    public Set<Answer> answers;

    public Question(String text, Set<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }
    
    public Answer getCorrectAnswer() {
        Answer correctAnswer = null;
        for (Answer answer : answers) {
            if (answer.correct) {
                correctAnswer = answer;
                break;
            }
        }
        return correctAnswer;
    }
}
