package engine;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;
import play.test.Fixtures;
import models.Question;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andreas
 */
public class GameSessionTest extends UnitTest {
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
        Fixtures.loadModels("data.yml");
    }
    
    @Test
    public void testRetrieveQuestions() {
        BasicGameSession session = new BasicGameSession();
        List<Question> questions = session.loadQuestions();
        assertNotNull(questions);
        assertEquals(1, questions.size());
        Question q = questions.get(0);
        assertEquals("Who is the father of Java?", q.text);
        assertNotNull(q.answers);
        assertEquals(4, q.answers.size());
    }
}
