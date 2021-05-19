package com.github.pojler.translator.main;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
public class TextProcessor {

    public void process(String inputFile, String outputFile){

        Map<String, String> replacements = getReplacementsMap();
        FileIO io = new FileIO();
        List<String> lines = io.readFile(inputFile);
        for (String line: lines) {
            String []words = line.split(" ");
            for(int i = 0; i< words.length; i++){
                if(replacements.containsKey(words[i])){
                    words[i] = replacements.get(words[i]);
                }
            }
           line = String.join(" ", words);
        }
        io.writeFile(lines, outputFile);

    }


    public Map<String, String> getReplacementsMap(){

        Map<String, String> replacements = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File replacementsFile = new File(classLoader.getResource("replacements.txt").getFile());
        try{
            FileReader reader = new FileReader(replacementsFile);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while((line = br.readLine()) != null){
                String [] tempReplacements = line.split(" ");
                replacements.put(tempReplacements[0], tempReplacements[1]);
            }
            br.close();
            reader.close();

        }
        catch(FileNotFoundException ex){
            log.error("Replacements file missing");
        }
        catch(IOException ex){
            log.error("Error while reading replacements file");
        }

        return replacements;
    }


}
