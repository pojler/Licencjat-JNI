package com.github.pojler.translator;

import com.github.pojler.translator.rules.Rules;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private FileIO fileIO = new FileIO();
    private List<String> lines;
    private Rule[] ruleSet;

    public Engine(){
        ruleSet = Rules.getAllRules();
    }

    public void readFile() {
        lines = fileIO.readFile();
    }

    public void displayFile() {

        for (String line : lines) {
            System.out.println(line);
        }

    }

    private void parse() {
        List<String> output = new ArrayList<>();
        int level = 0;
        for (String line : lines) {
            String parsed = parseUnit(line.trim());
            if (line.trim().equals("}")) {
                level--;
            }
            if(level > 0) {
                System.out.println(generateIndent(level-1) + parsed);
                 output.add(generateIndent(level-1) + parsed);
            }
            int bracket = line.indexOf('{');
            if (bracket > -1 &&bracket == line.length()-1) {
                level++;
            }
        }
        fileIO.writeFile(output, "file1");

    }

    private String parseUnit(String line) {
        for(Rule rule: ruleSet){
            if(rule.test(line)){
                return rule.parse(line);
            }
        }
        return "#ERR#";
    }

    private String generateIndent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        Engine engine = new Engine();
        engine.readFile();
        //engine.displayFile();
        engine.parse();
        System.exit(0);

    }

}
