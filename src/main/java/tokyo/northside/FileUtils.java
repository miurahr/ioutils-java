/*
 * FileUtils library.
 *
 * Copyright (C) 2016 Hiroshi Miura
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
  *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */

package tokyo.northside;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;


/**
 * General IO stream manipulation utility.
 * <p>
 * This class provides static utility methods for input/output operations.
 * <ul>
 * <li>contentEquals - these methods compare the content of two streams
 * </ul>
 * <p>
 * All the methods in this class that read a stream are buffered internally.
 * This means that there is no cause to use a <code>BufferedInputStream</code>
 * or <code>BufferedReader</code>. The default buffer size of 4K has been shown
 * to be efficient in tests.
 * <p>
 * Wherever possible, the methods in this class do <em>not</em> flush or close
 * the stream. This is to avoid making non-portable assumptions about the
 * streams' origin and further use. Thus the caller is still responsible for
 * closing streams after use.
 * <p>
 * Created by Hiroshi Miura on 16/04/09.
 */
public final class FileUtils {

    private static final int EOF = -1;
    private static final int BUF_LEN = 4096;

    /**
     * Compare files. Both files must be files (not directories) and exist.
     *
     * @param first  - first file
     * @param second - second file
     * @return boolean - true if files are binery equal
     * @throws IOException - error in function
     */
    static boolean contentEquals(final File first, final File second) throws IOException {
        return contentEquals(first, second, 0, first.length());
   }

    /**
     * Compare binary files (for test). Both files must be files (not directories) and exist.
     *
     * @param first  - first file
     * @param second - second file
     * @param off    - compare from offset
     * @param len    - comparison length
     * @return boolean - true if files are binery equal
     * @throws IOException - error in function
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
                BufferedInputStream bufFirstInput = null;
                BufferedInputStream bufSecondInput = null;

                try {
                    firstInput = new FileInputStream(first);
                    secondInput = new FileInputStream(second);
                    bufFirstInput = new BufferedInputStream(firstInput, BUF_LEN);
                    bufSecondInput = new BufferedInputStream(secondInput, BUF_LEN);

                    byte[] firstBytes = new byte[BUF_LEN];
                    byte[] secondBytes = new byte[BUF_LEN];

                    if (off > 0) {
                        long totalSkipped = 0;
                        while (totalSkipped < off) {
                            long skipped = bufFirstInput.skip(off - totalSkipped);
                            if (skipped == 0) {
                                throw new IOException("Cannot seek offset bytes.");
                            }
                            totalSkipped += skipped;
                        }
                        totalSkipped = 0;
                        while (totalSkipped < off) {
                            long skipped = bufSecondInput.skip(off - totalSkipped);
                            if (skipped == 0) {
                                throw new IOException("Cannot seek offset bytes.");
                            }
                            totalSkipped += skipped;
                        }
                    }


                    long readLengthTotal = 0;
                    result = true;
                    while (readLengthTotal < len) {
                        int readLength = BUF_LEN;
                        if (len - readLengthTotal < (long) BUF_LEN) {
                            readLength = (int) (len - readLengthTotal);
                        }
                        int lenFirst = bufFirstInput.read(firstBytes, 0, readLength);
                        int lenSecond = bufSecondInput.read(secondBytes, 0, readLength);
                        if (lenFirst != lenSecond) {
                            result = false;
                            break;
                        }
                        if ((lenFirst < 0) && (lenSecond < 0)) {
                            result = true;
                            break;
                        }
                        readLengthTotal += lenFirst;
                        if (lenFirst < firstBytes.length) {
                            byte[] a = Arrays.copyOfRange(firstBytes, 0, lenFirst);
                            byte[] b = Arrays.copyOfRange(secondBytes, 0, lenSecond);
                            if (!Arrays.equals(a, b)) {
                                result = false;
                                break;
                            }
                        } else if (!Arrays.equals(firstBytes, secondBytes)) {
                            result = false;
                            break;
                        }
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (IOException ioe) {
                     throw ioe;
                } finally {
                     IOUtils.closeQuietly(bufFirstInput);
                     IOUtils.closeQuietly(firstInput);
                     IOUtils.closeQuietly(bufSecondInput);
                     IOUtils.closeQuietly(secondInput);
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
