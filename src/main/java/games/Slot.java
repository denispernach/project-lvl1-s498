package games;

import org.slf4j.Logger;

public class Slot {
    private static int money = 100;
    private static int bet = 10;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    public static void main(String... __) {
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;
        boolean status;

        while (money >= bet) {
            log.info("You have {}$, the bet is - {}$", money, bet);
            firstCounter = (firstCounter + (int) Math.round(Math.random() * 100)) % size;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * 100)) % size;
            log.info("Let's do it! And we have the following results: ");
            log.info("The first number is - {}, the second - {}, and the third - {}",firstCounter, secondCounter,thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                money += 1_000;
                log.info("you get - 1 000 $, and your money is: {}$ now", money);
            } else {
                money -= bet;
                log.info("you lost {}$, and your money is: {}$ now", bet, money);
            }

        }
    }
}
