package controllers;

import engine.GameEngine;
import java.util.List;
import java.util.Set;
import play.mvc.Controller;

import javax.inject.Inject;
import models.Answer;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class Question extends Controller {
    
    @Inject
    static GameEngine gameEngine;

    public static void next() {
        String gameSessionId = session.get("gameSessionId");
        String playerId = session.get("playerId");
        models.Question question = gameEngine.getNextQuestion(gameSessionId, playerId);
        if (question == null) {
            redirect("/game/stop");
        } else {
            render("@question",question);
        }
    }
    
    public static void answer(String question, String answer) {
        validation.required(question);
        validation.required(answer);
        play.Logger.info("Answering question " + question + " with answer " + answer);
        String gameSessionId = session.get("gameSessionId");
        String playerId = session.get("playerId");
        boolean correct = gameEngine.answerQuestion(gameSessionId, playerId, Long.parseLong(question), Long.parseLong(answer));
        play.Logger.info("Answer was: " + (correct ? "Correct" : "Incorrect"));
        next();
    }
    
    public static void list() {
        List<models.Question> questions = models.Question.findAll();
        render(questions);
    }

}
