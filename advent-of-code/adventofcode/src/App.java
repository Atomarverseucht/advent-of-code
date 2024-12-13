import day2.day2;
import java.util.Scanner;

public class App {
    
    public static final int maxLevel = 13;
    private static final Scanner INPUT = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Which day would you like to calculate? Press '42' if you want to calculate everything ");
        while(INPUT.hasNext()){
            try{
            int input = inputInt();
            final long startTime = System.currentTimeMillis();
            startDay(input);
            long runtime = System.currentTimeMillis();
            runtime -= startTime;
            System.out.println("\nTime: "+runtime+"ms");
        }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    static void startDay(int in){
        String[] m = new String[1];
        try{
        switch (in) {
            case 1:
                day1.exercise1.main(m);
                break;
            case 2:
                day2.main(m); break;
            case 3:
                day3.Day3.main(m);break;
            case 4:
                day4.Day4.main(m); break;
            case 5:
                day5.Day5.main(m); break;
            case 6:
                day6.Day6.main(m); break;
            case 7:
                day7.Day7.main(m); break;
            case 8:
                day8.Day8.main(m); break;
            case 9:
                day9.Day9.main(m); break;
            case 10:
                day10.Day10.main(m); break;
            case 11:
                day11.Day11.main(m); break;
            case 12:
                day12.Day12.main(m); break;
            case 13:
                day13.Day13.main(m); break;
            case 42: in42(); break;
            default:
                throw new AssertionError();
        }}catch(Exception e){}
    }

    static void in42(){
        for (int i = 1; i <= maxLevel; i++) {
            System.out.println("Day"+i);
            startDay(i);
        }
    }
    static int inputInt(){
        String value = INPUT.next();
        try {
            int _value = Integer.parseInt(value);
            if((_value > 13 && _value != 42) || _value < 1){
                throw new NumberFormatException();
            }
            return _value;
            // Yes!  An double.
        } catch (NumberFormatException nfe) {
            System.out.println("Please give me a valid input Number between 1 and "+maxLevel+" (42 is also okay)");
            return inputInt();// Not an integer
        }
    }
}
