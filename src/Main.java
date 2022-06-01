import java.util.ArrayDeque;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static int waitDoorsInSeconds = 10;
    public static int waitMoveInSeconds = 5;


    public static void main(String[] args) throws RuntimeException {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> floors = new ArrayDeque<>();
        int input;
        int previousFloor = -1;
        int totalSeconds = 0;
        while (true) {

            System.out.println("Ожидаю ввода этажа: (для завершения введите 0).");
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неправильный формат ввода, используйте числа для ввода этажа.\n");
                scanner.next();
                continue;
            }
            if (input == 0) {
                break;
            }
            if (input == previousFloor) {
                System.out.println("Вы уже находитесь на этом этаже, введите другой этаж для продолжения движения лифта.");
                continue;
            }
            if (input < 0 || input > 25) {
                System.out.println("Этажа " + input + " нет в доме.");
            } else {
                if (previousFloor != -1) {
                    totalSeconds += Math.abs((input - previousFloor)) * waitMoveInSeconds + waitDoorsInSeconds;
                }
                previousFloor = input;
                changeFloor(floors, input);
            }
        }
        floorMovement(floors, totalSeconds);
    }

    public static void changeFloor(Queue<Integer> floors, Integer input) {
        floors.offer(input);
    }

    public static void floorMovement(Queue<Integer> floors, int totalSeconds) {
        System.out.println("Лифт проследовал по следующим этажам:");
        while (!floors.isEmpty()) {
            System.out.print("Этаж " + floors.poll() + " -> ");
        }
        System.out.println("Этаж 0");
        System.out.println("Время затраченное лифтом на маршрут = " + totalSeconds + " сек.");
    }
}