package tictactoe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Output {

    public void print(String state) {
        Pattern pattern = Pattern.compile(".{3}");
        Matcher matcher = pattern.matcher(state);
        System.out.println("---------");
        while (matcher.find()) {
            System.out.println("|" + matcher.group().replaceAll("", " ").replaceAll("_", " ") + "|");
        }
        System.out.println("---------");
    }

}
