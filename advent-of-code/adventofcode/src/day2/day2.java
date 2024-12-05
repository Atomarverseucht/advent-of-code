package day2;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class day2 {
    static int direction = 0;
    static int result = 0;
    public static void main(String[] args) throws Exception {
        
        //Daten Einlesen aus txt
        System.out.println("\nDec. 2nd!");
        String fileName = "advent-of-code/adventofcode/src/day2/input.txt";
        Path path = Paths.get(fileName);
        List<String> reports = Files.readAllLines(path, StandardCharsets.UTF_8);
        
        for (String value : reports) {
            String[] levelStrings = value.split(" ");
            int ignoredIndex = -1;
            for (int h = 0; h < levelStrings.length; h++) {
                if(checkLevels(levelStrings, ignoredIndex)){
                    result++; break;
                } else {
                   ignoredIndex++; 
                }
            }
        }

        System.out.println(result);
    }

    public static int compareLevels(int level1, int level2){
        int difference = level1 - level2;
        if(Math.abs(difference) > 3 || difference == 0){
            return -1;
        } else if(difference > 0){
            return 0;
        } else if(difference < 0){
            return 1;
        }
        return -1;
    }

    public static boolean checkLevels(String[] levelStrings, int ignoredIndex){
        int lastLevel = 0;
        boolean firstRun = true;
        if(ignoredIndex != 0){
            lastLevel = Integer.parseInt(levelStrings[0]);
        } else{ lastLevel = Integer.parseInt(levelStrings[1]);}

        for (int i = 1; i < levelStrings.length; i++) { 
            if(i != ignoredIndex || !(i == 1 && ignoredIndex == 0)){
            int newLevel = Integer.parseInt(levelStrings[i]);
            int res = compareLevels(lastLevel, newLevel);
            if(res == -1){
                return false;
            } else if(firstRun){
                direction = res;
                firstRun = false;
            } else if(res != direction){
                return false;
            } if(i == levelStrings.length - 1){
                return true;
            } lastLevel = newLevel; }
        }
        return false;
    }
}
