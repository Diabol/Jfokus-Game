package controllers;

import java.util.List;
import javax.persistence.Query;
import models.Score;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class Scoreboard extends Controller {

    public static void index() {
        Query query = JPA.em().createQuery("select s from Score s order by s.numberOfPoints desc");
        List<Score> scores = query.setMaxResults(100).getResultList();
        render(scores);
    }
}