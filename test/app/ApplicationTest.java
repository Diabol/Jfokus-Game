package app;

import controllers.Application;
import models.Player;
import org.junit.Test;
import play.test.UnitTest;

/**
 * @author <a href="mailto:tommy.tynja@diabol.se">Tommy Tynj&auml;</a>
 */
public class ApplicationTest extends UnitTest {
    
    @Test
    public void emptyTwitterHandleShouldReturnNull() {
        assertNull(null, Application.formatTwitterHandle(null));
        assertNull(null, Application.formatTwitterHandle(""));
        assertNull(null, Application.formatTwitterHandle(" "));
        assertNull(null, Application.formatTwitterHandle("  "));
    }

    @Test
    public void shouldRemoveDuplicateAtSignsFromTwitterHandle() {
        final String handle = "@tommysdk";
        assertEquals(handle, Application.formatTwitterHandle(handle));
        assertEquals(handle, Application.formatTwitterHandle("@" + handle));
        assertEquals(handle, Application.formatTwitterHandle("@@" + handle));
        assertEquals(handle, Application.formatTwitterHandle(handle + "@"));
        assertEquals(handle, Application.formatTwitterHandle(handle + "@@"));
        assertEquals(handle + "abc", Application.formatTwitterHandle(handle + "@@abc@"));
    }

    @Test
    public void shouldAddAtSignToTwitterHandleWithoutPrefixedAtSign() {
        final String handle = "tommysdk";
        assertEquals("@" + handle, Application.formatTwitterHandle(handle));
    }
    
    @Test
    public void emptyIfNullShouldReturnEmptyStringIfNull() {
        assertEquals("", Application.emptyIfNull(null));
    }
    
    @Test
    public void emptyIfNullShouldReturnParameterIfNonNull() {
        final String string = "arbitraryStringToTestWith:13681113193553.%";
        assertEquals(string, Application.emptyIfNull(string));
    }
    
    @Test
    public void shouldBeConsideredSamePlayer() {
        Player p = new Player("myname", "myemail@domain.org", "sometwitterhandle");
        assertTrue(Application.isSamePlayer(p.name, p.email, p.twitter, p));

        String twitterHandle = "twitterwithATprefix";
        p.twitter = "@" + twitterHandle;
        assertTrue(Application.isSamePlayer(p.name, p.email, twitterHandle, p));

        p.twitter = twitterHandle;
        assertTrue(Application.isSamePlayer(p.name, p.email, "@" + twitterHandle, p));

        p.twitter = null;
        assertTrue(Application.isSamePlayer(p.name.toUpperCase(), p.email.toUpperCase(), "", p));
    }
    
    @Test
    public void shouldNotBeConsideredSamePlayer() {
        Player p = new Player("myname", "myemail@domain.org", null);
        assertFalse(Application.isSamePlayer(p.name + "1", p.email, p.twitter, p));

        assertFalse(Application.isSamePlayer(p.name, p.email + "1", p.twitter, p));

        assertFalse(Application.isSamePlayer(p.name, p.email, "@sometwitterhandle", p));
    }

}
