package day5;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class Day5 {
    static boolean[][] numbersAfter = new boolean[100][100];
    static int result = 0;
    static final int division = 1177;
    public static void main(String[] args){
        try {
            // Daten aus Datei einlesen
        String fileName = "D:/,private Projekte/Code/advent-of-code/advent-of-code/advent-of-code/adventofcode/src/day5/input.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String[] input = new String[allLines.size()];
        for (int i = 0; i < input.length; i++) {
            input[i] = allLines.get(i);
        }
        for (int i = 0; i < division-1; i++) {
            String num[] = {"0", "0"};
            num = (input[i].split("\\|"));
            numbersAfter[Integer.parseInt(num[0])][Integer.parseInt(num[1])] = true;
        }
        //System.out.println(input.length);
        ex2(input);
        System.out.println("Ergebnis: " + result);
        } catch (Exception e) {
           System.out.println(e.toString());
           e.printStackTrace();
        }
        
    }
    
    public static void ex1(String[] input){
        for (int i = division+1; i < input.length; i++) {
            String[] val = input[i].split(",");
            int[] value = new int[val.length];
            for (int h = 0; h < val.length; h++) {
                value[h] = Integer.parseInt(val[h]);
            }
            //System.out.println("Zeile " + i + ": " + Arrays.toString(val));
            nums:
            for (int l = 0; l < value.length; l++) {
                int x = value[l];
                for (int p = 0; p < l; p++) {
                    int y = value[p];
                    if(numbersAfter[x][y]){
                        break nums;
                    }
                }
                if(l == value.length -1){
                    System.out.println(Arrays.toString(value));
                    System.out.println(value[(value.length - 1) / 2]);
                    result += value[(value.length - 1) / 2];
                }
            } 
        }
    }

    public static void ex2(String[] input){
        
        for (int i = division+1; i < input.length; i++) {
            boolean firstRun = true;
            String[] val = input[i].split(",");
            int[] value = new int[val.length];
            for (int h = 0; h < val.length; h++) {
                value[h] = Integer.parseInt(val[h]);
            }
            //System.out.println("Zeile " + i + ": " + Arrays.toString(val));
            nums:
            for (int l = 0; l < value.length; l++) {
                int x = value[l];
                for (int p = 0; p < l; p++) {
                    int y = value[p];
                    if(numbersAfter[x][y]){
                        int help = value[p];
                        value[p] = value[l];
                        value[l] = help;
                        firstRun = false;
                        l = 0;
                        continue nums;
                    }
                }
                if(l == value.length -1 && !firstRun){
                    System.out.println(Arrays.toString(value));
                    System.out.println(value[(value.length - 1) / 2]);
                    result += value[(value.length - 1) / 2];
                }
            } 
        }
    }
}
