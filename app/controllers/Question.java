package controllers;

import play.data.validation.Required;
import play.mvc.Controller;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class Question extends Controller {

    public static void question() {
        String question = "Q";
        String answer1 = "1";
        String answer2 = "2";
        String answer3 = "3";
        String answer4 = "4";

        render(question, answer1, answer2, answer3, answer4);
    }

    public static void validate(@Required String question, @Required String answer) {
        System.out.println("Q: " + question + ", A: " + answer);
        
        question();
    }
}
