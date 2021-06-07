package com.github.pojler.translator.rules;

import com.github.pojler.translator.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rules {

    public final static Rule FUNCTION = new Rule("^((public|private|protected|static|final) )*(void|int|long|double|float|boolean|char|String) .+\\(.*\\).*$", str -> {
        return str.replaceAll("(public|private|protected|static|final) ", "")
                .replaceAll("String", "string");
    });

    public final static Rule VARIABLE = new Rule("^(int|long|double|float|boolean|char|String) .*$", str -> str
            .replaceAll("boolean", "bool")
            .replaceAll("String", "std::string")
    );

    public final static Rule ARRAY  = new Rule("^.*\\[\\].*$", str -> {
        String pattern = "(.*)(\\[\\]) ([A-Za-z]*)( = )?(.*)";
        StringBuilder result = new StringBuilder();

        Pattern pat = Pattern.compile(pattern);
        Matcher m = pat.matcher(str);
        if(m.find()) {
            StringBuilder sb = new StringBuilder();
            Pattern fullyInitialized = Pattern.compile("^\\{.*};$");
            Pattern initialized = Pattern.compile("^new .*$");
            Matcher fullyInit  = fullyInitialized.matcher(m.group(5));
            Matcher init  = initialized.matcher(m.group(5));
            if(fullyInit.matches()){
                result.append("std::array<");
                String type = m.group(1);
                type = type.replaceAll("String", "std::string").replaceAll("boolean", "bool");
                result.append(type).append(",");
                int count = 0;
                for (int i = 0; i < m.group(5).length(); i++) {
                    if(m.group(5).charAt(i) == ','){
                        count++;
                    }
                }
                result.append(count).append("> ").append(m.group(3)).append(" = ").append(m.group(5));
                return result.toString();
            }
            else if(init.matches()){
                result.append("std::array<");
                String type = m.group(1);
                type = type.replaceAll("String", "std::string").replaceAll("boolean", "bool");
                result.append(type).append(",");
                String sizeInfo = m.group(5);
                sizeInfo = sizeInfo.substring(sizeInfo.indexOf("[")+1);
                sizeInfo = sizeInfo.substring(0, sizeInfo.indexOf(("]")));
                result.append(sizeInfo).append("> ").append(m.group(3)).append(";");
                return result.toString();
            }
            else{
                result.append("std::vector <");
                String type = m.group(1);
                type = type.replaceAll("String", "std::string").replaceAll("boolean", "bool");
                result.append(type).append("> ").append(m.group(3)).append(";");
                return result.toString();
            }

        }
        else{
            return "no match";
        }
    });

    public final static Rule CLOSING_BRACKET = new Rule("^}$", str -> str);

    public final static Rule FOR = new Rule("^for.*$", str -> {
        if (str.contains(":")) {
            String pattern = "(for\\()([A-Za-z]* )([A-Za-z]*)(:)([A-Za-z]*)\\)";
            Pattern pat = Pattern.compile(pattern);
            Matcher m = pat.matcher(str);
            if(m.find()){
                return "for(int i = 0; i <" + m.group(5)+".length(); i++) { //you may want to edit inside of this for statement";
            }
        }
        return str;
    });

    public static final Rule IF = new Rule ("^if\\(.*$", str -> str);
    public static final Rule ELSE = new Rule ("^else\\.*$", str -> str);
    public static final Rule ELSE_IF = new Rule ("^else if\\(.*$", str -> str);


    public final static Rule PRINT = new Rule("^System.out.print\\(.*$", str ->
            str
                    .replace("System.out.print(", "cout << ")
                    .replace("+", "<<")
                    .replace(");", ";")
    );
    public final static Rule PRINTLN = new Rule("^System.out.println.*$", str ->
            str.replace("System.out.println();", "cout << \"\\n\";")
                    .replace("System.out.println(", "cout << ")
                    .replace("+", "<<")
                    .replace(");", " << \"\\n\";")
    );

    public static final Rule EMPTY_LINE = new Rule ("^$", str -> str);

    public final static Rule[] getAllRules() {
        return new Rule[]{FUNCTION, VARIABLE, ARRAY, CLOSING_BRACKET, FOR, PRINT, PRINTLN, EMPTY_LINE, IF, ELSE, ELSE_IF};
    }

}
