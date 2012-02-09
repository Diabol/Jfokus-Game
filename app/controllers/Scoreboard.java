package controllers;

import engine.ArenaScoreboard;
import engine.GameEngine;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;

import engine.GameSession;
import models.Score;
import play.data.validation.Match;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class Scoreboard extends Controller {
    
    @Inject
    static GameEngine gameEngine;
    
    public static void index() {
        String gameSessionId = session.get("gameSessionId");
        GameSession gameSession = gameEngine.getGameSession(gameSessionId);
        if (gameSession == null || !gameSession.isDone()) {
            render("@Application.index");
        }
        List<Score> scores = gameEngine.getScores(gameSessionId);
        render(scores);
    }
    
    public static void arena(@Match("1080p|720p") String resolution) {
        render(resolution);
    }
    
    public static void list(int max) {
        if (max == 0) max = ArenaScoreboard.MAX_RESULTS;
        Query query = JPA.em().createQuery("select s from Score s order by s.numberOfPoints desc");
        List<Score> scores = query.setMaxResults(max).getResultList();
        render("@list", scores);
    }
}