package day9;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static long result = 0;
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        //System.out.println();
        String fileName = "advent-of-code/adventofcode/src/day9/test.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        char[] field = line.get(0).toCharArray();
        ex1(field);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        ex2(field);
        System.out.println("Exercise 2: " + result);

       } catch(Exception e){
            System.out.println();
            e.printStackTrace();
       }
    }
    public static void ex1(char[] values){
        List<String> memoryList = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < Character.getNumericValue(values[i]); j++) {
                if(i%2 == 0){
                    numbers.add(Integer.toString(x));
                    memoryList.add(Integer.toString(x));
                } else{
                    memoryList.add(".");
                }
            } if(i%2 == 1){x++;}
        }

        int check = numbers.size();
        for (int i = 0; i < memoryList.size(); i++) {
            String k = memoryList.get(i);
            int index = numbers.size() - 1;
                if(k.equals(".")){
                    memoryList.set(i, numbers.get(index));
                    numbers.remove(index);
                    memoryList.removeLast();
                }
        }
        
        List<Integer> num = new ArrayList<Integer>();
        for (int i = 0; i < memoryList.size(); i++) {
            String k = memoryList.get(i);
            if(k != "." && i < check){
                num.add(Integer.parseInt(k));
            }
        }

        for (int i = 0; i < num.size(); i++) {
            result += i * num.get(i);
        }
    }

    public static void ex2(char[] values){
        List<String> memoryList = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<Integer> packN = new ArrayList<>();
        List<Integer> packP = new ArrayList<>();

        int x = 0;
        for (int i = 0; i < values.length; i++) {
            int k = Character.getNumericValue(values[i]);
                if(i%2 == 0){
                    numbers.add(x);
                    memoryList.add(Integer.toString(x));
                    packN.add(k);
                    x++;
                } else{
                    memoryList.add("."); packP.add(k);
                }
             
            if(i%2 == 0){} else{}
        }
        List<Integer> num = new ArrayList<Integer>();
        for (int i = 0; i < memoryList.size(); i++) {
            if(packN.isEmpty()){break;}
            String k = memoryList.get(i);
            out:
           if(k.equals(".")){
                for (int j = packN.size()-1; j > i; j--) {
                    if(packP.get(i) >= packN.get(j)){
                        for (int h = 0; h < packN.get(j); h++) {
                            num.add(numbers.get(j));
                        }
                        
                        int rest = packP.get(0) - packN.get(j);
                        numbers.remove(j);
                        packN.remove(j); 
                        if(rest != 0){
                            packP.set(0, rest); 
                            j = packN.size() -1; 
                            continue;} 
                        else{
                            packP.removeFirst();
                            break out;}
                    } if(j == packN.size()-1){
                        packP.removeFirst();
                    }
                }
           } else{
                int p = 0;
                for (int h = 0; h < packN.get(p); h++) {
                    num.add(numbers.get(p));
                }numbers.remove(p); packN.remove(p);
            }   System.out.println(num.toString());
        }

        for (int i = 0; i < packN.size(); i++) {
            for (int j = 0; j < packN.get(i); j++) {
                num.add(numbers.get(i));
            }
        }
        
        for (int i = 0; i < num.size(); i++) {
            result += i * num.get(i);
        }
    }
}
