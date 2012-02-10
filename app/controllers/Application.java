package controllers;

import engine.GameEngine;
import java.util.List;
import javax.inject.Inject;
import models.Player;
import play.Logger;
import play.data.validation.*;
import play.i18n.Messages;
import play.mvc.Controller;

public class Application extends Controller {
    
    @Inject
    static GameEngine gameEngine;

    public static void index() {
        render();
    }
    
    public static void mock(){
        Player player = null;
        List<Player> players = Player.find("byEmail", "mockedMail").fetch();
        String name = "Test player";
        String email = "test@test.com";
        String twitter = "twitter";
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
        String gameSessionId = gameEngine.registerPlayer(player);
        gameEngine.registerPlayer(player);
        session.put("gameSessionId", gameSessionId);
        session.put("playerId", player.id);
        redirect("/Game/waitForStart");
    }

    public static void registerPlayer (
            @Required String name,
            @Required @Email String email,
            @Required String twitter,
            @Required @IsTrue boolean termsOfUse) {

        name = name.toLowerCase();
        email = email.toLowerCase();
        twitter = twitter.toLowerCase();

        // Handle validation errors
        if (Validation.hasErrors()) {
            Validation.addError("registration", Messages.get("error.mandatoryRegistrationFields"));
            render("@index");
        }
        // Check if player is already registered
        Player player = null;
        List<Player> players = Player.find("byEmail", email.toLowerCase()).fetch();
        if (players.isEmpty()) {
            player = new Player(name, email.toLowerCase(), formatTwitterHandle(twitter));
            Player.em().persist(player);
        } else {
            if (players.size() == 1 && isSamePlayer(name, email, twitter, players.get(0))) {
                player = players.get(0);
            } else {
                Validation.addError("registration", Messages.get("error.emailAreadyRegistred"));
                render("@index");
                return;
            }
        }
        String gameSessionId = gameEngine.registerPlayer(player);
        session.put("gameSessionId", gameSessionId);
        session.put("playerId", player.id);
        redirect("/Game/waitForStart");
    }

    public static String formatTwitterHandle(String handle) {
        if (handle == null || handle.trim().equals("")) return null;
        handle = handle.replace("@", "");
        return "@" + handle;
    }
    
    public static String emptyIfNull(final String string) {
        if (string == null) return "";
        else return string;
    }

    public static boolean isSamePlayer(String name, String email, String twitter, Player player) {
        final String vname = emptyIfNull(name);
        final String vemail = emptyIfNull(email);
        final String vtwitter = emptyIfNull(formatTwitterHandle(twitter));
        final String ptwitter = emptyIfNull(formatTwitterHandle(player.twitter));
        
        return vname.equalsIgnoreCase(player.name) && vemail.equalsIgnoreCase(player.email) && vtwitter.equalsIgnoreCase(ptwitter);
    }
}