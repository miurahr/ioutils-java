package tokyo.northside.io;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * Created by miurahr on 16/04/17.
 */
public class IOUtilsTest {

    @Test (groups="stream")
    public void testContentEquals() throws Exception {
        System.out.println("contentEquals");
        File firstFile =  new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util1.txt").getFile());
        InputStream firstInput = new FileInputStream(firstFile);
        InputStream secondInput = new FileInputStream(secondFile);
        assertTrue(IOUtils.contentEquals(firstInput, secondInput, 0, firstFile.length()));
    }

    @Test (groups="stream")
    public void testContentEquals_same() throws Exception {
        System.out.println("contentEquals");
        File firstFile =  new File(this.getClass().getResource("/test_util.txt").getFile());
        InputStream firstInput = new FileInputStream(firstFile);
        assertFalse(IOUtils.contentEquals(firstInput, firstInput, 0, firstFile.length()));
    }

    @Test (groups="stream")
    public void testContentEquals_false() throws Exception {
        System.out.println("contentEquals");
        File firstFile =  new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        InputStream firstInput = new FileInputStream(firstFile);
        InputStream secondInput = new FileInputStream(secondFile);
        assertFalse(IOUtils.contentEquals(firstInput, secondInput, 0, firstFile.length()));
    }

    @Test (groups="stream")
    public void testContentEquals_range() throws Exception {
        System.out.println("contentEquals_range");
        File firstFile = new File(this.getClass().getResource("/test_util.txt").getFile());
        File secondFile = new File(this.getClass().getResource("/test_util2.txt").getFile());
        InputStream firstInput = new FileInputStream(firstFile);
        InputStream secondInput = new FileInputStream(secondFile);
        assertTrue(IOUtils.contentEquals(firstInput, secondInput, 10, 64));
    }

}
