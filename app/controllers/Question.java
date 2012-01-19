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

    public static void next(final String playerId) {
        String gameSessionId = session.get("gameSessionId");
        models.Question question = gameEngine.getNextQuestion(gameSessionId, playerId);
        render("@question",question);
    }
    
    public static void answer() {
        String gameSessionId = session.get("gameSessionId");
    }
    
    public static void list() {
        List<models.Question> questions = models.Question.findAll();
        render(questions);
    }

}
