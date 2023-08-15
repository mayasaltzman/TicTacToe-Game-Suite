package boardgame;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

/**
This class manages the file input and output
 @author Maya Saltzman
*/
public class SaveToFile{
    /**
     save: allows user to save their game to a file of their choice
     in: Saveable, String, String
     out: void
     */
    public static void save(Saveable toSave, String filename, String relativePath){
        try{
           Path path = FileSystems.getDefault().getPath(relativePath,filename);
           try{
              Files.writeString(path, toSave.getStringToSave());
           }catch(IOException e){
            throw(new RuntimeException(e.getMessage()));
           } 
        }catch(NullPointerException e){
        }
    }

    /**
    load: allows user to continue a saved game
     in: Saveable, String, String
     out: void
     */
    public static void load(Saveable toLoad, String filename, String relativePath){
      try{
        Path path = FileSystems.getDefault().getPath(relativePath,filename);
        try{
            String fileLines = String.join("\n",Files.readAllLines(path)); 
            toLoad.loadSavedString(fileLines);
        }catch(IOException e){
        } 
      }catch(NullPointerException e){
      }
    }

    /**
    savePlayerProfile: user can save their updated player profile statistics to a file of their choice
    in: String, String
    out: void
     */
    public static void savePlayerProfile(String fileName, String toWrite){
        try{
          BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
          writer.write(toWrite);
          writer.close();
        }catch(IOException e){
        } 
    }

    /**
    loadFromPlayerProfile: returns string that holds the contents of the player profile read from the file
    in: String
    out: String
     */
    public static String loadFromPlayerProfile(String fileName){
        String str = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            str = reader.readLine();
            reader.close();
        }catch(IOException e){
        }
        return str;
    }
        

}

