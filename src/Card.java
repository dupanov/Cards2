/*
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */


/*
 * In this version of the Card class, a deck is an array
 * of Cards, and everything is written as a class method.
 */

    class Card {
        int suit, rank;
        private static int[] points =  {10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        static String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "Jack", "Queen", "King" };

        /*
         * No-argument constructor.
         */
        public Card() {
            this.suit = 0;  this.rank = 0;
        }

        /*
         * Constructor with arguments.
         */
        public Card(int suit, int rank) {
            this.suit = suit;  this.rank = rank;
        }

        /*
         * Prints a card in human-readable form.
         */
        public static void printCard(Card c) {
            System.out.println(ranks[c.rank] + " of " + suits[c.suit]);
        }



        /*
         * Compares two cards: returns 1 if the first card is greater
         * -1 if the seconds card is greater, and 0 if they are the equivalent.
         */
        public static int compareCards(Card c1, Card c2) {
            // compare the suits
            if (c1.suit > c2.suit) return 1;
            if (c1.suit < c2.suit) return -1;

            // if the suits are the same,
            // use modulus arithmetic to rotate the ranks
            // so that the Ace is greater than the King.
            // WARNING: This only works with valid ranks!
               int rank1 = (c1.rank + 11) % 13;
              int rank2 = (c2.rank + 11) % 13;

            // compare the rotated ranks

            if (rank1 > rank2) return 1;
            if (rank1 < rank2) return -1;

            return 0;
        }

    /*
     * Return true if the cards are equivalent.
     */
    public static boolean sameCard(Card c1, Card c2) {
        return (c1.suit == c2.suit && c1.rank == c2.rank);
    }

    /*
     * Compares two cards: returns 1 if the first card is greater
     * -1 if the seconds card is greater, and 0 if they are the equivalent.
     */


    /*
     * Makes an array of 52 cards.
     */
    public static Card[] makeDeck() {
        Card[] cards = new Card [52];

        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(suit, rank);
                index++;
            }
        }
        return cards;
    }

    /*
     * Prints an array of cards.
     */
    public static void printDeck(Card[] cards) {
        for (int i=0; i<cards.length; i++) {
            printCard(cards[i]);
        }
    }

    /*
     * Returns index of the first location where card appears in cards.
     * Or -1 if it does not appear.  Uses a linear search.
     */
    public static int findCard (Card[] cards, Card card) {
        for (int i = 0; i< cards.length; i++) {
            if (sameCard(cards[i], card)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Returns index of a location where card appears in cards.
     * Or -1 if it does not appear.  Uses a bisection search.
     *
     * Searches from low to high, including both.
     *
     * Precondition: the cards must be sorted from lowest to highest.
     */
    public static int findBisect(Card[] cards, Card card, int low, int high) {
        if (high < low) return -1;

        int mid = (high + low) / 2;
        int comp = compareCards(card, cards[mid]);

        if (comp == 0) {
            return mid;
        } else if (comp < 0) {
            // card is less than cards[mid]; search the first half
            return findBisect(cards, card, low, mid-1);
        } else {
            // card is greater; search the second half
            return findBisect(cards, card, mid+1, high);
        }
    }

    /**
     * Calculate hand score method
     * */
    public static int handScore (Card[] deck){
        int sum=0;
        for (int i = 0; i < deck.length ; i++) {
            sum +=points[deck[i].rank];
        }
        return sum;
    }


    public static int[] suitHist (Card[] hand){
        int[] hist = new int[4];
        for (int i = 0; i < hand.length; i++) {
            hist[hand[i].suit]++;
        }
        return hist;
    }

    public static boolean hasFlush(Card[] hand){
        int[] hist = suitHist(hand);
        for (int i = 0; i < hist.length; i++) {
            if( hist[i] >= 5 ) return true;
        }
        return false;
    }


}



