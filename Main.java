/* @author Nand Patel
 * @email pateln@students.dsbn.org or nandpatel14@gmail.com
 * @about This program will take runners' names and times from a 100m race and output new information. The data is provided from a separate file.
 * @version 1.1
 * @notes Minor bug fix involving incorrectly functioning outputFastestEachRun method. 
*/

//Imported scanners for user input and file scanning.
import java.io.*;
import java.util.Scanner;

class Main {

  /*This method outputs each runners' times, from order of first run to third run.
  Please note that parrallel arrays were used in all methods in this program.
  */
  public static void outputResults (String[] names, double[] firstRun, double[] secondRun, double[] thirdRun) {

    //Constant for the total number of runners.
    final int numOfRunners = 4;

    System.out.print("\nThe following times are in order of first run to last run.\n\n");

    //Outputs each runners' name and times.
    for (int i = 0; i < numOfRunners; i++) {

      System.out.print(names[i] + ": " + firstRun[i] + "s, " + secondRun[i] + "s, " + thirdRun[i] + "s\n");

    }

  }

  //Outputs the average time from all three runs for each runner.
  public static void outputAvgTime (String[] names, double[] firstRun, double[] secondRun, double[] thirdRun) {

    final int numOfRunners = 4;

    System.out.print("\nThe following times are an average of the runners' three races.\n\n");

    for (int i = 0; i < numOfRunners; i++) {

      //Calculates the average.
      double avgRunTime = (firstRun[i] + secondRun[i] + thirdRun[i]) / 3;

      //Rounds the average to the second decimal place.
      avgRunTime = avgRunTime * 100;
      avgRunTime = Math.round(avgRunTime);
      avgRunTime = avgRunTime / 100; 

      //Outputs runner's name with their average time.
      System.out.print(names[i] + " had an average time of " + avgRunTime + "s\n");

    }

  }
  
  //This method is for outputting the fastest runner for each run.
  public static void outputFastestEachRun (String[] names, double[] firstRun, double[] secondRun, double[] thirdRun) {

    final int numOfRunners = 4; 

    /*Temporarily initiialized both variables using real data instead of 0, because no runner could finish in under 0 seconds. These variables are used to hold the
    fastest runner's name and time.
    */
    String fastestRunner = names[0];
    double fastestTime = firstRun[0];

    /*Compares the current fastest time with new time in data, and a new fastest time and runner is assigned if the if statement is true.
    */
    for (int i = 0; i < numOfRunners; i++) {

      if (fastestTime > firstRun[i]) {

        fastestTime = firstRun[i];
        fastestRunner = names[i];

      }

    }
    
    //Outputs fastest runner for the first run.
    System.out.print("\nIn the first run, " + fastestRunner + " was the fastest runner with a time of " + fastestTime + "s\n\n");

    //Temporarily reassigns values to data from the second run.
    fastestTime = secondRun[0];
    fastestRunner = names[0];

    for (int i = 0; i < numOfRunners; i++) {

      if (fastestTime > secondRun[i]) {

        fastestTime = secondRun[i];
        fastestRunner = names[i];

      }

    }

    System.out.print("In the second run, " + fastestRunner + " was the fastest runner with a time of " + fastestTime + "s\n\n");

    //Temporarily reassigns values to data from the third run.
    fastestTime = thirdRun[0];
    fastestRunner = names[0];

    for (int i = 0; i < numOfRunners; i++) {

      if (fastestTime > thirdRun[i]) {

        fastestTime = thirdRun[i];
        fastestRunner = names[i];

      }

    }

    System.out.print("In the third run, " + fastestRunner + " was the fastest runner with a time of " + fastestTime + "s\n\n");

  }
  
  //Outputs the overall fastest runner.
  public static void outputFastestRunner (String[] names, double[] firstRun, double[] secondRun, double[] thirdRun) {

    final int numOfRunners = 4;

    String fastestRunner = names[0];
    double fastestTime = firstRun[0];

    //The following will find the fastest runner's name and time.
    for (int i = 0; i < numOfRunners; i++) {
      
      /*If statements were used instead of else if statements because the program needs to consider not only the data from first run, but the second and third one as well no matter what. If I had not done this, the program would skip the else if statements as long as the first if statment was true.
      */
      if (fastestTime > firstRun[i]) {

        fastestTime = firstRun[i];
        fastestRunner = names[i];

      }

      if (fastestTime > secondRun[i]) {

        fastestTime = secondRun[i];
        fastestRunner = names[i];

      }

      if (fastestTime > thirdRun[i]) {

        fastestTime = thirdRun[i];
        fastestRunner = names[i];

      }

    }
    
    //Outputs the fastest runner's name and time.
    System.out.print("\n" + fastestRunner + " was the fastest runner with a time of " + fastestTime + " seconds!");

  }

  //The main program starts here.
  public static void main(String[] args) {
    
    //The following variables are for user input, total number of runners, and to confirm the validity of input.
    String userChoice = "";  
    int choice = 0;
    final int numOfRunners = 4;
    boolean invalid = true;

    //Arrays are set up to hold each runner's name and times for each run.
    String names [] = new String [numOfRunners];
    double firstRun [] = new double [numOfRunners];
    double secondRun [] = new double [numOfRunners];
    double thirdRun [] = new double [numOfRunners];

    //A try... catch statement is used to prevent the program from crashing if there is a problem with the data file.
    try {
      
      //Searches for a file with the name "runnersData" in a DAT format. 
      FileReader file = new FileReader("runnersData.dat");
      BufferedReader buffer = new BufferedReader(file);

      //The file data is scanned and assigned to parallel arrays, which will be used in the methods later on.
      for (int i = 0; i < numOfRunners; i++) {

        names[i] = buffer.readLine();
        firstRun[i] = Double.parseDouble(buffer.readLine());
        secondRun[i] = Double.parseDouble(buffer.readLine());
        thirdRun[i] = Double.parseDouble(buffer.readLine());

      }
      buffer.close();

    }

    /*If the file was not found, then the following message is outputted and the program ends.
    */
    catch (IOException e) {

      System.out.print("\nSorry, the file containing runners' names and times was not found. Please ensure that your file is called \"runnersData\" with a \"DAT\" format and try again.");
     
      System.exit(0);

    }

    /*If the file was found, but the data within is invalid, then the following message is outputted and the program ends.
    */
    catch (NumberFormatException e) {

      System.out.print("\nThe file was found, but the data within it was invalid. Please ensure the file contains valid data and try again.");

      System.exit(0);

    }

    /*As long as the user has not chosen "5", which exits the program, this while loop containing the menu options will continue running. 
    */
    while (choice != 5) {

     //Resets boolean 'invalid' to true again so the menu is not skipped.
     invalid = true;

     //A user menu is outputted displaying options.    
      while (invalid == true) {
       
        //Prompts the user to choose an option.
        System.out.print("\n\nPlease select an option by entering an integer:\n\n");
      
        //Options 1-4 are for outputting new data based on the original data.
        System.out.print("1... Output Runners' Times In Order of First Run to Third Run\n");
        System.out.print("2... Output Runners' Average Time\n");
        System.out.print("3... Output Fastest Runner For Each Run\n");
        System.out.print("4... Output Fastest Runner\n");
      
        //Option 5 is to exit the program.
        System.out.print("5... Exit Program\n");

        //User input is read and assigned to 'userChoice'.
        Scanner keyedInput = new Scanner(System.in);
        userChoice = keyedInput.nextLine();

        //A try...catch statement is used to check for valid input.
        try {
        
          /*The string in 'userChoice' is parsed into an integer, which is assigned to 'choice'. 
          */
          choice = Integer.parseInt(userChoice);

          /*If the user has inputted an integer that was not an option on the user menu, then the following message is displayed and the menu is outputted again. 
          */
          if (choice > 5 || choice < 1) {

            System.out.print("Sorry, that was not valid input. Please enter an integer from 1 to 5.\n");
            break;

          }
        
          //Boolean "invalid" is set to false so the program can move on and use the methods.
          invalid = false; 

        }
      
        /*If the user entered something other than an integer, the following message is displayed and then the menu is outputted again. 
        */
        catch (NumberFormatException e) {

          System.out.print("Sorry, that was not valid input. Please enter an integer from 1 to 5.\n");

        } 
      
      }
    
      /*A switch statement with the "choice" variable is used to determine which case to run. The actions of each case can be derived from the user menu. The actual result is calculated and outputted using the methods in the beginning of the program.
      */ 
      switch (choice) {

        case 1: {

          outputResults (names, firstRun, secondRun, thirdRun);
          break;

        }

        case 2: {

          outputAvgTime (names, firstRun, secondRun, thirdRun);
          break;

        }

        case 3: {

          outputFastestEachRun (names, firstRun, secondRun, thirdRun);
          break;

        }

        case 4: {

          outputFastestRunner (names, firstRun, secondRun, thirdRun);
          break;

        }
      
        /* This case is the only one without a break statement, because the user has chosen for the program to end. 
        */
        case 5: {

          System.out.print("\nThank for using the program. Goodbye."); 

        }

      }

    }
    
  }

}
