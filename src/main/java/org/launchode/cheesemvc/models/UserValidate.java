package org.launchode.cheesemvc.models;

import static jdk.nashorn.internal.objects.NativeString.charAt;

public class UserValidate {


    public static boolean nameIsAlpha(String username) {
        char[] characters = username.toCharArray();
        for (char letter : characters) {
            if (!Character.isLetter(letter)) {
                return false;
//            } else {
//                return true; note don't know why this return doesn't work
//        }
            }
        }
        return true;

    }
}

//        for (Character username)
//        for (int i =0; i <= username.length(); i++) {
//            if ((int)charAt(i) < 64 && (int)charAt(i) > 123) {
//            return false;
//            } else {
//                return true;
//            }


