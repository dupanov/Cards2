/*
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */


/*
 * A Card represents a playing card with rank and suit.
 */



/*
 * A Deck contains an array of cards.
 */

import java.lang.Math;

class Deck {
    Card[] cards;

    /*
     * Makes a Deck with room for n Cards (but no Cards yet).
     */
    public Deck(int n) {
        this.cards = new Card[n];
    }

    /*
     * Makes an array of 52 cards.
     */
    public Deck() {
        this.cards = new Card [52];

        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(suit, rank);
                index++;
            }
        }
    }

    /*
     * Prints a deck of cards.
     */
    public static void printDeck(Deck deck) {
        for (int i=0; i<deck.cards.length; i++) {
            Card.printCard(deck.cards[i]);
        }
    }

    /*
     * Returns index of the first location where card appears in deck.
     * Or -1 if it does not appear.  Uses a linear search.
     */
    public static int findCard (Deck deck, Card card) {
        for (int i = 0; i< deck.cards.length; i++) {
            if (Card.sameCard(card, deck.cards[i])) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Returns index of a location where card appears in deck.
     * Or -1 if it does not appear.  Uses a bisection search.
     *
     * Searches from low to high, including both.
     *
     * Precondition: the cards must be sorted from lowest to highest.
     */
    public static int findBisect(Deck deck, Card card, int low, int high) {
        if (high < low) return -1;

        int mid = (high + low) / 2;
        int comp = Card.compareCards(card, deck.cards[mid]);

        if (comp == 0) {
            return mid;
        } else if (comp < 0) {
            // card is less than deck.cards[mid]; search the first half
            return findBisect(deck, card, low, mid-1);
        } else {
            // card is greater; search the second half
            return findBisect(deck, card, mid+1, high);
        }
    }

    /*
     * Chooses a random integer between low and high, including low,
     * not including high.
     */
    public static int randInt(int low, int high) {
        int rand = (int)(Math.random()*(high+low)) - low;
        return rand;
    }

    /*
     * Swaps two cards in a deck of cards.
     */
    private static void swapCards(Deck deck, int i, int j) {
        Card temp = null;
        temp = deck.cards[i];
        deck.cards[i] = deck.cards[j];
        deck.cards[j] = temp;
    }

    /*
     * Shuffles the cards in a deck.
     */
    public static void shuffleDeck(Deck deck) {
        int j = 0;
        do{
            for (int i = 0; i < deck.cards.length; i++){
                swapCards(deck, randInt(0, 52), randInt(0,52));
                i++;
            }
            j++;
        }
        while(j<8);

    }

    /*
     * Sorts a deck from low to high.
     */
    public static void sortDeck(Deck deck) {
        int length = deck.cards.length;
        int indexOfLowest;
        for (int i = 0; i < length  ; i++) {
            indexOfLowest = indexLowestCard(deck, i, length-1 );
            swapCards(deck, indexOfLowest, i);
        }

    }

    /*
     * Finds the index of the lowest card between low and high,
     * including both.
     */
    private static int indexLowestCard(Deck deck, int low, int high) {
        Card min =  deck.cards[low];
        Card card1;
        int index = low;
        for(int i=low; i <= high; i++){
            card1 = deck.cards[i];
            if(Card.compareCards(min, card1) != -1){
                min = deck.cards[i];
                index = i;
            }
        }
        return index;
    }

    /*
     * Makes a new deck of cards with a subset of cards from the original.
     * The old deck and new deck share references to the same card objects.
     */
    public static Deck subdeck(Deck deck, int low, int high) {
        Deck sub = new Deck(high-low+1);

        for (int i = 0; i<sub.cards.length; i++) {
            sub.cards[i] = deck.cards[low+i];
        }
        return sub;
    }

    /*
     * Merges two sorted decks into a new sorted deck.
     */
    private static Deck merge(Deck d1, Deck d2) {
        Deck result = new Deck(d1.cards.length + d2.cards.length);
        int i = 0;
        int j = 0;
        int k = 0;
        while (k <result.cards.length) {
            if(Card.compareCards(d1.cards[i],d2.cards[j])==-1){
                    result.cards[k] = d1.cards[i];
                    if(i < d1.cards.length-1){i++;}
            } else{
                    result.cards[k] = d2.cards[j];
                    if(j<d2.cards.length-1){j++;}
                }
        k++;
        }
        return result;
    }

    /*
     * Sort the Deck using merge sort.
     */

    public static Deck mergeSort(Deck deck){
        Deck sub1 = subdeck(deck, 0, ((deck.cards.length - 1) / 2));
        Deck sub2 = subdeck(deck, deck.cards.length-1 - ((deck.cards.length-1)  / 2), deck.cards.length - 1);
        sortDeck(sub1);
        sortDeck(sub2);
        Deck result = Deck.merge(sub1, sub2);
        return result;
    }

/**
    public static Deck mergeSort(Deck deck) {
        if( deck.cards.length <= 1 ){
            return deck;
        }

        return deck.merge(mergeSort(subdeck(deck, 0, (int) Math.ceil((deck.cards.length - 1) / 2))), mergeSort(subdeck(deck, (int) (deck.cards.length-1 - Math.ceil((deck.cards.length-1) / 2)), deck.cards.length-1)));
    }
 */
}
