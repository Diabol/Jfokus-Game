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
    public String firstName;
    public String lastName;
    public String email;
    public String twitter;

    public Player() {
    }
    
    public Player(final String firstName, final String lastName, final String email, final String twitterHandle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.twitter = twitterHandle;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
