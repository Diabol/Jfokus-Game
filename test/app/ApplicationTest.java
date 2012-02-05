package app;

import controllers.Application;
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

}
