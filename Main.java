import java.util.Scanner;
public class Main {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Ваше выражение: ");
            String input = scan.nextLine();
            input = input.replace(" ","");
            System.out.println(calc(input));

    }
        public static String calc(String input) {
            Mapping conv = new Mapping();
            String[] actions = {"+", "-", "/", "*"};
            String[] regexActions = {"\\+", "-", "/", "\\*"};
            String output;

            long sum = count(input, '+') + count(input, '-') + count(input, '*') + count(input, '/');
            if (sum > 1) {
                throw new Error("Введено более одного оператора");
            }
            int actionIndex = -1;
            for (int i = 0; i < actions.length; i++) {
                if (input.contains(actions[i])) {
                    actionIndex = i;
                    break;
                }
            }
            if (actionIndex == -1) {
                throw new Error("Строка не является математической операцией");
            }

            String[] data = input.split(regexActions[actionIndex]);
            if (conv.isRoman(data[0]) == conv.isRoman(data[1])) {
                int x, y;
                boolean isRoman = conv.isRoman(data[0]);
                if (isRoman) {

                    x = conv.romanToInt(data[0]);
                    y = conv.romanToInt(data[1]);

                } else {
                    x = Integer.parseInt(data[0]);
                    y = Integer.parseInt(data[1]);
                }
                if(x<1 || x>10 || y<1 || y>10) {
                    throw new Error("При вводе допустимы числа от 1 до 10 включительно");
                }
                int result = switch (actions[actionIndex]) {
                    case "+" -> x + y;
                    case "-" -> x - y;
                    case "*" -> x * y;
                    default -> x / y;
                };
                if(isRoman){
                    if(result >= 1) {
                        output = conv.intToRoman(result);
                    }
                    else {
                        throw new Error("В римской системе есть только положительные числа");
                    }
                }
                else{
                    output = Integer.toString(result);
                }
            }else{
                throw new Error("Вы допустили ошибку при вводе римских чисел");
            }

            return output;
        }
            private static Long count (String input,char c){
                return input.chars().filter(x -> x == c).count();
            }
        }
