package games;

import org.slf4j.Logger;

import java.io.IOException;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды
    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока
    private static int[] playersMoney = {100, 100};

    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static final int MAX_VALUE_FOR_COMP_DECISION = 19;
    private static final int BET = 10;

    public static void main(String[] args) throws IOException {
        while (playersMoney[0] > 0 && playersMoney[1] > 0) {
            initRound();
            playerGetCards(0);
            playerGetCards(1);
            showCurrentResult();
        }
        if (playersMoney[0] > 0)
            log.info("You win! Congratulations!");
        else
            log.info("You lose. Don't play again...");
    }

    private static void initRound() {
        log.info("\nYou have " + playersMoney[0] + "$, computer have - " + playersMoney[1] + "$. Let's go!");
        cards = CardUtils.initDeck();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }

    private static void playerGetCards(int player) throws IOException {
        addCardToPlayerUI(player);
        addCardToPlayerUI(player);
        String msgToConfirm = "Do you need any more? ";
        if (player == 0) {
            while (sum(0) < MAX_VALUE - 1 && confirm(msgToConfirm)) {
                addCardToPlayerUI(0);
            }
        } else {
            while (sum(1) < MAX_VALUE_FOR_COMP_DECISION) {
                addCardToPlayerUI(1);
            }
        }

    }

    private static void addCardToPlayerUI(int player) {
        String msg = player == 0 ? "you get - " : "comp get - ";
        log.info(msg + CardUtils.toString(addCardToPlayer(player)));
    }

    private static int addCardToPlayer(int player) {
        playersCards[player][playersCursors[player]++] = cards[cursor];
        return cards[cursor++];
    }

    private static int sum(int player) {
        int sum = 0;
        for (int i = 0; i < playersCursors[player]; i++) {
            sum += value(playersCards[player][i]);
        }
        return sum;
    }

    private static void showCurrentResult() {
        int playerSum = getFinalSum(0);
        int compSum = getFinalSum(1);
        log.info("Your scores equals - " + playerSum +
                ", comp scores equals - " + compSum);
        if (playerSum > compSum) {
            log.info("You win current round! And win " + BET + "$");
            playersMoney[0] += BET;
            playersMoney[1] -= BET;
            return;
        }
        if (playerSum < compSum) {
            log.info("You loose current round! And lost " + BET + "$");
            playersMoney[0] -= BET;
            playersMoney[1] += BET;
            return;
        }
        log.info("It's draw");
    }

    private static int getFinalSum(int player) {
        int currentSum = sum(player);
        return currentSum > 21 ? 0 : currentSum;
    }

    static boolean confirm(String message) throws IOException {
        log.info(message + " \"Y\" - Yes, {any another key} - no (to stop the game, press Ctrl + C)");
        switch (Choice.getCharacterFromUser()) {
            case 'Y':
            case 'y':
                return true;
            default:
                return false;
        }
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK:
                return 2;
            case QUEEN:
                return 3;
            case KING:
                return 4;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case ACE:
            default:
                return 11;
        }
    }
}
