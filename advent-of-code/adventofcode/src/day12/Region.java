package day12;

import java.util.ArrayList;
import java.util.List;

import day8.Position;

public final class Region {
    char flower;
    List<Position> plots = new ArrayList<>();

    public void addPlot(int row, int column){
        Position p = new Position();
        p.row = row;
        p.column = column;
        this.plots.add(p);
    }

    public static Region getRegion(Position position, char flower){
        Region reg = new Region();
        reg.flower = flower;
        reg.plots.add(position);
        return reg;
    }
    
    public String toString(){
        return plots.toString();
    }
}
