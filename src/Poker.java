/**
 *  COMP90041 - Programming and Software Development.
 *  Project - Main Class
 *
 *  This is the main class of the poker game. The aim of this class is input
 *  detection(if any errors), cards spread, cards classification and make a
 *  decision on who wins or draws.
 *
 *  @author Chongzheng Zhao <Login ID: Chongzhengz>
 */
import java.util.Arrays;
public class Poker {
    /**
     *  This main function can use CardBuilder(class) to build valid cards to
     *  detect any wrong input arguments, use Player(class) to build player
     *  object and can use many useful functions like getSortedRank, use
     *  Classification(class) to classify the cards held by each player and
     *  make decision on who will wins or draws regarding the rule of the game.
     */
    public static void main(String[] args) {
        // Make sure arguments converted to uniformly uppercase
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].toUpperCase();
        }

        // Use cardBuilder to build valid cards for validation check
        cardBuilder newGame = new cardBuilder();
        newGame.buildValidCard();

        // Check validation of card number
        if (args.length <= 0 || args.length % 5 != 0) {
            System.out.println
                    ("Error: wrong number of arguments; must be a multiple of 5");
            System.exit(0);
        }

        // Check validation of card name by 'contains' function of cardBuilder
        for (String arg : args) {
            if (!newGame.contains(arg)) {
            System.out.println("Error: invalid card name '" + arg + "'");
            System.exit(0);
            }
        }

        // Calculate the number of player
        int playerNum = (args.length + 1) / 5;

        // Create each player objects and seperate cards to each of them
        player[] player = new player[playerNum];
        for (int i = 0; i < playerNum; i++) {
            player[i] = new player(Arrays.copyOfRange(args, 5 * i,
                    5 * (i + 1)));
        }

        /*
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

        // Init an array and store the class code of each player
        int[] playerCodeList = new int[playerNum];
        for (int i = 0; i < playerNum; i++) {
            playerCodeList[i] = player[i].getCode();
        }

        // Init an array to store index of the players who have the same
        // minimal class code
        int[] sameMinCat = new int[playerNum];
        int sameCount = 0;

        // Init an array to store index of the players who win
        int[] winnerID = new int[playerNum];

        // Count the number of winner, and start from 1
        int winnerCount = 0;

        // Find the minimal class code of all the player
        int minCode;
        Arrays.sort(playerCodeList);
        minCode = playerCodeList[0];

        // Find players whose code is minimal in code array
        for (int i = 0; i < playerNum; i++) {
            if (player[i].getCode() == minCode) {
                sameMinCat[sameCount] = i;
                sameCount++;
            }
        }

        // Get the most largest card rank of each player
        int maxFifth = 0;
        int maxFifthCount = 0;
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[4] > maxFifth) {
                maxFifth = player[sameMinCat[i]].getSortedRank()[4];
            }
        }
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[4] == maxFifth) {
                maxFifthCount++;
            }
        }

        // Get the forth largest card rank of each player
        int maxForth = 0;
        int maxForthCount = 0;
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[3] > maxForth) {
                maxForth = player[sameMinCat[i]].getSortedRank()[3];
            }
        }
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[3] == maxForth) {
                maxForthCount++;
            }
        }

        // Get the third largest card rank of each player
        int maxThird = 0;
        int maxThirdCount = 0;
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[2] > maxThird) {
                maxThird = player[sameMinCat[i]].getSortedRank()[2];
            }
        }
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[2] == maxThird) {
                maxThirdCount++;
            }
        }
        // Get the second largest card rank of each player
        int maxSecond = 0;
        int maxSecondCount = 0;
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[1] > maxSecond) {
                maxSecond = player[sameMinCat[i]].getSortedRank()[1];
            }
        }
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[1] == maxSecond) {
                maxSecondCount++;
            }
        }
        // Get the minimal card rank of each player
        int maxFirst = 0;
        //int maxFirstCount = 0;
        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getSortedRank()[0] > maxFirst) {
                maxFirst = player[sameMinCat[i]].getSortedRank()[0];
            }
        }

        // Get the most largest value of r1 in all players
        int[] r1Array = new int[sameCount];
        int highR1Count = 0;

        for (int i = 0; i < sameCount; i++) {
            r1Array[i] = player[sameMinCat[i]].getR1();
        }
        Arrays.sort(r1Array);
        int maxR1 = r1Array[sameCount - 1];

        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getR1() == maxR1) {
                highR1Count++;
            }
        }

        // Get the most largest value of r2 in all players
        int[] r2Array = new int[sameCount];
        int highR2Count = 0;

        for (int i = 0; i < sameCount; i++) {
            r2Array[i] = player[sameMinCat[i]].getR2();
        }
        Arrays.sort(r2Array);
        int maxR2 = r2Array[sameCount - 1];

        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getR2() == maxR2) {
                highR2Count++;
            }
        }

        // Get the most largest value of extraInfo in all players
        int[] extraInfoArray = new int[sameCount];
        int highEFCount = 0;

        for (int i = 0; i < sameCount; i++) {
            extraInfoArray[i] = player[sameMinCat[i]].getExtraInfo();
        }
        Arrays.sort(extraInfoArray);
        int maxExtraInfo = extraInfoArray[sameCount - 1];

        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getExtraInfo() == maxExtraInfo) {
                highEFCount++;
            }
        }

        // Get the most largest value of extraInfo2 in all players
        int[] extraInfo2Array = new int[sameCount];
        int highEF2Count = 0;

        for (int i = 0; i < sameCount; i++) {
            extraInfo2Array[i] = player[sameMinCat[i]].getExtraInfo2();
        }
        Arrays.sort(extraInfo2Array);
        int maxExtraInfo2 = extraInfo2Array[sameCount - 1];

        for (int i = 0; i < sameCount; i++) {
            if (player[sameMinCat[i]].getExtraInfo2() == maxExtraInfo2) {
                highEF2Count++;
            }
        }

        // Get the most largest value of extraInfo3 in all players
        int[] extraInfo3Array = new int[sameCount];

        for (int i = 0; i < sameCount; i++) {
            extraInfo3Array[i] = player[sameMinCat[i]].getExtraInfo3();
        }
        Arrays.sort(extraInfo3Array);
        int maxExtraInfo3 = extraInfo3Array[sameCount - 1];


        // If only one player has the minimal class code, the player wins.
        if (sameCount == 1) {
            winnerID[0] = sameMinCat[0];
            winnerCount = 1;
        }

        // If more than one players have the minimal class code, we need to make
        // decision on who wins or they are draw
        int judgementCode = -1;
        if (sameCount > 1) {
            if (minCode == 1 || minCode == 4 || minCode == 5 || minCode == 9) {
                judgementCode = 1;
            } else if (minCode == 2) {
                judgementCode = 2;
            } else if (minCode == 3) {
                judgementCode = 3;
            } else if (minCode == 6) {
                judgementCode = 4;
            } else if (minCode == 7) {
                judgementCode = 5;
            } else if (minCode == 8) {
                judgementCode = 6;
            }
        }

        switch (judgementCode) {
            /*
             Make decision on the players who class code is 1 or 4 or 5 or 9
             Hint: Anyone who has the most largest of fifth largest card wins.
             If the fifth largest card same, compare the forth largest card and
             so on.
             So use counter of maxFifth to maxFirst to see whether we should
             check further.
             */
            case 1:
                for (int i = 0; i < sameCount; i++) {
                    if (maxFifthCount == 1 &&
                            player[sameMinCat[i]].getSortedRank()[4]
                                    == maxFifth) {
                        winnerID[winnerCount] = i;
                        winnerCount++;
                    }
                    if (maxFifthCount > 1 && maxForthCount == 1 &&
                            player[sameMinCat[i]].getSortedRank()[4]
                                    == maxFifth &&
                            player[sameMinCat[i]].getSortedRank()[3]
                                    == maxForth) {
                        winnerID[winnerCount] = i;
                        winnerCount++;
                    }
                    if (maxFifthCount > 1 && maxForthCount > 1
                            && maxThirdCount == 1 &&
                            player[sameMinCat[i]].getSortedRank()[4]
                                    == maxFifth &&
                            player[sameMinCat[i]].getSortedRank()[3]
                                    == maxForth &&
                            player[sameMinCat[i]].getSortedRank()[2]
                                    == maxThird) {
                        winnerID[winnerCount] = i;
                        winnerCount++;
                    }
                    if (maxFifthCount > 1 && maxForthCount > 1
                            && maxThirdCount > 1 && maxSecondCount == 1 &&
                            player[sameMinCat[i]].getSortedRank()[4]
                                    == maxFifth &&
                            player[sameMinCat[i]].getSortedRank()[3]
                                    == maxForth &&
                            player[sameMinCat[i]].getSortedRank()[2]
                                    == maxThird &&
                            player[sameMinCat[i]].getSortedRank()[1]
                                    == maxSecond) {
                        winnerID[winnerCount] = i;
                        winnerCount++;
                    }
                    if (maxFifthCount > 1 && maxForthCount > 1 &&
                            maxThirdCount > 1 && maxSecondCount > 1 &&
                            player[sameMinCat[i]].getSortedRank()[4]
                                    == maxFifth &&
                            player[sameMinCat[i]].getSortedRank()[3]
                                    == maxForth &&
                            player[sameMinCat[i]].getSortedRank()[2]
                                    == maxThird &&
                            player[sameMinCat[i]].getSortedRank()[1]
                                    == maxSecond &&
                            player[sameMinCat[i]].getSortedRank()[0]
                                    == maxFirst) {
                        winnerID[winnerCount] = i;
                        winnerCount++;
                    }
                }
                break;
            /*
             Make decision on the players who class code is 2
             Hint: Anyone who has the most largest value of r1 wins. If the r1
             value same, compare the fifth card which store in 'extraInfo'. It
             is possible that no one wins. Simply use counter of r1 to see
             whether we should check further.

             r1 - a set of four cards of the same rank
             extraInfo - any other card except the four cards
             */
            case 2:
                for (int i = 0; i < sameCount; i++) {
                    if (highR1Count == 1 &&
                            player[sameMinCat[i]].getR1() == maxR1) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }

                    if (highR1Count > 1
                            && player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                }
                break;
            /*
             Make decision on the players who class code is 3
             Hint: Anyone who has the most largest value of r1 wins. If the r1
             value same, compare the most largest value of r2. It is possible
             that no one wins. Simply use counter of r1 to see whether we should
             check further.

             r1 - three cards of one rank
             r2 - two cards of one rank
             */
            case 3:
                for (int i = 0; i < sameCount; i++) {
                    if (highR1Count == 1
                            && player[sameMinCat[i]].getR1() == maxR1) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }

                    if (highR1Count > 1
                            && player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getR2() == maxR2) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                }
                break;
            /*
             Make decision on the players who class code is 6
             Hint: Anyone who has the most largest value of r1 wins. If the r1
             value same, compare the most largest value of extraInfo. If they
             both same, compare the extraInfo2. Simply use counter of r1,
             extraInfo to see whether we should check further.

             r1 - 3 cards of the same rank
             extraInfo - the higher rank one of any other 2 cards
             extraInfo2 - the last one of any other 2 cards
             */
            case 4:
                for (int i = 0; i < sameCount; i++) {
                    if (highR1Count == 1
                            && player[sameMinCat[i]].getR1() == maxR1) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highEFCount == 1
                            && player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highEFCount > 1
                            && player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo &&
                            player[sameMinCat[i]].getExtraInfo2()
                                    == maxExtraInfo2) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                }
                break;
            /*
             Make decision on the players who class code is 7
             Hint: Anyone who has the most largest value of r1 wins. If the r1
             value same, compare the most largest value of r2. If they both
             same, compare the extraInfo. Simply use counter of r1, r2 to see
             whether we should check further.

             r1 - 2 cards of one higher rank
             r2 - 2 cards of another rank
             extraInfo - any other card
             */
            case 5:
                for (int i = 0; i < sameCount; i++) {
                    if (highR1Count == 1
                            && player[sameMinCat[i]].getR1() == maxR1) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highR2Count == 1
                            && player[sameMinCat[i]].getR1() == maxR1
                            && player[sameMinCat[i]].getR2() == maxR2) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highR2Count > 1 &&
                            player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getR2() == maxR2 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                }
                break;
            /*
             Make decision on the players who class code is 8
             Hint: Anyone who has the most largest value of r1 wins. If the r1
             value same, compare the most largest value of extraInfo. If they
             both same, compare the extraInfo2 and so on. Simply use counter of
             r1, extraInfo, extraInfo2 to see whether we should check further.
             r1 - 2 cards of one rank

             extraInfo - the higher rank one of any other 3 cards
             extraInfo2 - the middle rank one of any other 3 cards
             extraInfo3 - the last one of any other 3 cards
             */
            case 6:
                for (int i = 0; i < sameCount; i++) {
                    if (highR1Count == 1 &&
                            player[sameMinCat[i]].getR1() == maxR1) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highEFCount == 1 &&
                            player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highEFCount > 1
                            && highEF2Count == 1 &&
                            player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo &&
                            player[sameMinCat[i]].getExtraInfo2()
                                    == maxExtraInfo2) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;
                    }
                    if (highR1Count > 1 && highEFCount > 1 &&
                            highEF2Count > 1 &&
                            player[sameMinCat[i]].getR1() == maxR1 &&
                            player[sameMinCat[i]].getExtraInfo()
                                    == maxExtraInfo &&
                            player[sameMinCat[i]].getExtraInfo2()
                                    == maxExtraInfo2 &&
                            player[sameMinCat[i]].getExtraInfo3()
                                    == maxExtraInfo3) {
                        winnerID[winnerCount] = sameMinCat[i];
                        winnerCount++;

                    }
                }
                break;

            default:
                break;
        }

        // Build a list to store the winner in printing format
        StringBuilder playerList = new StringBuilder();
        if (winnerCount == 1) {
            playerList.append(winnerID[0] + 1);
        }
        else if (winnerCount == 2) {
            playerList.append(winnerID[0] + 1).
                    append(" and ").append(winnerID[1] + 1);
        }
        else if (winnerCount > 2) {
            for (int i = 0; i < winnerCount - 2; i++) {
                playerList.append(winnerID[i] + 1).append(", ");
                if (i == winnerCount - 3) {
                    playerList.append(winnerID[i + 1] + 1).
                            append(" and ").append(winnerID[i + 2] + 1);
                }
            }
        }

        // Description: Print out classification description for each player
        for (int i = 0; i < playerNum; i++) {
            System.out.println("Player " + (i + 1) + ": " +
                    player[i].getDescription());
        }

        // Draw: If draw happens, print out who draws
        if (playerNum > 1 && winnerCount > 1) {
            System.out.println("Players " + playerList.toString() + " draw.");
        }
        // Win: If one player wins, print out it
        else if (playerNum > 1 && winnerCount == 1) {
            System.out.println("Player " + playerList.toString() + " wins.");
        }
    }
}