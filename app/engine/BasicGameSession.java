package engine;

import java.util.ArrayList;
import models.Question;
import models.Player;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import models.GameRound;

/**
 * @author <a href="mailto:tommy@diabol.se">Tommy Tynj&auml;</a>
 */
public class BasicGameSession implements GameSession {

    private static final Logger LOG = Logger.getLogger("GameSession");
    private final Set<PlayerSession> playerSessions = new LinkedHashSet<PlayerSession>();
    private GameRound gameRound;

    public BasicGameSession() {
        gameRound = new GameRound(null, null, 180);
        GameRound.em().persist(gameRound);
    }

    @Override
    public Long getId() {
        return gameRound.getId();
    }

    public List<Question> loadQuestions() {
        List<Question> questions = models.Question.findAll();
        Random rnd = new Random();
        List<Question> randomList = new ArrayList<Question>(10);
        int size = Math.min(10, questions.size()); // if there are less than 10 questions in the db
        for (int i = 0; i < size; i++){
            randomList.add(questions.get(rnd.nextInt(size)));
        }
        return randomList;
    }

    @Override
    public void addPlayer(final Player player) {
        PlayerSession newPlayerSession = new BasicPlayerSession(player);
        playerSessions.add(newPlayerSession);
        LOG.info("Successfully add player: " + player + " with session: " + newPlayerSession);
    }

    public List<Player> getPlayers() {
        List<Player> playerList = new LinkedList<Player>();
        for (PlayerSession playerSession : playerSessions) {
            playerList.add(playerSession.getPlayer());
        }
        return playerList;
    }
    
    @Override
    public void start() {
    }

    @Override
    public void handleAnswer(final Integer questionId, final Integer answerId) {
    }

    @Override
    public Scoreboard getScore() {
        return null;
    }
}
