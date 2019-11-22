/**
 *  COMP90041 - Programming and Software Development.
 *  Project - Player Class
 *
 *   This class allows us to create multiple players and spread the cards. We
 *   can get sorted card rank, get description regarding card classification,
 *   get the value of r1, r2, extraInfo(1-3) after classification
 *
 *  @author Chongzheng Zhao <Login ID: Chongzhengz>
 */
import java.util.Arrays;

public class player {
    // Init holdCards. It contains cards which player holds.
    private String[] holdCards = new String[5];
    // Init sortedRanks. It contains sorted rank of cards which player holds.
    private int[] sortedRanks = new int[5];
    // Init sortedRanks. It contains sorted suit of cards which player holds.
    private String[] sortedSuits = new String[5];

    /**
     * Player Class - Player Constructor
     *
     * This is the player constructor
     */
    player(String[] cards){
        System.arraycopy(cards, 0, this.holdCards, 0, 5);
    }

    private char getCardRank(int num){
        return holdCards[num].charAt(0);
    }

    private String getCardSuit(int num){
        return String.valueOf(holdCards[num].charAt(1));
    }

    /**
     * Player Class - make ranks sorted
     *
     * This function aims to sort the card ranks of a player. In order to sort
     * the array, the program regards T to 0, J to 11, Q to 12, K to 13 and A
     * to 14.
     *
     * CompareTo function is to compare two elements if they are the same, the
     * return value is 0.
     *
     * @return return an array which contains a sorted ranks
     */
    int[] getSortedRank(){
        String[] temp = new String[5];
        for(int i = 0; i < 5; i++){
            temp[i] = String.valueOf(getCardRank(i));
            if(temp[i].compareTo("T") == 0){
                temp[i] = String.valueOf(10);
            }
            if(temp[i].compareTo("J") == 0){
                temp[i] = String.valueOf(11);
            }
            if(temp[i].compareTo("Q") == 0){
                temp[i] = String.valueOf(12);
            }
            if(temp[i].compareTo("K") == 0){
                temp[i] = String.valueOf(13);
            }
            if(temp[i].compareTo("A") == 0){
                temp[i] = String.valueOf(14);
            }
            sortedRanks[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(sortedRanks);
        return sortedRanks;
    }
    /**
     * Player Class - make suits sorted
     *
     * This function aims to sort card suits of a hand.
     *
     * @return return an array which contains a sorted suits
     */
    private String[] getSortedSuit(){
        for(int i = 0; i < 5; i++){
            sortedSuits[i] = getCardSuit(i);
        }
        Arrays.sort(sortedSuits);
        return sortedSuits;
    }

    /**
     * Player Class - Classify and get description
     *
     * This method allows program to make the cards classify and get the
     * description regarding to the game concept
     *
     * @return return the description
     */
    String getDescription(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getDescription();
    }

    /**
     * Player Class - Get class code
     *
     * Each classification has a code, this method allows to return which
     * classification code the five cards are.
     *
     * @return the class code of the five cards
     */
    public int getCode(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getCurrent_code();
    }

    /*
    Player Class - Value getter(r1, r2, extrainfo 1-3)

    These five method allows to be returned the value of each of r1, r2,
    extra information 1-3. These values are vital in further judgement on
    who wins in Poker Class.
    */
    int getR1(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getR1();
    }

    int getR2(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getR2();
    }

    int getExtraInfo(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getExtraInfo();
    }

    int getExtraInfo2(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getExtraInfo2();
    }

    int getExtraInfo3(){
        classification go = new classification();
        go.startClassification(getSortedRank(), getSortedSuit());
        return go.getExtraInfo3();
    }

}
