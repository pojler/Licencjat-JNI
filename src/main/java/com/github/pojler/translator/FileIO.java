package com.github.pojler.translator;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;




public class FileIO {

    public List<String> readFile(){
        List<String> lines = new ArrayList<>();
        try{
            FileDialog fd = new FileDialog((Frame)null, "Choose a file", FileDialog.LOAD);
            fd.setDirectory("C:\\");
            fd.setFile("*.java");
            fd.setVisible(true);
            String filename = fd.getDirectory() + fd.getFile();
            if (filename == null)
                System.out.println("You cancelled the choice");
            else
                System.out.println("You chose " + filename);
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
            System.err.println("File not found");
        }
        catch (IOException ex){
            System.err.println("Error while reading file");
        }

        return lines;
    }

    public void writeFile(List<String> lines, String filename){
        File file = new File(System.getProperty("user.dir")+"/outputFiles/"+filename+".cpp");
        try{
            FileWriter writer = new FileWriter(file);
            for(String line : lines){
                writer.write(line +"\n");
            }
            writer.close();
        }
        catch(IOException ex){
            System.err.println("Error while writing to file " + filename);
        }
    }
}
