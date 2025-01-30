package day24;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Day24 {
    public static final int devide = 10;
    public static int result = 0;
    static void main(String[] args){
        try{
        Day24 d = new Day24();
        List<Variable> vars = new ArrayList<>();
        List<Operation> op = new ArrayList<>();
        //Daten Einlesen aus txt
        long startTime = System.currentTimeMillis();
        String fileName = "advent-of-code/adventofcode/src/day24/test.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i = 0; i < line.size(); i++) {
            String s = line.get(i);
            if(i<devide){
                String[] st = s.split(": ");
                Variable v = d.new Variable();
                v.name = st[0];
                v.value = Boolean.getBoolean(st[1]);
                v.isCalculated = true;
                vars.add(v);
            } else if (i>devide){
                String[] st = s.split(" ");
                Operation o = d.new Operation();
                o.v1 = st[0];
                o.operator = st[1];
                o.v2 = st[2];
                o.out = st[4];
                Variable v = d.new Variable();
                v.name = o.out;
                vars.add(v);
                op.add(o);
            }
        }
        ex1(vars, op);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        //ex2(field);
        System.out.println("Exercise 2: " + result);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + totalTime +"ms");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void ex1(List<Variable> varList, List<Operation> op){
        boolean[] check = new boolean[op.size()];
        everything:
        while (!op.isEmpty()) {
            for (int i = 0; i < op.size(); i++) {
                check[i] = true;
                Operation operate = op.get(i);
                Variable v1 = searchVariable(operate.v1, varList);
                Variable v2 = searchVariable(operate.v2, varList);
                if(v1.isCalculated && v2.isCalculated){ 
                    boolean out = false;
                    switch(operate.operator){
                        case "OR": out = v1.value | v2.value;
                        case "AND": out = v1.value && v2.value;
                        case "XOR": out = v1.value ^ v2.value;
                    }
                    Variable outv = searchVariable(operate.out, varList);
                    outv.value = out;
                    outv.isCalculated = true;
                    op.remove(i);
                    check[i] = false;
                }
            } 
            for (int i = 0; i < check.length; i++) {
                if(!check[i]) break;
                if(i == check.length -1) break everything;
            }
        }
        int count = 0;
        long val = 1;
        while (true) { 
            String name = "z" + ((count<10) ? "0"+Integer.toString(count) : Integer.toString(count));
            Variable v = searchVariable(name, varList);
            if(v == null){break;}
            result += (v.value ? val : 0);
            count++;
            val *= 2;
        }
    }

    public static Variable searchVariable(String name, List<Variable> things){
        for (Variable var : things) {
            if(name.equals(var.name)){
                return var;
            }
        }
        return null;
    }

    public class Variable{
        boolean value;
        boolean isCalculated = false;
        String name;
        public Variable(){}
    }

    public class Operation{
        String v1, v2, out;
        String operator;
    }
}