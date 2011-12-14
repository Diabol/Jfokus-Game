package controllers;

import play.data.validation.*;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void handleSubmit( // TODO: Rename to register player (cascade into html/routes etc)
            @Required String firstname,
            @Required String lastname,
            @Required @Email String email,
            @Required @Equals("email") String emailConfirm,
            @Required String twitter,
            @Required @IsTrue boolean termsOfUse) {

        // Handle errors
        if (Validation.hasErrors()) {
            render("@index");
        }

        // TODO: Persist user details

        // TODO: Make game type selection (e.g. join existing game X, Y or Z)

        // TODO: Register player wihtin a game session etc

        render(firstname, lastname, email, twitter);
    }
}