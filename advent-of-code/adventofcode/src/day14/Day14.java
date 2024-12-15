package day14;

import day8.Position;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14 {
    public static long result = 0;
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        long startTime = System.currentTimeMillis();
        String fileName = "advent-of-code/adventofcode/src/day14/input.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        int[][] field = new int[line.size()][4];
        for (int i = 0; i < line.size(); i++) {
                String row = line.get(i);
                row = row.replace("p=", "").replace("v=","");
                String[] col_ = row.split(" ");
                for (int j = 0; j < 2; j++) {
                    String[] c = col_[j].split(",");
                    field[i][j*2] = Integer.parseInt(c[0]);
                    field[i][j*2 + 1] = Integer.parseInt(c[1]);
                }
        }
  
        ex1(field);
        System.out.println("\nExercise 1: " + result);
        result = 0;
        //ex2(field);
        System.out.println("Exercise 2: " + result);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + totalTime +"ms");

       } catch(Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
       }
    }

    public static void ex1(int[][] input){
        Position maxPos = Position.getPosition(102, 100);
        ArrayList<Robot> robots = new ArrayList<>();
        
        for (int i = 0; i < input.length; i++) {
            int[] in = input[i];
            robots.add(new Robot(Position.getPosition(in[1], in[0]), Position.getPosition(in[3], in[2])));
        }for (Robot robot2 : robots) {
            System.out.println(robot2.position.toString());
        }
System.out.println(robots.size());
        for (int i = 0; i < 101; i++) {
            for (Robot robot : robots) {
                robot.moveRobot(maxPos);
            }
        }
        

        int[] count = Robot.countRobotsInQuadrats(robots, maxPos);
        System.out.println(Arrays.toString(count));
        result = count[0] * count[1] * count[2] * count[3];
    }
}
