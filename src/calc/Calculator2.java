package calc;

import java.util.Scanner;

public class Calculator2 {

    public static void main(String[] args) {
        String text; //содержит выражение пользователя
        String[] part;//массив с подстроками числа, операторы
        double result;//конечный результат
        Scanner scanner = new Scanner(System.in);//повтор запуска
        int ans;
        do {
            text = readText();//запрос от пользователя выражения

            part = splitText(text);//деление выражения на подстроки

            result = calc(part);//вычисляем выражение по массиву данных

            System.out.println("Результат: " + result);

            System.out.println("\n       Продолжим? Если ДА, то нажми 1, если НЕТ, то любую другую цифру");
            if (scanner.hasNextInt()) {
                ans = scanner.nextInt();
            } else {
                ans = 0;
            }
        } while (ans == 1);
        System.out.println("До новых встреч!");
    }

    private static String readText() {
        //запрос выражения у пользователя
        System.out.println("ВВЕДИТЕ МАТ.ВЫРАЖЕНИЕ, в конце нажмите ENTER");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static String[] splitText(String text) {
        //деление текста на подстроки: число, оператор, число, оператор
        String[] part;
        part = new String[text.length()];
        String[] returnPart;//КОНЕЧНЫЙ МАССИВ ПОДСТРОК, ВОЗВРАЩАЕМЫЙ В MAIN
        int numberPart = 0;
        int leftBorderPart = 0;
        int size = text.length();

        text = text.replace(',', '.');

        for (int i = 0; i < size; i++) {
            if (text.charAt(i) < 42 || text.charAt(i) > 57) {
                System.out.println("Вы нарушили условия и ввели недопустимые символы.Продолжение работы программы невозможно");
                System.exit(1);
            }
            if (text.charAt(i) == '+' || text.charAt(i) == '-' || text.charAt(i) == '*' || text.charAt(i) == '/') {
                part[numberPart] = text.substring(leftBorderPart, i);
                part[++numberPart] = text.substring(i, i + 1);
                numberPart++;
                leftBorderPart = i + 1;


            } else if (i == size - 1) {
                part[numberPart] = text.substring(leftBorderPart, i + 1);

            }
        }
        returnPart = new String[numberPart + 1];//уже знаем точный размер возвращаемого массива
        System.arraycopy(part, 0, returnPart, 0, numberPart + 1);
        return returnPart;
    }

    private static double calc(String[] part) {
        int size = part.length;
        double res;
        String operator;
        res = Double.parseDouble(part[0]);
        for (int i = 1; i < size; i++) {
            operator = part[i];
            switch (operator.charAt(0)) {
                case '+':
                    res += Double.parseDouble(part[++i]);
                    break;
                case '-':
                    res -= Double.parseDouble(part[++i]);
                    break;
                case '*':
                    res *= Double.parseDouble(part[++i]);
                    break;
                case '/':
                    if (Double.parseDouble(part[i + 1]) == 0) {
                        System.out.println("ДЕЛЕНИЕ НА НОЛЬ НЕВЫПОЛНИМО. Продолжение работы программы невозможно");
                        System.exit(1);
                    } else {
                        res /= Double.parseDouble(part[++i]);
                        break;
                    }

            }
        }
        return (res);
    }
}
