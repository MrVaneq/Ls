package ls;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LsTest {

    @Test
    public void test1() throws IOException {
        Ls test1 = new Ls(true, false, false, "results/result1.txt");
        test1.start("files");
        File expected = new File("expected/expected1.txt");
        File actual = new File("results/result1.txt");
        assertTrue(FileUtils.contentEquals(expected, actual));
    }

    @Test
    public void test2() throws IOException {
        Ls test2 = new Ls(false, true, false, "results/result2.txt");
        test2.start("files");
        File expected = new File("expected/expected2.txt");
        File actual = new File("results/result2.txt");
        assertTrue(FileUtils.contentEquals(expected, actual));
    }

    @Test
    public void test3() throws IOException {
        Ls test3 = new Ls(true, true, true, "results/result3.txt");
        test3.start("files/text1.txt");
        File expected = new File("expected/expected3.txt");
        File actual = new File("results/result3.txt");
        assertTrue(FileUtils.contentEquals(expected, actual));
    }

    @Test
    public void test4() throws IOException {
        Ls test4 = new Ls(true, true, false, "results/result4.txt");
        test4.start("files/documents");
        File expected = new File("expected/expected4.txt");
        File actual = new File("results/result4.txt");
        assertTrue(FileUtils.contentEquals(expected, actual));
    }

    /*
    private void assertEqualsFiles(File expected, File actual) {
        try {
            FileInputStream fis1 = new FileInputStream(expected);
            FileInputStream fis2 = new FileInputStream(actual);
            char current1;
            char current2;
            while (fis1.available() > 0) {
                current1 = (char) fis1.read();
                current2 = (char) fis2.read();
                assert current1 == current2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */
}