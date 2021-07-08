package com.github.pojler.translator.rules;

import com.github.pojler.translator.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CppRules implements Rules {

    private static final  String KEYWORD = "((public|private|protected|static|final) )";
    private static final  String TYPE = "(void|int|long|double|float|boolean|char|String|int\\[]|long\\[]|double\\[]|float\\[]|boolean\\[]|char\\[]|String\\[])";
    private static final  String TOKEN = "([a-z][A-Za-z]*)";
    private static final  String PARAMS = "(\\(.*\\))";


    public final static Rule FUNCTION = new Rule("^((public|private|protected|static|final) )*(void|int|long|double|float|boolean|char|String|int\\[\\]|long\\[\\]|double\\[\\]|float\\[\\]|boolean\\[\\]|char\\[\\]|String\\[\\]) .+\\(.*\\).*\\{$", str -> {
        System.out.println(str);
        String pattern = KEYWORD+"*"+TYPE+" "+TOKEN+PARAMS;
        System.out.println(pattern);
        Pattern patt = Pattern.compile(pattern);
        Matcher m = patt.matcher(str);
        if(m.find()){
            if(m.group(3).charAt(m.group(3).length()-1) == ']'){
                StringBuilder sb = new StringBuilder();
                sb.append("std:array<");
                sb.append(m.group(3).substring(0,m.group(3).length()-2));
                sb.append(", N> ");
                sb.append(m.group(4));
                sb.append(m.group(5));
                sb.append("{");
                return sb.toString().replaceAll("String", "std::string")
                        .replaceAll("boolean", "bool");
            }
        }
        return str.replaceAll("(public|private|protected|static|final) ", "")
                .replaceAll("String", "std::string")
                .replaceAll("boolean", "bool");
    });

    public final static Rule VARIABLE = new Rule("^(int|long|double|float|boolean|char|String) .*$", str -> str
            .replaceAll("boolean", "bool")
            .replaceAll("String", "std::string")
    );

    public final static Rule GETARRAYVALUE = new Rule("^.*\\[(([0-9]*)|([a-z]))] = .*$", str -> {
        String p = "(.*)\\[(.*)] = (.*;)";
        Pattern pattern = Pattern.compile(p);
        Matcher m = pattern.matcher(str);
        if(m.find()){
            StringBuilder sb = new StringBuilder();
            sb.append(m.group(1));
            sb.append(".at(");
            sb.append(m.group(2)).append(") = ");
            sb.append(m.group(3));
            return sb.toString();
        }
        System.out.println(str);
        return str
                .replaceAll("boolean", "bool")
                .replaceAll("String", "std::string");
    });


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
                int count = 1;
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
                result.append("std::array <");
                String type = m.group(1);
                type = type.replaceAll("String", "std::string").replaceAll("boolean", "bool");
                result.append(type).append(",N").append("> ").append(m.group(3)).append(";");
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
                return "for( auto " + m.group(3) + " = " + m.group(5) + ".begin(); " + m.group(3) + " != " +
                        m.group(5)+ ".end();" + " ++" + m.group(3) +" ) {";
            }
        }
        return str;
    });

    public static final Rule IF = new Rule ("^if\\(.*$", str ->{
        String p = "if\\((.*)\\[(.*)] (.*) (.*)\\)";
        Pattern pattern = Pattern.compile(p);
        Matcher m = pattern.matcher(str);
        if(m.find()){
            StringBuilder sb = new StringBuilder();
            sb.append("if(").append(m.group(1)).append(".at(").append(m.group(2)).append(") ").append(m.group(3)).append(" ").append(m.group(4)).append(";");
            return sb.toString();
        }
        return str;
    } );
    public static final Rule ELSE = new Rule ("^else.*$", str -> str);
    public static final Rule ELSE_IF = new Rule ("^else if\\(.*$", str -> {
        String p = "else if\\((.*)\\[(.*)] (.*) (.*)\\)";
        Pattern pattern = Pattern.compile(p);
        Matcher m = pattern.matcher(str);
        if(m.find()){
            StringBuilder sb = new StringBuilder();
            sb.append("else if(").append(m.group(1)).append(".at(").append(m.group(2)).append(") ").append(m.group(3)).append(" ").append(m.group(4)).append(";");
            return sb.toString();
        }
        return str;
    });


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
    public static final Rule RETURN= new Rule ("^return .*$", str -> str);


    public final static Rule[] getAllRules() {
        return new Rule[]{FUNCTION, VARIABLE, ARRAY, CLOSING_BRACKET, FOR, PRINT, PRINTLN, EMPTY_LINE, IF, ELSE, ELSE_IF, RETURN, GETARRAYVALUE};
    }

}
