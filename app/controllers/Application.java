package controllers;

import engine.GameEngine;
import java.util.List;
import models.Player;
import play.Logger;
import play.data.validation.*;
import play.i18n.Messages;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void registerPlayer(
            @Required String name,
            @Required @Email String email,
            String twitter,
            @Required @IsTrue boolean termsOfUse) {

        // Handle validation errors
        if (Validation.hasErrors()) {
            Validation.addError("registration", Messages.get("error.mandatoryRegistrationFields"));
            render("@index");
        }
        
        // Check if player is already registered
        Player player = null;
        List<Player> players = Player.find("byEmail", email).fetch();
        if (players.isEmpty()) {
            player = new Player(name, email, twitter);
            Player.em().persist(player);
        } else {
            if (players.size()==1 && isSamePlayer(name,email,twitter, players.get(0))) {
                player = players.get(0);
            } else {
                Validation.addError("registration", Messages.get("error.emailAreadyRegistred"));
                render("@index");
            }
        }
        
        

        // TODO: Make game type selection (e.g. join existing game X, Y or Z)

        // TODO: Register player wihtin a game session etc

        render(name, email, twitter);
    }

    private static boolean isSamePlayer(String name, String email, String twitter, Player player) {
        return name.equals(player.name) && email.equals(player.email) && twitter.equals(player.twitter);
    }
}