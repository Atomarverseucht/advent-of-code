package day8;
import java.util.List;

public class Antennatype {
    char index;
    List<Position> antenna;

    public void addAntenna(int row, int column){
        Position p = new Position();
        p.row = row;
        p.column = column;
        this.antenna.add(p);
    }
}
