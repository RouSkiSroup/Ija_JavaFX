package FinalProject.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ReadFile {
    private BufferedReader reader;

    ReadFile(String file){
        try {
            this.reader = new BufferedReader(new FileReader(file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

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
