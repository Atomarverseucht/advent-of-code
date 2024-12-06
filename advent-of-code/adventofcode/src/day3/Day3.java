package day3;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {
    static boolean allowedMul = true; // Steuert, ob Multiplikationen ausgeführt werden
    static int result = 0;           // Ergebnis der gültigen Multiplikationen

    public static void main(String[] args) throws Exception {
        System.out.println("Dec. 3rd!");

        // Daten aus Datei einlesen
        String fileName = "advent-of-code/adventofcode/src/day3/input.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

        ex2(allLines);
        System.out.println("Ergebnis: " + result);
    }

    public static void ex2(List<String> allLines) {
        for (String string : allLines) {
            char[] charInput = string.toCharArray();
            for (int i = 0; i < charInput.length; i++) {
                int res = newIndex(i, charInput, "do()");
                if (res != -1) {
                    allowedMul = true;
                    i += res;
                }

                res = newIndex(i, charInput, "don't()");
                if (res != -1) {
                    allowedMul = false;
                    i += res;
                }

                if (allowedMul) {
                    res = newIndex(i, charInput, "mul(");
                    if (res != -1) {
                        i += res + 1; // Springe zum Start der Zahlen nach "mul("
                        res = mulCommand(i, charInput);
                        if (res != -1) {
                            i = res; // Aktualisiere Index, um hinter der aktuellen Multiplikation fortzufahren
                        }
                    }
                }
            }
        }
    }

    public static int newIndex(int i, char[] chars, String keyword) {
        char[] keyChars = keyword.toCharArray();
        int index = 0;

        // Prüfe, ob das Keyword vollständig in die Zeichenkette passt
        for (char c : keyChars) {
            if (i + index >= chars.length || c != chars[i + index]) {
                return -1; // Ungültig, wenn ein Zeichen nicht übereinstimmt
            }
            index++;
        }
        return index - 1; // Letzter Index des Keywords
    }

    public static int mulCommand(int i, char[] charInput) {
        int res = -1;

        if (Character.isDigit(charInput[i])) {
            int[] nums = getNumbers(i, charInput);
            if (nums[2] != -1) {
                int x = nums[0];
                int y = nums[1];
                res = nums[2];
                result += x * y; // Addiere das Produkt der Zahlen zum Ergebnis
            }
        }
        return res;
    }

    public static int[] getNumbers(int i, char[] chars) {
        StringBuilder res = new StringBuilder();
        int[] out = new int[3];
        boolean commaAllowed = true;
        boolean hasValue = false;

        for (int j = 0; i + j < chars.length; j++) {
            char sign = chars[i + j];
            if (Character.isDigit(sign)) {
                res.append(sign);
                hasValue = true;
            } else if (sign == ',' && commaAllowed) {
                out[0] = Integer.parseInt(res.toString());
                res.setLength(0); // StringBuilder leeren
                commaAllowed = false;
                hasValue = false;
            } else if (!commaAllowed && sign == ')' && hasValue) {
                out[1] = Integer.parseInt(res.toString());
                out[2] = i + j; // Rückgabe des letzten Indexes
                return out;
            } else {
                break;
            }
        }
        out[2] = -1; // Ungültig, falls die Schleife abbricht
        return out;
    }
}
