package day4;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class Day4 {
    static int result = 0;
    public static void main(String[] args){
        System.out.println("Dec. 4th!");
        try {
            // Daten aus Datei einlesen
            String fileName = "advent-of-code/adventofcode/src/day4/input.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String[] all = new String[allLines.size()];
        for (int i = 0; i < all.length; i++) {
            all[i] = allLines.get(i);
        }
        ex2(all);
        System.out.println("Ergebnis: " + result);
        } catch (Exception e) {
           System.out.println(e.toString());
           e.printStackTrace();
        }
        
    }

    public static void ex1(String[] input){
        char[][] inputChars = new char[input.length][maxStringArray(input)]; 
        for (int h = 0; h < input.length; h++) {
            for (int k = 0; k < maxStringArray(input); k++) {
                inputChars[h][k] = '-';
            }
        }
        for (int i = 0; i < input.length; i++) {
            inputChars[i] = input[i].toCharArray();
        }
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < maxStringArray(input); column++) {
                int res = newIndex(column, inputChars[row], "XMAS");
                if(res != -1){
                    result++;
                }
                res = newIndex(column, inputChars[row], "SAMX");
                if(res != -1){
                    result++;
                }
                isDiagonal(row, column, inputChars, "XMAS", input.length, maxStringArray(input));
                isDiagonal(row, column, inputChars, "SAMX", input.length, maxStringArray(input));
                isVertical(row, column, inputChars, "XMAS", input.length);
                isVertical(row, column, inputChars, "SAMX", input.length);
            }
        }
    }

    public static void ex2(String[] input){
        char[][] inputChars = new char[input.length][maxStringArray(input)]; 
        for (int h = 0; h < input.length; h++) {
            for (int k = 0; k < maxStringArray(input); k++) {
                inputChars[h][k] = '-';
            }
        }
        for (int i = 0; i < input.length; i++) {
            inputChars[i] = input[i].toCharArray();
        }
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < maxStringArray(input); column++) {
                isCross(row, column, inputChars, "MAS", input.length, maxStringArray(input));
            }
        }
    }

    public static void isCross(int row, int column, char[][] chars, String keyword, int maxRow, int maxColumn){
        char[] keyChars = keyword.toCharArray();
        if(keyChars.length == 3 && row+2 < maxRow && column +2 < maxColumn){
                if((keyChars[0] == chars[row][column] && keyChars[2] == chars[row+2][column+2])||(keyChars[2] == chars[row][column] && keyChars[0] == chars[row+2][column+2])){
                    if(keyChars[1]== chars[row+1][column+1]){
                        if((keyChars[0] == chars[row][column+2] && keyChars[2] == chars[row+2][column])||(keyChars[2] == chars[row][column+2] && keyChars[0] == chars[row+2][column]))
                        {result++;}
                    }
                    
                }
        }
    }
    public static boolean compareChars(char[] chars, char input, int index){
        if(chars[index] == input || chars[2-index] == input){
            return true;
        }
        else{
            return false;
        }
    }
    public static void isDiagonal(int row, int column, char[][] chars, String keyword, int maxRow, int maxColumn){
        char[] keyChars = keyword.toCharArray();
        if(row + keyChars.length <= maxRow){
            if(column + keyChars.length <= maxColumn){
            for (int i = 0; i < keyChars.length; i++) {
            if(keyChars[i] != chars[row+i][column+i]){
                break;
            } else if(i == keyChars.length - 1){
                result++;
            }}  
            }
            if(column - (keyChars.length -1) >= 0){
                for (int h = 0; h < keyChars.length; h++) {
                    if(keyChars[h] != chars[row+h][column-h]){
                        break;
                    } else if(h == keyChars.length -1){
                        result++;
                    }
                }
            }
        }
    }

    public static void isVertical(int row, int column, char[][] chars, String keyword, int maxRow){
        char[] keyChars = keyword.toCharArray();
        if(row + keyChars.length <= maxRow){
            for (int i = 0; i < keyChars.length; i++) {
                if(keyChars[i] != chars[row + i][column]){
                    break;
                }else if(i == keyChars.length -1){
                    result++;
                }
            }
        }
        
    }
    public static int newIndex(int i, char[] chars, String keyword) {
        char[] keyChars = keyword.toCharArray();
        int index = 0;

        // Prüfe, ob das Keyword vollständig in die Zeichenkette passt
        for (char c : keyChars) {
            if (i + index >= chars.length || c != chars[i + index]) {
                return -1; // Ungültig, wenn ein Zeichen nicht übereinstimmt
            }
            index++;
        }
        return index - 1; // Letzter Index des Keywords
    }
    public static int maxStringArray(String[] in){
        int out = 0;
        for (String string : in) {
            if(out < string.length()){
                out = string.length();
            }
        }
        return out;
    }
}
