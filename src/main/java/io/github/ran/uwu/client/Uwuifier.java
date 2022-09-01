package io.github.ran.uwu.client;

import java.util.Random;

/**
 * This class either contains the best code or the worst code ever written
 * @author Ran
 * @author TreeCaptcha
 * @author RoostersEatYou
 */
public class Uwuifier {
    /**
     * Should uwuifyMessage have the same output for every input?
     */
    public static boolean USE_PREVIEW = false;

    public static String uwu(String stringToUwuify) {
        if (stringToUwuify.equals(UwUMod.prevUwuifiedMessage)) stringToUwuify = UwUMod.prevMessage;
        if (stringToUwuify.endsWith(" >_<")) stringToUwuify = stringToUwuify.substring(0, stringToUwuify.length() - 4);
        return UwUMod.prevUwuifiedMessage = (UwUMod.prevMessage = stringToUwuify).toLowerCase().replaceAll("[rl]","w").replaceAll("n([aeiou])", "ny$1").replaceAll("ove", "uve").replaceAll("uck", "uwq").replaceFirst("i", "i-i").replaceFirst("(?s)(.*)" + "i-i-i", "$1" + "i-i") + ((new Random().nextInt(10)) <= 2 ? " >_<" : ""); // Wanted to put >~< but it doesn't look good in minecraft font rendering
    }

    public static String uwuWithoutCuteFace(String stringToUwuify) {
        if (stringToUwuify.equals(UwUMod.prevUwuifiedMessage)) return stringToUwuify;
        return UwUMod.prevUwuifiedMessage = (UwUMod.prevMessage = stringToUwuify).toLowerCase().replaceAll("[rl]","w").replaceAll("n([aeiou])", "ny$1").replaceAll("ove", "uve").replaceAll("uck", "uwq").replaceFirst("i", "i-i").replaceFirst("(?s)(.*)" + "i-i-i", "$1" + "i-i");
    }

    /**
     * This uwuifier makes no guarantee that every input has the same output.
     * Consider using uwuifySame to get the same output on each input.
     * @param stringToUwuify - the string to be uwuified
     * @return - the uwuified string
     */
    public static String uwuify(String stringToUwuify) {
        Random rand = new Random();
        return stringToUwuify.toLowerCase().replaceAll("[rl]","w").replaceAll("n([aeiou])", "ny$1").replaceAll("ove", "uve").replaceAll("uck", "uwq").replaceFirst("i", "i-i").replaceFirst("(?s)(.*)" + "i-i-i", "$1" + "i-i") + ((rand.nextInt(10)) <= 2 ? " >-<" : "") + ((rand.nextInt(10)) <= 1 ? " uwu" : "");
    }


    /**
     * This uwuifier makes a guarantee that every input has the same output.
     * Consider using uwuify to get a different output on each input.
     * @param stringToUwuify - the string to be uwuified
     * @return - the uwuified string
     */
    public static String uwuifySame(String stringToUwuify){


        Random rand = new Random(stringToSeed(stringToUwuify));

        return stringToUwuify.toLowerCase().replaceAll("[rl]","w").replaceAll("n([aeiou])", "ny$1").replaceAll("ove", "uve").replaceAll("uck", "uwq").replaceFirst("i", "i-i").replaceFirst("(?s)(.*)" + "i-i-i", "$1" + "i-i") + ((rand.nextInt(10)) <= 2 ? " >-<" : "") + ((rand.nextInt(10)) <= 1 ? " uwu" : "");
    }


    /**
     * This uwuifier decides between uwuify and uwuifySame depending on the value of USE_PREVIEW.
     * @param stringToUwuify - the string to be uwuified
     * @return - the uwuified string
     */
    public static String uwuifyMessage(String stringToUwuify) {

        if(USE_PREVIEW) {
            return uwuifySame(stringToUwuify);
        }

        return uwuify(stringToUwuify);

    }

    private static long stringToSeed(String stringToSeed) {
        long seed = 0;
        for(int i = 0; i < stringToSeed.length(); i++) {
            seed = (seed * 31) + stringToSeed.charAt(i);
        }
        return seed;
    }
}
