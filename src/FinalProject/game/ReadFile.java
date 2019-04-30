package FinalProject.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading file.
 */
class ReadFile {
    private BufferedReader reader;

    /**
     * Constructor
     * @param file  File to read.
     */
    ReadFile(String file){
        try {
            this.reader = new BufferedReader(new FileReader(file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads one line from file and returns it as a string.
     * @return  Returns one line of file.
     */
    String getLine(){
        try {
            String line = reader.readLine();
            if (line != null) {
                return line;
            }
            reader.close();
            return null;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
