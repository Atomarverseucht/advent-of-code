package day7;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day7 {

    static long result = 0;
    public static void main(String[] args) throws Exception {
        
        //Daten Einlesen aus txt
        String fileName = "advent-of-code/adventofcode/src/day7/input.txt";
        Path path = Paths.get(fileName);
        List<String> equatation = Files.readAllLines(path, StandardCharsets.UTF_8);
        ex2(equatation);
        System.out.println(result);
    }

    public static void ex1(List<String> rows){
        for (String row : rows) {
            String[] splitted = row.split(" ");
            long solution = Long.parseLong(splitted[0].replace(":", ""));
            long[] nums = new long[splitted.length-1];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(splitted[i+1]);
            }
            final int n = nums.length;
            for (int i = 0; i < Math.pow(2, n); i++) {
                String bin = Integer.toBinaryString(i);
                while (bin.length() < n){
                    bin = "0" + bin;}
                char[] chars = bin.toCharArray();
                boolean[] boolArray = new boolean[n];
                for (int j = 0; j < chars.length; j++) {
                    boolArray[j] = (chars[j] == '0') ? true : false;
                }
                if(calculate(nums, boolArray) == solution){
                    result += solution;
                    break;
                }
            }
        }
    }

    public static void ex2(List<String> rows){
        for (String row : rows) {
            String[] splitted = row.split(" ");
            long solution = Long.parseLong(splitted[0].replace(":", ""));
            long[] nums = new long[splitted.length-1];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(splitted[i+1]);
            }

            final int n = nums.length;
            for (int i = 0; i < Math.pow(3, n); i++) {
                String bin = Integer.toString(i, 3);
                while (bin.length() < n){
                    bin = "0" + bin;}
                char[] chars = bin.toCharArray();
                int[] boolArray = new int[n];
                for (int j = 0; j < chars.length; j++) {
                    String hs = Character.toString(chars[j]);
                    boolArray[j] = Integer.parseInt(hs);
                }

                if(calculate(nums, boolArray) == solution){
                    result += solution;
                    break;
                } 
            }
        }
    }
    public static long calculateNumbers(long n1, long n2, boolean isMultiply){
        if(isMultiply){
            return n1 * n2;
        } else{
            return n1 + n2;
        }
    }

    public static long calculateNumbers(long n1, long n2, int operator) {
        switch(operator){
            case 0: return n1 + n2;
            case 1: return n1 * n2;
            default:
            case 2: return Long.parseLong(n1 + "" + n2);
        }
        
    }

    public static long calculate(long[] nums, boolean[] operator){
        long out = nums[0];
        for (int i = 1; i < nums.length; i++) {
            out = calculateNumbers(out, nums[i], operator[i-1]);
        }
        return out;
    }
    public static long calculate(long[] nums, int[] operator){
        long out = nums[0];
        for (int i = 1; i < nums.length; i++) {
            out = calculateNumbers(out, nums[i], operator[i-1]);
        }
        return out;
    }
}
