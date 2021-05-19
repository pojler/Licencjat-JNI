package com.github.pojler.translator.main;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileIO {

    public List<String> readFile(String filename){
        List<String> lines = new ArrayList<>();
        try{
            File file = new File(filename);
            FileReader fReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fReader);
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            fReader.close();
            reader.close();
        }
        catch (FileNotFoundException ex){
            log.error("File not found");
        }
        catch (IOException ex){
            log.error("Error while reading file");
        }

        return lines;
    }

    public void writeFile(List<String> lines, String filename){
        File file = new File(System.getProperty("user.dir")+"/outputFiles/"+filename);
        try{
            FileWriter writer = new FileWriter(file);
            for(String line : lines){
                writer.write(line);
            }
            writer.close();
        }
        catch(IOException ex){
            log.error("Error while writing to file " + filename);
        }
    }
}
