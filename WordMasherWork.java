import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class WordMasherWork
{
    //Prompts the user to choose from a list of word lists containing adjectives or input their own list.
    //Returns string of filename
    public static String getAdjChoice() {
        
        Scanner keyboard = new Scanner(System.in);
        String result;
        int adjChoice;

        do {
            System.out.println("Please choose an adjective list (Enter 1 - 5): ");
            System.out.println("[1] 1 Syllable Adjectives");
            System.out.println("[2] 2 Syllable Adjectives");
            System.out.println("[3] 3 Syllable Adjectives");
            System.out.println("[4] 4 Syllable Adjectives");
            System.out.println("[5] 5 Import my own list");
            adjChoice = keyboard.nextInt();
        } while (adjChoice < 1 || adjChoice > 5);

        if (adjChoice == 1)
            result = "1syllableadjectives.txt";
        else if (adjChoice == 2)
            result = "2syllableadjectives.txt";
        else if (adjChoice == 3)
            result = "3syllableadjectives.txt";
        else if (adjChoice == 4)
            result = "4syllableadjectives.txt";
        else
        {
            keyboard.nextLine();
            System.out.println("Enter file name: ");
            result = keyboard.nextLine();
        }
        return result;
    }
    //Prompts the user to choose from a list of word lists containing nouns or input their own list.
    //Returns string of filename
    public static String getNounChoice() {
        
        Scanner keyboard = new Scanner(System.in);
        String result;
        int nounChoice;

        do {
            System.out.println("Now choose a list of nouns: (Enter 1 - 5): ");
            System.out.println("[1] 1 Syllable Nouns");
            System.out.println("[2] 2 Syllable Nouns");
            System.out.println("[3] 3 Syllable Nouns");
            System.out.println("[4] 4 Syllable Nouns");
            System.out.println("[5] 5 Import my own list");
            nounChoice = keyboard.nextInt();

        } while (nounChoice < 1 || nounChoice > 5);

        if (nounChoice == 1)
            result = "1syllablenouns.txt";
        else if (nounChoice == 2)
            result = "2syllablenouns.txt";
        else if (nounChoice == 3)
            result = "3syllablenouns.txt";
        else if (nounChoice == 4)
            result = "4syllablenouns.txt";
        else
        {
            System.out.println("Enter file name: ");
            keyboard.nextLine();
            result = keyboard.nextLine();
        }
        return result;
    }

    //Prompts the user for a number between 100 and 999 that will go at the end of the Wifi Password.
    //Utilizes a do while loop to make sure the input is within the range. Returns int.
    public static int getNumChoice() {
        Scanner keyboard = new Scanner(System.in);
        int numChoice;
        do {
            System.out.println("Lastly, please enter a number from 100 - 999");
            numChoice = keyboard.nextInt();
        } while (numChoice < 100 || numChoice > 999);

        return numChoice;
    }

    //Asks the user if they would like to see examples of word combinations from the 2 lists + number
    //If yes - asks them for how many (1-100) - using do/while loop to make sure they are in range.
    //Returns number as int. If no returns 0;
    public static int getNumExamples()
    {
        int numChoice = 0;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Would you like to see some examples of possible combos??");
        System.out.println("[Y]es");
        System.out.println("[N]o");
        String choice = keyboard.nextLine();

        if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes"))
        {
            do {
                System.out.println("How many?? (Enter 1 - 100)");
                numChoice = keyboard.nextInt();
            }while(numChoice<1 || numChoice>100);
        }

        return numChoice;
    }

    //Takes in a file selected by the user -- scans it -- then converts it to and ArrayList
    public static ArrayList<String> arrMaker(File file) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(file);

        ArrayList<String> arr = new ArrayList<>();
        while(scanner.hasNext())
        {
            String temp = scanner.nextLine();
            arr.add(temp);
        }
        scanner.close();
        return arr;
    }

    // Alot of the number crunching gets done here. This takes in 2 arrays(adj/noun) and the number
    // chosen by the user.
    public static String[] runningNums(ArrayList<String> arr1, ArrayList<String> arr2, long userNum)
    {

        double byteCount;
        double arrBytes1 = 0;
        double arrBytes2 = 0;
        int arrCount1 = 0;
        int arrCount2 = 0;
        int count1 = 0;
        int count2 = 0;
        int numExamples = getNumExamples();
        long possComb = arr1.size()*arr2.size()*(userNum-99);//Possible combinations
        //calls randomExample and passes the possible combinations as well as
        //calling getNumExamples to see how many examples user wants then passing that to randomExample
        long[] rand = randomExample(arr1.size(), arr2.size(), userNum, numExamples);
        System.out.println("Rand.length =" + rand.length);
                //used to transport all info from here.
        String[] bigNums  = new String[numExamples+5];

        for(long randTest: rand)
            System.out.println(randTest);

        // These for loops run through each array. Counts total number of characters in each array.
        // Also pulls string from part of the array that matches up with random number.
        // Then combines them in an "adjective + noun + number" form. Does this as many times as the
        // user requested.
        for (String temp1 : arr1)
        {
            arrBytes1 += temp1.length(); //counts total number of characters in Adj array
            if(arrCount1 < numExamples && rand[arrCount1] == count1)
            {
                bigNums[arrCount1 + 5] = "" + temp1;
                arrCount1++;
            }
            count1++;
        }
        for (String temp2 : arr2)
        {
            arrBytes2 += temp2.length(); //counts total number of characters in Noun array
            if(arrCount2 < numExamples && rand[arrCount2+numExamples] == count2)
            {
                bigNums[arrCount2 + 5] = bigNums[arrCount2 + 5] + temp2;
                arrCount2++;
            }
            count2++;
        }
        for (int i = 0; i < numExamples; i++)
            bigNums[i+5] = "" + bigNums[i+5] + rand[i + numExamples+numExamples];

        //byteCount == the avg # of chars per line when combined together. This is used to calculate size later.
        //            shows the avg of arr1     avg of arr2 (had to / by arr1.size & arr2.size + 3 for number
        byteCount = (arrBytes1/arr1.size()) + (arrBytes2/(arr2.size()) + 3) ;
        bigNums[0] = "" + possComb; //possible combos
        bigNums[1] = "" + byteCount; //avg string length
        bigNums[2] = "" + arr1.size();
        bigNums[3] = "" + arr2.size();
        bigNums[4] = "" + userNum;

        return bigNums;
    }

    //Prompts the user to decide if they want to print the huge list of words or not
    // If so it asks them for a file name and prints it out. If not - says goodbye and exits.
    public static void printString(ArrayList<String> arr1, ArrayList<String> arr2, long userNum)
            throws FileNotFoundException
    {

        System.out.println("Now that you have seen the file size you're working with.");
        System.out.println("Would you still like to print these word combos to a file?? [Y/N]");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine();

        if(userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y"))
        {
            System.out.println("Please enter a file name");
            String fileName = keyboard.nextLine();
            PrintWriter file = new PrintWriter(fileName);
            System.out.println("**Printing....**");
            //Nested for loop runs through all possible combinations
            for (String temp1 : arr1) {
                for (String temp2 : arr2) {
                    for (int k = 100; k <= userNum; k++) {
                        file.println(temp1+temp2+k);
                    }
                }
            }
        }
            System.out.println("Alright-- thanks for using Mart's Charter Wifi Password Maker");
            System.out.println("                  Have a nice day :)");
    }


    //Takes in ref of each array, 3 digit number and number of examples user requested
    //Creates for each array plus the 3 digit number (all in the range of their lengths)
    //Sends array result back to runningNums in this form:
    // [---Adj Array RNs---Noun Array RNs---3 digit RNs---]
    // Each is numExamples tokens long.
    public static long[] randomExample(int arr1, int arr2, long userNum, int numExamples)
    {
        long[] result = new long[numExamples*3];

        for(int i = 0; i < result.length/3; i++)
        {
            result[i] = ThreadLocalRandom.current().nextLong(arr1);
            result[i+numExamples] = ThreadLocalRandom.current().nextLong(arr2);
            result[i+numExamples+numExamples] = ThreadLocalRandom.current().nextLong(100,userNum);
        }
        Arrays.sort(result,0,numExamples);
        Arrays.sort(result, numExamples, numExamples+numExamples);

        return result;
    }

    //Displays all the results and also acts as a pseudo main() --
    public static void displayResults(String[] numResults)
    {
        double totalCombos = Double.parseDouble(numResults[0]);
        double avgWordLen = Double.parseDouble(numResults[1]);
        int adjListSize = Integer.parseInt(numResults[2]);
        int nounListSize = Integer.parseInt(numResults[3]);
        int userNum = Integer.parseInt(numResults[4]);

        double sizeGigs = (totalCombos * avgWordLen) / 1073741824.00;

        System.out.println("Okee Dokee. Here's some info about the files you've chosen so far");
        System.out.println("Total Adjectives: " + adjListSize);
        System.out.println("Total Nouns: " + nounListSize);
        System.out.println("Your Number: " + userNum);
        System.out.printf("Total possible combinations: %,.0f\n" , totalCombos);
        System.out.printf("Avg String length: %,.2f\n", avgWordLen);
        System.out.printf("Estimated File size: %,.2f" , sizeGigs);
        System.out.println(" GB");
        System.out.println("Example Output:");

        for (int i = 5; i < numResults.length; i++)
            System.out.println("-*-*-|| " + numResults[i]+ " ||-*-*-");


    }

}
