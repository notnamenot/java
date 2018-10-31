import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Cryptographer {
    public static void cryptfile(String fromFile, String toFile, Algorithm alg) throws Exception {
        try {
            FileReader fileReader = new FileReader(fromFile);
            Scanner in = new Scanner(fileReader);

            PrintWriter fileWriter = new PrintWriter(toFile);

            String s= "";
            String cr = "";
            String c = "";
            in.useDelimiter("");    //After this reader.next() will return a single-character string.

            while (in.hasNext()) {
                c = in.next();
                s= "";

                while (Character.isLetterOrDigit((c.charAt(0)))) {
                    s +=c;
                    if (!in.hasNext())
                        break;
                    c = in.next();
                }

                if(s != "") {
                    cr = alg.crypt(s);
                    fileWriter.print(cr);
                }
                if (Character.isWhitespace(c.charAt(0)))  //c = in.next(); while
                    fileWriter.print(c);
            }
            fileWriter.close();


        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static  void decryptfile(String fromFile, String toFile, Algorithm alg) throws Exception {
        try {
            FileReader fileReader = new FileReader(fromFile);
            Scanner in = new Scanner(fileReader);

            PrintWriter fileWriter = new PrintWriter(toFile);

            String s= "";
            String cr = "";
            String c = "";
            in.useDelimiter("");    //After this reader.next() will return a single-character string.

            while (in.hasNext()) {
                c = in.next();
                s= "";

                while (Character.isLetterOrDigit((c.charAt(0)))) {
                    s +=c;
                    if (!in.hasNext())
                        break;
                    c = in.next();
                }

                if(s != "") {
                    cr = alg.decrypt(s);
                    fileWriter.print(cr);
                }
                if (Character.isWhitespace(c.charAt(0)))  //c = in.next(); while
                    fileWriter.print(c);
            }
            fileWriter.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

}