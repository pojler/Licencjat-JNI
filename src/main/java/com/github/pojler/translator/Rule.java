package com.github.pojler.translator;

import com.github.pojler.translator.rules.IParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

    private Pattern pattern;
    private IParser parser;

    public Rule(String pattern, IParser parser){
        this.pattern = Pattern.compile(pattern);
        this.parser = parser;
    }

    public boolean test(String line){
        return pattern.matcher(line).matches();
    }

    public String parse(String line){
        return parser.parse(line);
    }


}
