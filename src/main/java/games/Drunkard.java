package games;


import org.apache.commons.math3.util.MathArrays;

public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = new int[2];
    private static boolean firstWin;

    public static void main(String[] args) {
        int[] deck = initDeck();
        dealCards(deck);
        game();
        if(firstWin){
            System.out.println("выиграл первый");
        } else {
            System.out.println("Выиграл второй");
        }
    }

    private static int[] initDeck(){
        int[] cards = new int[CARDS_TOTAL_COUNT];
        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            cards[i] = i;
        }
        MathArrays.shuffle(cards);
        return cards;
    }

    private static void dealCards(int[] deck){
        int half = CARDS_TOTAL_COUNT/2;
        for (int i = 0; i <half ; i++) {
            playersCards[0][i] = deck[i];
            playersCards[1][i] = deck[half + i];
        }
        playersCardTails[0] = 0;
        playersCardTails[1] = 0;
        playersCardHeads[0] = half;
        playersCardHeads[1] = half;
    }

    private static int getCard(int player){
        int card = playersCards[player][playersCardTails[player]];
        playersCardTails[player] = incrementIndex(playersCardTails[player]);
        return card;
    }

    private static void addCard(int player, int card){
        playersCards[player][playersCardHeads[player]] = card;
        playersCardHeads[player] = incrementIndex(playersCardHeads[player]);
    }


    private static int incrementIndex(int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private static void game(){
        int count = 0;
        while(true){
            if(playerCardsIsEmpty(0) || playerCardsIsEmpty(1)) break;
            count ++;
            int card1 = getCard(0);
            int card2 = getCard(1);
            System.out.println("Итерация №" + count + " Игрок №1 карта: " + toString(card1) +
                    " Игрок №2 карта: " + toString(card2));
            int ignoreSuitCard1 = card1 % PARS_TOTAL_COUNT;
            int ignoreSuitCard2 = card2 % PARS_TOTAL_COUNT;
            if(ignoreSuitCard1 == 0 && ignoreSuitCard2 == 8){
                currentWin(true, card1, card2);
                continue;
            }
            if(ignoreSuitCard1 == 8 && ignoreSuitCard2 == 0){
                currentWin(false, card1, card2);
                continue;
            }
            if(ignoreSuitCard1 > ignoreSuitCard2){
                currentWin(true, card1, card2);
                continue;
            }
            if(ignoreSuitCard1 < ignoreSuitCard2){
                currentWin(false, card1, card2);
                continue;
            }
            draw(card1, card2);
        }
    }

    private static boolean playerCardsIsEmpty(int player) {
        int tail = playersCardTails[player];
        int head = playersCardHeads[player];
        return tail == head;
    }

    private static void currentWin(boolean firstWin, int card1, int card2){
        Drunkard.firstWin = firstWin;
        if(firstWin){
            System.out.println("Выиграл игрок 1!");
            addCard(0, card1);
            addCard(0, card2);
        } else {
            System.out.println("Выиграл игрок 2!");
            addCard(1, card1);
            addCard(1, card2);
        }
    }

    private static void draw(int card1, int card2){
        System.out.println("Спор - каждый остаётся при своих!");
        addCard(0, card1);
        addCard(1, card2);
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK, // Валет
        QUEEN, // Дама
        KING, // Король
        ACE // Туз
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }
}
