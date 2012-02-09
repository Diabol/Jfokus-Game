package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import play.mvc.Controller;

/**
 *
 * @author andreas
 */
public class Info extends Controller {
    
    public static final List<String> actions = new ArrayList<String>(Arrays.asList(new String[]{"comeAndPlay","rules","topScores"}));

    public static void index(String rollTime) {
        if (rollTime==null) {
            rollTime = "10";
        }
        int numberOfActions = actions.size();
        render(rollTime,numberOfActions);
    }
    
    public static void roll(int actionIndex) {
        if (actionIndex < actions.size()) {
           String nextMethod = actions.get(actionIndex);
            if ("comeAndPlay".equals(nextMethod)) {
                comeAndPlay();
            } else if ("rules".equals(nextMethod)) {
                rules();
            } else if ("topScores".equals(nextMethod)) {
                topScores();
            } 
        }
    }
    
    public static void comeAndPlay() {
        render();
    }
    
    public static void rules() {
        render();
    }
    
    public static void topScores() {
        Scoreboard.list(20);
    }
    
}
