package games;

public class Slot {

    public static void main(String[] args) {
        int money = 100;
        int bet = 10;
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;
        boolean status;

        while (money >= bet) {
            System.out.println("У Вас " + money + "$, ставка - " + bet + "$");
            firstCounter = (firstCounter + (int) Math.round(Math.random() * 100)) % size;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * 100)) % size;
            System.out.println("Крутим барабаны! Розыгрыш принёс следующие результаты: ");
            System.out.println("первый барабан - " + firstCounter +
                    ", второй - " + secondCounter +
                    ", третий - " + thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                money += 1_000;
                System.out.println("Выигрышь - 1 000 $, Ваш капитал теперь составляет: " + money + "$");
            } else {
                money -= bet;
                System.out.println("Проигрыш " + bet + "$, ваш капитал теперь составляет: " + money + "$");
            }

        }
    }
}
