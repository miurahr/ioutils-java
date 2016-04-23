package tokyo.northside.io;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.io.File;


/**
 * Created by Hiroshi Miura on 16/04/09.
 */
public class FileUtils2Test {

    @Test (groups="file")
    public void testContentEquals_range() throws Exception {
        System.out.println("contentEquals_range");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        assertTrue(FileUtils2.contentEquals(firstFile, secondFile, 10, 64));
    }

    @Test (groups="file")
    public void testContentEquals_range_false() throws Exception {
        System.out.println("contentEqualsEquals_range_false");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        assertFalse(FileUtils2.contentEquals(firstFile, secondFile, 0, 64));
    }
}
