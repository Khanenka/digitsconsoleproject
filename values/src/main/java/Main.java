import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {

  private static String numText;

  private static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите число от -999 999 999 до 999 999 999");
    long value = sc.nextLong();
    System.out.println(WordsToText(value));

  }

  public static String WordsToText(long value) throws IOException {

    long numberMax = 999999999999L;
    if (value < -numberMax || value > numberMax) {
      numText = ("Число выходит за рамки указанного диапазона");
    }
    if (value == 0) {
      numText = ("ноль ");
    }
    if (value < 0) {
      numText = ("минус ");
      value = -value;
    } else if (value > 0) {
      numText = ("");
    }

// разбиваем число на миллиарды,миллионы,тысячи и единицы
    int billion = (int) (value / 1_000_000_000);
    int million = (int) (value - (billion * 1_000_000_000)) / 1_000_000;
    int thousand = (int) (value - (billion * 1_000_000_000) - (million * 1_000_000)) / 1000;
    int toThousand = (int) (value % 1000);

    String x = divisoinThousandAnd(billion, 0);
    String z = divisoinThousandAnd(million, 1);
    String v = divisoinThousandAnd(thousand, 2);
    String c = divisoinThousandAnd(toThousand, 3);

    return numText + c;

  }

  public static String divisoinThousandAnd(int value, int index) throws IOException {

    int hundreds = value / 100;//сотни
    int decimal = (value - (hundreds * 100)) / 10;//десятые
    int units = value % 10;//единицы

//  число без степени
    String unitsInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(units);
    String doubleUnitsInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(11 + units);
    String decimalInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(21 + decimal);
    String hundredsInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(31 + hundreds);
    String endingNa = Files.readAllLines(Paths.get("src/main/vocabulary")).get(46);
    String endingE = Files.readAllLines(Paths.get("src/main/vocabulary")).get(47);
    String endingSpace = Files.readAllLines(Paths.get("src/main/vocabulary")).get(48);
    String endingIn = Files.readAllLines(Paths.get("src/main/vocabulary")).get(49);
    String endingA = Files.readAllLines(Paths.get("src/main/vocabulary")).get(50);
    String billionInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(44);
    String endingOv = Files.readAllLines(Paths.get("src/main/vocabulary")).get(52);
    String millionInfile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(43);
    String thousandInFile = Files.readAllLines(Paths.get("src/main/vocabulary")).get(42);
    String endingI = Files.readAllLines(Paths.get("src/main/vocabulary")).get(53);

    if (decimal == 1) {

      sb.append(hundredsInFile).append(doubleUnitsInFile);

    } else {
      sb.append(hundredsInFile).append(decimalInFile).append(unitsInFile);

    }

    if (index == 2) {
      if (units == 1 && decimal != 1) {//од..НА
        sb.append(endingNa);
      } else if (units == 2 & decimal != 1) {
        sb.append(endingE);//дв..Е
      }
      if (units > 1 && decimal != 1) {
        sb.append(endingSpace);//" "пятьдесят четыре
      }
    } else {
      if (units == 1 && decimal != 1) {// тридцать оди..ИН
        sb.append(endingIn);
      }
      if (units == 2 & decimal != 1) {// двадцать дв..А
        sb.append(endingA);
      }
      if (units != 0 & decimal != 1) {
        sb.append(endingSpace);//" "пятьдесят четыре
      }
    }

    if (value != 0) {
      if (units == 0 && decimal >= 0 && index == 0) {
        sb.append(billionInFile).append(endingOv);
      } else if (units == 1 && decimal >= 0 && index == 0) {
        sb.append(billionInFile).append(endingSpace);
      } else if (units > 2 && units < 5 && decimal != 1 && index == 0) {
        sb.append(billionInFile).append(endingA);
      } else if (units > 2 && units < 5 && decimal == 1 && index == 0) {
        sb.append(billionInFile).append(endingOv);
      } else if (units > 5 && decimal >= 0 && index == 0) {
        sb.append(billionInFile).append(endingOv);


      } else if (units == 0 && decimal >= 0 && index == 1) {
        sb.append(millionInfile).append(endingOv);
      } else if (units == 1 && decimal >= 0 && index == 1) {
        sb.append(millionInfile).append(endingSpace);
      } else if (units > 2 && units < 5 && decimal != 1 && index == 1) {
        sb.append(millionInfile).append(endingA);
      } else if (units > 2 && units < 5 && decimal == 1 && index == 1) {
        sb.append(millionInfile).append(endingOv);
      } else if (units > 5 && decimal >= 0 && index == 1) {
        sb.append(millionInfile).append(endingOv);


      } else if (units == 0 && decimal >= 0 && index == 2) {
        sb.append(thousandInFile).append(endingSpace);
      } else if (units == 1 && decimal >= 0 && index == 2) {
        sb.append(thousandInFile).append(endingSpace);
      } else if (units > 2 && units < 5 && decimal == 0 && index == 2) {
        sb.append(thousandInFile).append(endingI);
      } else if (units > 5 && decimal >= 0 && index == 2) {
        sb.append(thousandInFile).append(endingSpace);
      } else if (units > 2 && units < 5 && decimal != 1 && index == 2) {
        sb.append(thousandInFile).append(endingI);
      }
    }

    // дописываем степень числа
    return String.valueOf(sb);
  }

}


