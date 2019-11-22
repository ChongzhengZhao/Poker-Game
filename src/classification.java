/**
 * COMP90041 - Programming and Software Development.
 * Project - Classification Class
 *
 * The classification class is to identify which class the input card it is and
 * build its own description. There are nine classifications in total and each
 * of them is identified as a code. Besides the r1 and r2, there are three other
 * values called "extraInfo", "extraInfo2", "extraInfo3" to store information
 * like the last one card except the four same cards to allow the program to
 * further judge on which player will win regarding the extra information.
 *
 * @author Chongzheng Zhao <Login ID: Chongzhengz>
 */
class classification {
    /*
    Init r1, r2, extraInfo, extraInfo2, extraInfo3 to be -1
    For each classification, the meaning of extra information would be different
     */
    private int r1 = -1;
    private int r2 = -1;
    private int extraInfo = -1;
    private int extraInfo2 = -1;
    private int extraInfo3 = -1;
    private String description;
    /*
    Current_code initialize as a large enough value(999), and can use "< and >"
    function to judge and then continue refresh the value, because the category
    is higher as the class code smaller.

    Class Code Standard:
    1 - Straight flush
    2 - Four of a kind
    3 - Full house
    4 - Flush
    5 - Straight
    6 - Three of a kind
    7 - Two pair
    8 - One pair
    9 - High card
    */
    private int current_code = 999;

    /**
     * Classification Class - Start to make decision on category
     *
     * This method calls straight, flush, and n_of_a_kind function to check
     * whether the cards meet each criterion. If meets, each function will
     * automatically refresh the current code, description and some values
     * like r1, r2, extraInfo, etc. If not, it will be considered to be the
     * ninth type called "high card".
     */
    void startClassification(int[] ranks, String[] suits) {
        this.straights(ranks);
        this.flushes(ranks, suits);
        this.n_of_a_kind(ranks);
        if (current_code == 999) {
            current_code = 9;
            r1 = ranks[4];
            description = transToRealRank(r1) + "-high";
        }
    }

    /**
     * Classification - Make judge on straights
     * Class Code -> 5(Straight)
     * <p>
     * Straights type has the obvious characters which the card rank is
     * sequential number after sorted. So check the gap between two conjecture
     * cards is enough.
     */
    private void straights(int[] ranks) {
        int achieveCount = 0;
        for (int i = 0; i < 4; i++) {
            if (ranks[i] - ranks[i + 1] == -1) {
                ++achieveCount;
            }
        }
        // Class code 5 criteria
        if (achieveCount == 4 && current_code > 5) {
            current_code = 5;
            r1 = ranks[4];
            description = transToRealRank(r1) + "-high straight";
        }
    }

    /**
     * Classification Class - Make judge on flushes
     * Class Code -> 1(Straight flush) and 4(Flush)
     * <p>
     * To determine whether the five cards are flushes is easy, just to check
     * whether they have the same suit. To achieve this, I use "compareTo"
     * function.
     */
    private void flushes(int[] ranks, String[] suits) {
        int achieveCount = 0;
        for (int i = 0; i < 4; i++) {
            if (suits[i].compareTo(suits[i + 1]) == 0) {
                ++achieveCount;
            }
        }
        // Class code 1 criteria(it must obey the Class 5 rule at the same time)
        if (achieveCount == 4 && current_code == 5) {
            current_code = 1;
            // The last one card has the most largest ranks
            r1 = ranks[4];
            description = transToRealRank(r1) + "-high straight flush";
        }
        // Class code 4 criteria
        else if (achieveCount == 4 && current_code > 4) {
            current_code = 4;
            // The last one card has the most largest ranks
            r1 = ranks[4];
            description = transToRealRank(r1) + "-high flush";
        }
    }

    /**
     * Classification Class - Make judge on n of a kind
     * Class Code -> 2, 3, 6, 7 and 8
     * <p>
     * Class 2, 3, 6, 7 and 8 all belong to "n of a kind" type. Use "differ"
     * arguments to store the difference between two adjacent card ranks.
     */
    private void n_of_a_kind(int[] ranks) {
        int[] differ = new int[4];
        for (int i = 0; i < 4; i++) {
            differ[i] = ranks[i + 1] - ranks[i];
        }
        // Class code 2 criteria
        for (int i = 0; i < 2; i++) {
            if (differ[i] == 0 && differ[i + 1] == 0 &&
                    differ[i + 2] == 0 && current_code > 2) {
                current_code = 2;
                r1 = ranks[1];
                if (i == 0) {
                    extraInfo = ranks[4];
                } else {
                    extraInfo = ranks[0];
                }
                description = "Four " + transToRealRank(r1) + "s";
                break;
            }

        }
        // Class code 3 criteria
        if (current_code > 3) {
            if (differ[0] == 0 && differ[1] == 0 && differ[3] == 0) {
                current_code = 3;
                r1 = ranks[0];
                r2 = ranks[4];
                description = transToRealRank(r1) + "s full of " +
                        transToRealRank(r2) + "s";
            } else if (differ[0] == 0 && differ[2] == 0 && differ[3] == 0) {
                current_code = 3;
                r1 = ranks[4];
                r2 = ranks[0];
                description = transToRealRank(r1) + "s full of " +
                        transToRealRank(r2) + "s";
            }
        }
        // Class code 6 criteria
        for (int i = 0; i < 3; i++) {
            if (differ[i] == 0 && differ[i + 1] == 0 && current_code > 6) {
                current_code = 6;
                r1 = ranks[i];
                if (i == 0) {
                    extraInfo = ranks[4];
                    extraInfo2 = ranks[3];
                } else if (i == 1) {
                    extraInfo = ranks[4];
                    extraInfo2 = ranks[0];
                } else {
                    extraInfo = ranks[1];
                    extraInfo2 = ranks[0];
                }
                description = "Three " + transToRealRank(r1) + "s";
                break;
            }
        }
        // Class code 7 criteria
        if (current_code > 7) {
            if (differ[0] == 0 && differ[2] == 0) {
                current_code = 7;
                r1 = ranks[2];
                r2 = ranks[0];
                extraInfo = ranks[4];
                description = transToRealRank(r1) + "s over " +
                        transToRealRank(r2) + "s";
            }
            if (differ[0] == 0 && differ[3] == 0) {
                current_code = 7;
                r1 = ranks[3];
                r2 = ranks[0];
                extraInfo = ranks[2];
                description = transToRealRank(r1) + "s over " +
                        transToRealRank(r2) + "s";
            }
            if (differ[1] == 0 && differ[3] == 0) {
                current_code = 7;
                r1 = ranks[3];
                r2 = ranks[1];
                extraInfo = ranks[0];
                description = transToRealRank(r1) + "s over " +
                        transToRealRank(r2) + "s";
            }
        }
        // Class code 8 criteria
        for (int i = 0; i < 4; i++) {
            if (differ[i] == 0 && current_code > 8) {
                current_code = 8;
                r1 = ranks[i];
                if (i == 0) {
                    extraInfo = ranks[4];
                    extraInfo2 = ranks[3];
                    extraInfo3 = ranks[2];
                } else if (i == 1) {
                    extraInfo = ranks[4];
                    extraInfo2 = ranks[3];
                    extraInfo3 = ranks[0];
                } else if (i == 2) {
                    extraInfo = ranks[4];
                    extraInfo2 = ranks[1];
                    extraInfo3 = ranks[0];
                } else {
                    extraInfo = ranks[2];
                    extraInfo2 = ranks[1];
                    extraInfo3 = ranks[0];
                }
                description = "Pair of " + transToRealRank(r1) + "s";
            }
        }
    }

    /**
     * Classification Class - Shown the real rank in description
     * <p>
     * This function is aimed to converted from numeric card rank to letter form
     * only for Jack, Queen, King, and Ace. Other input besides the four kinds
     * will return the original value.
     */
    private String transToRealRank(int input) {
        if (input == 11) {
            return "Jack";
        }
        if (input == 12) {
            return "Queen";
        }
        if (input == 13) {
            return "King";
        }
        if (input == 14) {
            return "Ace";
        }
        return String.valueOf(input);
    }

    int getCurrent_code() {
        return current_code;
    }

    String getDescription() {
        return description;
    }

    int getR1() {
        return r1;
    }

    int getR2() {
        return r2;
    }

    int getExtraInfo() {
        return extraInfo;
    }

    int getExtraInfo2() {
        return extraInfo2;
    }

    int getExtraInfo3() {
        return extraInfo3;
    }
}
