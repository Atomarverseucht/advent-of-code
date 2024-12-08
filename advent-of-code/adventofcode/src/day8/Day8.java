package day8;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static int result = 0;
    public static void main(String[] args){
       try{
        //Daten Einlesen aus txt
        String fileName = "advent-of-code/adventofcode/src/day8/input.txt";
        Path path = Paths.get(fileName);
        List<String> line = Files.readAllLines(path, StandardCharsets.UTF_8);
        char[][] field = new char[line.size()][line.get(0).length()];
        for (int i = 0; i < field.length; i++) {
            field[i] = line.get(i).toCharArray();
        }
        ex1(field);
        System.out.println(result);

       } catch(Exception e){
            e.printStackTrace();
       }
    }

    public static void ex1(char[][] input){
        char[][] antinodes = new char[input.length][input[0].length];
        List<Antennatype> antennatypes;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                char value = input[i][j];
                if(value != '.'){
                    int index = getIndexOfAntenna(antennatypes, value)
                    if(index == -1){
                        index = antennatypes.size();
                    } 
                    antennatypes.get(index).addAntenna(i,j);
                }
            }
        }
        
        for (int j = 0; j < input.length; j++) {
           Antennatype antennas = antennatypes.get(j);
           for (int k = 1; k < antennas.antenna.size(); k++) {
                Position antenna1 = antennas.antenna.get(j-1);
                Position antenna2 = antennas.antenna.get(j);
                Position deltaPosition = new Position();
                deltaPosition.deltaPosition(antenna1, antenna2);

                
           } 
        }
    }

    public static int getIndexOfAntenna(List<Antennatype> list, char value){
       for (int i = 0; i < list.size(); i++) {
        if(list.get(i).index == value){
            return i;
        }
       }
        return -1;
    }
}
