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
        System.out.println();
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
            System.out.println();
            e.printStackTrace();
       }
    }

    public static void ex1(char[][] input){
        boolean[][] antinodes = new boolean[input.length][input[0].length];
        List<Antennatype> antennatypes = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                char value = input[i][j];
                if(value != '.'){
                    int index = getIndexOfAntenna(antennatypes, value);
                    if(index == -1){
                        Antennatype an = new Antennatype();
                        an.frequency = value;
                        an.antenna.add(Position.getPosition(i,j));
                        antennatypes.add(an);
                    } else{
                        antennatypes.get(index).addAntenna(i,j);
                    }
                }
            }
        }
        
        //every antennatype
        for (Antennatype antennas : antennatypes) {

           // every combination of 2 antennas
           for (int k = 0; k < antennas.antenna.size(); k++) {
                for (int i = 0; i < antennas.antenna.size(); i++) {
                    if(i != k){
                        Position pos = antennas.antenna.get(i);
                        Position delta = new Position();
                        delta.deltaPosition(antennas.antenna.get(i), antennas.antenna.get(k));

                        int row = pos.row + delta.row;
                        int column = pos.column + delta.column;
                        if(row >= 0 && row < input.length && column >= 0 && column < input[0].length){
                            antinodes[row][column] = true;
                        }
                    }
                } 
            }    
        }

        for (boolean[] ant : antinodes) {
            for (boolean antinode : ant) {
                if(antinode){
                    result++;
                }
            }
        }
        //System.out.println(antennatypes.toString());
    }



    public static int getIndexOfAntenna(List<Antennatype> list, char value){
       for (int i = 0; i < list.size(); i++) {
            if(list.get(i).frequency == value){
                return i;
            }
        }
        return -1;
    }
}
