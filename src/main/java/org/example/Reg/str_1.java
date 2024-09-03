package org.example.Reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class str_1 {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("x");
        Matcher m = p.matcher("hfdhfxzyjhxxjhhfjoioodux");
        while (m.find()){
            System.out.print(m.start() + "" + m.group() + "");
        }


    }
}
