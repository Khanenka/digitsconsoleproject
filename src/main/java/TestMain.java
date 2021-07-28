import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TestMain {
  private static int billion;
  private static int million;
  private static int thousand;
  private static int toThousand;
  private static long value;
  private static long numberMax = 999999999999L;
  private static StringBuilder numText;

  //private  int index ;
  private static int indexA;
  private static int units;
  private static int decimal;
  private static int hundreds;
  public static void main(String[] args) throws IOException {
    StringBuilder sb=new StringBuilder();
    Scanner scanner=new Scanner(System.in);
    long numericalValue= scanner.nextLong();
    hundreds = (int) (numericalValue / 100);//сотни
    decimal = (int) ((numericalValue - (hundreds * 100)) / 10);//десятые
    units = (int) (numericalValue % 10);//единицы
    System.out.println(hundreds+"  "+decimal+"   "+units);
//  число без степени

    String unitsInFile =Files.readAllLines(Paths.get("src/main/vocabulary")).get(0+units);
    String doubleUnitsInFile=Files.readAllLines(Paths.get("src/main/vocabulary")).get(11+units);
    String decimalInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(21+decimal);
    String hundredsInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(31+hundreds);


    if (decimal == 1) {
      sb.append(hundredsInFile).append(doubleUnitsInFile);
      System.out.println(sb);
    } else {
      sb.append(hundredsInFile).append(decimalInFile).append(unitsInFile);
      System.out.println(sb);
    }
    }
  }

