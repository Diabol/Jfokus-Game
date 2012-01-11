package controllers;

import java.util.Date;
import java.util.List;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.Random;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class Question extends Controller {

    public static final String ANSWER_CANADA = "That canadian guy";

    // TODO: To fetch next question
    public static void question(final String playerId) {
        String question = "Who doesn't like sauce?";
        String answer1 = "Peter";
        String answer2 = "Daniel";
        String answer3 = "Andreas";
        String answer4 = ANSWER_CANADA;
        String score = "12/18";
        render(question, answer1, answer2, answer3, answer4, score);
    }
    
    public static void list() {
        List<models.Question> questions = models.Question.findAll();
        render(questions);
    }

    // TODO: Rename method (cascade into html/routes etc)
    public static void validate(@Required String question, @Required String answer) {
        System.out.println(System.currentTimeMillis() + "\tQ: " + question + ", A: " + answer);

        String correct;
        if (answer.equalsIgnoreCase(ANSWER_CANADA)) {
            correct = "Correct answer!";
        } else {
            correct = "Wrong answer!";
        }
        // TODO: Handle user input/answer via GameEngine/Session

        String score = "12/18";
        
        // TODO: Get next question from GameSession
        question = "Who still doesn't like sauce (@ " + new Date() + ")";
        String answer1 = "Patrik";
        String answer2 = "Tommy";
        String answer3 = ANSWER_CANADA;
        String answer4 = "Per";

        renderTemplate("Question/question.html", question, answer1, answer2, answer3, answer4, correct, score);
    }
}
