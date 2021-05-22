package com.github.pojler.translator.rules;

import com.github.pojler.translator.Rule;

public class Rules {

    public final static Rule FUNCTION = new Rule("^((public|private|protected|static|final) )*(void|int|long|double|float|boolean|char|String) .+\\(.*\\).*$", str -> {
        return str.replaceAll("(public|private|protected|static|final) ", "");
    });

    public final static Rule CLOSISNG_BRACKET = new Rule("^}$", str -> str );

    public final static Rule FOR = new Rule("^for.*$", str ->{
        if(str.contains(":")){
            return "Foreach";
        }
        return str;
    });

    public final static Rule PRINT = new Rule("^System.out.print\\(.*$", str->
        str
            .replace("System.out.print(", "cout << ")
            .replace("+", "<<")
            .replace(");", ";")
    );
    public final static Rule PRINTLN = new Rule("^System.out.println.*$", str->
        str.replace("System.out.println();", "cout << \"\\n\";")
            .replace("System.out.println(", "cout << ")
            .replace("+", "<<")
            .replace(");", " << \"\\n\";")
    );

    public final static Rule[] getAllRules(){
        return new Rule[]{FUNCTION, CLOSISNG_BRACKET, FOR, PRINT, PRINTLN};
    }

}
