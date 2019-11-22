/**
 *  COMP90041 - Programming and Software Development.
 *  Project - CardBuilder Class
 *
 *  This class aims to build valid card for input error detection.
 *
 *  @author Chongzheng Zhao <Login ID: Chongzhengz>
 */
class cardBuilder {
    // Init an array to store the cards containing rank and suit
    private final String[] cardName = new String[52];
    // Init an array to store the valid rank
    private final String[] cardRank = new String[]
            {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    // Init an array to store the valid suit
    private final String[] cardSuit = new String[]{"C", "D", "H", "S"};
    private int builderCount = 0;

    /**
     * CardBuilder Class - Build valid cards.
     *
     * This function allows the programme to build all 52 valid cards according
     * to the valid rank and suit.
     */
    void buildValidCard() {
        for (String s : cardRank) {
            for (String value : cardSuit) {
                cardName[builderCount] = s + value;
                builderCount++;
            }
        }
    }

    /**
     * CardBuilder Class - Detect whether input is the valid card or not.
     *
     * This function allows to called with a string containing rank and suit
     * and return true or false to show whether it is a valid card or not.
     *
     * @return True means the card is valid; False means the card is invalid.
     */
    boolean contains(String card){
        for (String s : cardName) {
            if (card.equals(s)) {
                return true;
            }
        }
        return false;
    }
}


