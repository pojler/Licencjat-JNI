package com.github.pojler.translator;

public class Array {
     String name;
     String type;
     String values;
     boolean initialized;
     boolean isEmpty;
     int size;

    Array (String code){

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(initialized){
            if(isEmpty){
                sb.append(type).append("* ").append(name).append(" = ").append("new ")
                        .append(type).append("[").append(size).append("];");

            }
            else{
                sb.append(type).append("[] ").append(name).append(" = ").append(values).append(";");
            }

        }
        else{
            sb.append(type+"[] ").append("[] ").append(name).append(";");
        }
        return sb.toString();
    }

}
