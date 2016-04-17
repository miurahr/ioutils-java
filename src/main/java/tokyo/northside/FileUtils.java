/*
 * FileUtils library.
 *
 * Copyright (C) 2016 Hiroshi Miura
 *
 */

package tokyo.northside;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * General File manipulation utility.
 * <p>
 * This class provides static utility methods for input/output operations.
 * <ul>
 * <li>contentEquals - these methods compare the content of two files
 * </ul>
 * <p>
 * The methods in this class that read a file are buffered internally.
 * The default buffer size of 4K has been shown to be efficient in tests.
 * <p>
 * Created by Hiroshi Miura on 16/04/09.
 *
 * @author Hiroshi Miura
 */
public final class FileUtils {

    /**
     * Compare file contents in range. Both files must be files (not directories) and exist.
     *
     * @param first   first file
     * @param second  second file
     * @param off     compare from offset
     * @param len     comparison length
     * @return boolean  true if files are equal, otherwise false
     * @throws IOException  error in function
     */
    static boolean contentEquals(final File first, final File second, final long off, final long len)
            throws IOException {
        boolean result = false;

        if (len < 1) {
            throw new IllegalArgumentException();
        }
        if (off < 0) {
            throw new IllegalArgumentException();
        }

        if ((first.exists()) && (second.exists())
                && (first.isFile()) && (second.isFile())) {
            if (first.getCanonicalPath().equals(second.getCanonicalPath())) {
                result = true;
            } else {
                FileInputStream firstInput = null;
                FileInputStream secondInput = null;

                try {
                    firstInput = new FileInputStream(first);
                    secondInput = new FileInputStream(second);
                    result = IOUtils.contentEquals(firstInput, secondInput, off, len);
                } catch (RuntimeException e) {
                    throw e;
                } catch (IOException ioe) {
                     throw ioe;
                } finally {
                     org.apache.commons.io.IOUtils.closeQuietly(firstInput);
                     org.apache.commons.io.IOUtils.closeQuietly(secondInput);
                }
            }
        }
        return result;
    }

    /**
     * Static utility should not be instantiated.
     */
    private FileUtils() {
    }
}
