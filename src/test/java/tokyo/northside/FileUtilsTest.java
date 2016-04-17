package tokyo.northside;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.io.File;


/**
 * Created by Hiroshi Miura on 16/04/09.
 */
public class FileUtilsTest {
    @Test
    public void testContentEquals() throws Exception {
        System.out.println("contentEquals");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util1.txt").getFile());
        assertTrue(FileUtils.contentEquals(firstFile, secondFile));
    }

    @Test
    public void testContentEquals_sameCanonicalPath() throws Exception {
        System.out.println("contentEquals with same canonical path");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        assertTrue(FileUtils.contentEquals(firstFile, secondFile));
    }

    @Test
    public void testContentEquals_false() throws Exception {
        System.out.println("contentEquals_false");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        assertFalse(FileUtils.contentEquals(firstFile, secondFile));
    }

    @Test
    public void testContentEquals_range() throws Exception {
        System.out.println("contentEquals_range");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        assertTrue(FileUtils.contentEquals(firstFile, secondFile, 10, 64));
    }

    @Test
    public void testContentEquals_range_false() throws Exception {
        System.out.println("contentEqualsEquals_range_false");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        assertFalse(FileUtils.contentEquals(firstFile, secondFile, 0, 64));
    }

}
