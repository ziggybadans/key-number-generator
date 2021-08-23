package com.ziggybadans.keynumbergenerator;

/**
 * A class used for the concatenation methods {@code setYear} and {@code setClientI} in {@code KeyNumberGenerator}.
 * @see KeyNumberGenerator
 */
public class CharConcatenation {
    public String concat(char... chars) {
        if (chars.length == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder(chars.length);
        for (char c : chars) {
            s.append(c);
        }
        return s.toString();
    }
}
