package models;

import javax.persistence.*;
 
import play.db.jpa.*;


/**
 * Represents a participant in a GameRound
 * 
 * @author andreas
 */
@Entity
public class Player extends Model {
    public String name;
    public String email;
    public String twitter;

    public static Player getComputer() {
        return find("byEmail", "computer@diabol.se").first();
    }
    
    public Player() {
    }
    
    public Player(final String name, final String email, final String twitterHandle) {
        this.name = name;
        this.email = email;
        this.twitter = twitterHandle;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
