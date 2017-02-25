/**
 * File: Deserializer.java
 * Author: Cesar Lopez
 * Date: October 8, 2016
 * Purpose: class parses data in file
 */
package calculatorgui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Deserializer {

    File file;

    /**
     * constructor with no parameters.
     */
    public Deserializer() {

        file = new File("");

    }// End of Deserializer no parameter constructor.

    /**
     * fully reads the file
     *
     * @param file
     * @return a string with all the contents of the file
     * @throws IOException
     */
    public String readAll(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            StringBuilder build = new StringBuilder();
            char[] buffer = new char[256];
            while (true) {
                int charRead = reader.read(buffer);
                if (charRead == -1) {
                    break;
                }
                build.append(buffer, 0, charRead);
            }
            return build.toString();
        }
    }//End readAll method
}
