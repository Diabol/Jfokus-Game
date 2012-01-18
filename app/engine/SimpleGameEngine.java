/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.List;
import java.util.Random;
import models.Player;
import org.apache.log4j.Logger;

/**
 *
 * @author andreas
 */
public class SimpleGameEngine implements GameEngine {
    private static final Logger LOG = Logger.getLogger("Game");
    
    private GameSession gameSession;

    public String registerPlayer(Player player) {
        if (gameSession==null) {
            gameSession = new BasicGameSession();
        }
        gameSession.addPlayer(player);
        LOG.info("Registred player: "+player.name);
        return ""+gameSession.getId().longValue();
    }

    public boolean hasSessionStarted(String gameSessionId) {
        boolean doStart = new Random(System.currentTimeMillis()).nextBoolean();
        if (doStart && gameSession.getPlayers().size()<2) {
            gameSession.addPlayer(Player.getComputer());
        }
        return doStart;
    }

    public List<Player> getPlayers(String gameSessionId) {
        return gameSession.getPlayers();
    }
    
}
