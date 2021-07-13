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
    private void addIncludes(List<String> outputList){
        outputList.add("#include<iostream>");
        outputList.add("#include<chrono>");
        outputList.add("#include<math.h>");
        outputList.add("#include <array>");
        outputList.add("");
        outputList.add("#define START(timer) timer = std::chrono::steady_clock::now()");
        outputList.add("#define ITV(start, stop) std::chrono::duration_cast<std::chrono::nanoseconds> (stop - start).count() / 1000000.0");
        outputList.add("#define MEASURE(timer) ITV(timer, std::chrono::steady_clock::now())");
    }

    private void parse() {
        List<String> jniOutput = new ArrayList<>();
        List<String> cppOutput = new ArrayList<>();
        addIncludes(jniOutput);
        addIncludes(cppOutput);
        int level = 0;
        for (String line : lines) {
            if(!line.equals("public static void main(String[] args) {") || !line.trim().equals("public static void main(String[] args){")) {
                String jniParsed = parseJniUnit(line.trim());
                String cppParsed = parseCppUnit(line.trim());
                if (line.trim().equals("}")) {
                    level--;
                }
                if (level > 0) {
                    //System.out.println(generateIndent(level-1) + jniParsed);
                    jniOutput.add(generateIndent(level - 1) + jniParsed);
                    cppOutput.add(generateIndent(level - 1) + cppParsed);
                }
                int bracket = line.indexOf('{');
                if (bracket > -1 && bracket == line.length() - 1) {
                    level++;
                }
            }
            else{
                addMain(cppOutput);
                continue;
            }
        }
        fileIO.writeFile(jniOutput, "jni");
        fileIO.writeFile(cppOutput, "c++");

    }

    private void addMain(List<String> output){
        output.add("int main(){");
        output.add("    START(auto code_timer);");
        output.add("    //use wrtitten function here");
        output.add("    auto time = MEASURE(code_timer);");
        output.add("    std::cout << \"Elapsed time in miliseconds : \"<< time<< \" ms\";");
        output.add("    return 0;");
        output.add("}");
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
