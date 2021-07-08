package com.github.pojler.translator;

import com.github.pojler.translator.rules.CppRules;
import com.github.pojler.translator.rules.JniRules;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private FileIO fileIO = new FileIO();
    private List<String> lines;
    private Rule[] jniRuleSet;
    private Rule[] cppRuleSet;

    public Engine(){
        jniRuleSet = JniRules.getAllRules();
        cppRuleSet = CppRules.getAllRules();
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
        List<String> jniOutput = new ArrayList<>();
        List<String> cppOutput = new ArrayList<>();
        int level = 0;
        for (String line : lines) {
            String jniParsed = parseJniUnit(line.trim());
            String cppParsed = parseCppUnit(line.trim());
            if (line.trim().equals("}")) {
                level--;
            }
            if(level > 0) {
                //System.out.println(generateIndent(level-1) + jniParsed);
                 jniOutput.add(generateIndent(level-1) + jniParsed);
                 cppOutput.add(generateIndent(level-1) + cppParsed);
            }
            int bracket = line.indexOf('{');
            if (bracket > -1 &&bracket == line.length()-1) {
                level++;
            }
        }
        fileIO.writeFile(jniOutput, "jni");
        fileIO.writeFile(cppOutput, "c++");

    }

    private String parseJniUnit(String line) {
        for(Rule rule: jniRuleSet){
            if(rule.test(line)){
                return rule.parse(line);
            }
        }
        return "#ERR#";
    }

    private String parseCppUnit(String line) {
        for(Rule rule: cppRuleSet){
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
