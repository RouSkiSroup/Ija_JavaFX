package FinalProject.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    BufferedReader reader;

    public ReadFile(String file){
        try {
            this.reader = new BufferedReader(new FileReader(
                    file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLine(){
        try {
            String line = reader.readLine();
            if (line != null) {
                //System.out.println(line);
                // read next line
                //line = reader.readLine();
                return line;
            }
            reader.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
