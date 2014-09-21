import java.io.*;

public class Solution {

  // total score to be returned
  private static int tallyScore = 0;

  // Number of turns taken
  private static int takenTurns = 1;

  public static void main(String[] args) {

    int noStrike = 0;
    String line = null, previousLine=null;//>>>>>>>>>>
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(args[0]));
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    try {
      while ((line = bufferedReader.readLine()) != null) {
        
       
        //second attempt at the current turn
        if (noStrike == 1){
          takenTurns++;
          noStrike = 0;
        }
          if (readScore(line) == 1) {
            takenTurns += 1;
            
            //if you score a strike at the tenth turn roll two more times
            if(10 == takenTurns){
              //first extra roll
              readScore(bufferedReader.readLine());
              //second extra roll
              readScore(bufferedReader.readLine());
              break;
            }
            continue;
          } else {
            
            noStrike++;
          }
          
          //check for errors in the recording of a score
          if(readScore(line) == -100){
            System.out.println("Go back and check the score card, invalid number found");
            System.exit(0);
          }
        previousLine = line;//>>>>>>>>>>>>>
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("Individual XYZ scored: " + tallyScore);
  }

  /**
   * 
   * Takes in the string and converts it to an integer.
   * Adds that integer to the total tally.
   * 
   * returns one if a strike was scored and zero otherwise
   * @param scored
   * @return
   */
  public static int readScore(String scored) {

    //find the number that corresponds to the input
    for (int i = 0; i <= 10; i++) {
      if (Integer.parseInt(scored) == i) {
        tallyScore += i;
        if (i == 10) {
          // no need to roll again
          return 1;
        } else {
          // did not get a strike
          return 0;
        }
      }// end of if statement
    }// end of for loop

    // error in scoring
    return -100;
  }
}
