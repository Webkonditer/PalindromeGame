package ru.webkonditer.service;

import static ru.webkonditer.repo.DatabaseManager.savePalindromeToDb;

public class PalindromeService {

    // Метод проверки палиндрома
    public static int checkPalindrome(String palindrome) {
        StringBuilder pal = new StringBuilder(palindrome.toLowerCase().replace(" ", ""));
        if (pal.toString().contentEquals(pal.reverse())) {
            return palindrome.length();
        }
        return -1;
    }

    // Метод соохранения палиндрома в базу
    public static void savePalindrome (String user, String palindrome) {
        int score = palindrome.length();
        savePalindromeToDb(user, palindrome, score);
    }

}
