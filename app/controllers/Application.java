package controllers;

import play.data.validation.*;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void handleSubmit(
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

        render(firstname, lastname, email, twitter);
    }
}