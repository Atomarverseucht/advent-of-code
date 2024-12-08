package day8;
import java.util.ArrayList;
import java.util.List;

public class Antennatype {
    char frequency;
    List<Position> antenna = new ArrayList<>();

    public void addAntenna(int row, int column){
        Position p = new Position();
        p.row = row;
        p.column = column;
        this.antenna.add(p);
    }

    public String toString(){
        return antenna.toString();
    }
}
