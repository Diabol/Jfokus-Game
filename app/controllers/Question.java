package controllers;

import play.data.validation.Required;
import play.mvc.Controller;

import java.util.Random;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class Question extends Controller {

    // TODO: To fetch next question
    public static void question(final String playerId) {
        String question = "Q";
        String answer1 = "1";
        String answer2 = "2";
        String answer3 = "3";
        String answer4 = "4";

        render(question, answer1, answer2, answer3, answer4);
    }

    // TODO: Rename method (cascade into html/routes etc)
    public static void validate(@Required String question, @Required String answer) {
        System.out.println(System.currentTimeMillis() + "\tQ: " + question + ", A: " + answer);

        String status;
        String correctAnswer = Integer.toString(new Random().nextInt(4) + 1);
        System.out.println("Correct answer was: " + correctAnswer);
        if (answer.equalsIgnoreCase(correctAnswer)) {
            status = "correct!";
        } else {
            status = "wrong! correct answer was: " + correctAnswer;
        }
        // TODO: Handle user input/answer via GameEngine/Session

        // TODO: Get next question from GameSession
        question = "New Q " + System.currentTimeMillis();
        String answer1 = "1";
        String answer2 = "2";
        String answer3 = "3";
        String answer4 = "4";

        renderTemplate("Question/question.html", question, answer1, answer2, answer3, answer4, status);
    }
}
