package models;

import com.google.gson.Gson;
import javax.persistence.*;
 
import play.db.jpa.*;


/**
 * Represents a participant in a GameRound
 * 
 * @author andreas
 */
@Entity
public class Configuration extends Model {
    public int gameSessionLength;
    public int infoPanelRollTime;
    public int pointsForCorrectAnswer;
    public int pointsForInCorrectAnswer;
    public String defaultArenaResolution;
    @Column(length=1024) 
    public String welcomeText;
    @Column(length=1024)
    public String rulesText;

    public Configuration() {
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
