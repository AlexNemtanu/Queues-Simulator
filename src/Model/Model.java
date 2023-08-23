package Model;

import java.util.regex.Pattern;

public class Model {
    // This method checks if a string is valid by verifying if it contains only digits
    // If the input string is null, empty or contains only whitespaces, it returns -1
    // If the input string contains only digits, it returns 0
    // Otherwise, it returns -1
    public int validator(String s) {
        if (s == null || s.trim().isEmpty()) {
            return -1;
        }

        Pattern pattern = Pattern.compile("\\d*");
        if (pattern.matcher(s).matches()) {
            return 0;
        } else {
            return -1;
        }
    }
}
