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
    public String email;
    public String nic;

    public Player(String email, String nic) {
        this.email = email;
        this.nic = nic;
    }

}
