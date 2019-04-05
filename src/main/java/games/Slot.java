package games;

public class Slot {
    private static int money = 100;
    private static int bet = 10;

    public static void main(String[] args) {
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;
        boolean status;

        while (money >= bet) {
            System.out.println("You have " + money + "$, the bet is - " + bet + "$");
            firstCounter = (firstCounter + (int) Math.round(Math.random() * 100)) % size;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * 100)) % size;
            System.out.println("Let's do it! And we have the following results: ");
            System.out.println("The first number is - " + firstCounter +
                    ", the second - " + secondCounter +
                    ", and the third - " + thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                money += 1_000;
                System.out.println("you get - 1 000 $, and your money is: " + money + "$ now");
            } else {
                money -= bet;
                System.out.println("you loose " + bet + "$, and your money is: " + money + "$ now");
            }

        }
    }
}
