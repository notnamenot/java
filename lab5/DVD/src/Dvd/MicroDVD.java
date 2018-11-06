package Dvd;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException; //>FileNotFoundException<Exception<Throwable
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDVD {


    public void delay(final String pathToInFile, String pathToOutFile, int fps, int ms) throws  Exception {

        Scanner in = null;
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(pathToOutFile);
            FileReader fileReader = new FileReader(pathToInFile);
            in = new Scanner(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        try {

            double fpms = fps * 0.001 * ms;
            int frames = (int) fpms;

            String line;
            int firstOpenBracket;
            int firstCloseBracket;
            int secondOpenBracket;
            int secondCloseBracket;

            String fram1;
            String fram2;

            String fra1;
            String fra2;

            int f1;
            int f2;

            while (in.hasNextLine()) {
                line = in.nextLine();

                if (!line.matches("\\{[0-9]+\\}\\{[0-9]+\\}.*"))
                    throw new Exception("Wrong value in frame at line: " + line);

                firstOpenBracket = line.indexOf("{");
                firstCloseBracket = line.indexOf("}");
                secondOpenBracket = line.indexOf("{", firstOpenBracket + 1);
                secondCloseBracket = line.indexOf("}", firstCloseBracket + 1);

                fram1 = line.substring(firstOpenBracket + 1, firstCloseBracket);
                fram2 = line.substring(secondOpenBracket + 1, secondCloseBracket);
                //if (!fram1.matches("[0-9]+") || !fram2.matches("[0-9]+"))throw new Exception("Not digit value in frame at line: " + line);

                f1 = Integer.parseInt(fram1);
                f2 = Integer.parseInt(fram2);
                if (f1 > f2)
                    throw new Exception("Wrong value in frame: first number of frame bigger than second at line: " + line);

                f1 += frames;
                f2 += frames;

                fra1 = Integer.toString(f1);
                fra2 = Integer.toString(f2);

                line = line.replace(fram1, fra1);
                line = line.replace(fram2, fra2);
                //System.out.println("E" + line);

                fileWriter.println(line);
            }
            in.close();

        } finally {
            fileWriter.close();
            in.close();
        }
    }
}




/*
class WordContainsException extends Exception {
    // Parameterless Constructor
    public WordContainsException() {}

    // Constructor that accepts a message
    public WordContainsException(String message) { super(message); }
}
//catch (WordContainsException e) { e.printStackTrace();System.out.println(e.getMessage());}
*/

