package day3;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class Day3 {
    static int[] array1 = new int[1000];
    static int[] array2 = new int[1000];
    static int result = 0;
    public static void main(String[] args) throws Exception {
        //Daten Einlesen aus txt
        System.out.println("Dec. 3rd!");
        String fileName = "C:/Users/tombo/private-Projekte/advent-of-code/advent-of-code/adventofcode/src/day3/input.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String string : allLines) {
            int index = 0;
            char[] charInput = string.toCharArray();
            for (int i=0; i < charInput.length; i++) {
                char charIn = charInput[i];
               if((charIn == 'm' && index == 0)||(charIn == 'u' && index == 1)||(charIn == 'l' && index == 2)||(charIn == '(' && index == 3)) {
                index++;
               } else if(Character.isDigit(charIn) && index == 4){ // Command sieht so aus: mul(X................. X ist eine Nummer
                index = 0;
                int[] nums = getNumbers(i,charInput);
                if(nums[2] != -1){
                    int x = nums[0];
                    int y = nums[1];
                    i = nums[2];
                    result += x * y;
                } else{ index = 0;}
               } else{ index = 0;}
            }
        }
        System.out.println(result);
    }
    public static int[] getNumbers(int i, char[] chars){
        String res = String.valueOf(chars[i]);
        int[] out = new int[3];
        boolean commaAllowed = true;
        boolean hasValue = false;
        for (int j = 1; j < 7; j++) {
            char sign = chars[i+j];
            if(Character.isDigit(sign)){
                res += sign;
                hasValue = true;
            } else if(sign == ',' && commaAllowed){
                out[0] = Integer.parseInt(res);
                res = "";
                commaAllowed = false;
                hasValue = false;
            }
            else if (!commaAllowed && sign == ')'&& hasValue){
                out[1] = Integer.parseInt(res);
                out[2] = (i+j) - 1; break;}
            else{out[2] = -1;}
        }
        return out;
    }
}