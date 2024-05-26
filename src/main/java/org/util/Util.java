package org.util;

public class Util {

    /**
     * converts every word to a title case
     * @param strIn inputs a String strIn
     * @return returns it toTitleCase
     */
    public static String toTitleCase(String strIn) {
        if (strIn == null || strIn.isEmpty()) {
            return strIn;
        }
        String[] words = strIn.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 0) {
                String titleCaseWord = Character.toTitleCase(word.charAt(0)) +
                        word.substring(1).toLowerCase();
                result += titleCaseWord;
                if (i < words.length - 1) {
                    result += " ";
                }
            }
        }
        return result;
    }
}
