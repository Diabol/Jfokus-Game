package controllers;

import engine.GameEngine;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;
import models.Score;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class Scoreboard extends Controller {

    @Inject
    static GameEngine gameEngine;
    
    public static void index() {
        String gameSessionId = session.get("gameSessionId");
        List<Score> scores = gameEngine.getScores(gameSessionId);
        render(scores);
    }
    
    public static void all() {
        Query query = JPA.em().createQuery("select s from Score s order by s.numberOfPoints desc");
        List<Score> scores = query.setMaxResults(100).getResultList();
        render("@index",scores);
    }
}