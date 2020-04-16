import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordMasher
{

    public static void main(String[] args) throws FileNotFoundException {

        //Welcome's everyone to this lovely program
        System.out.println("-*-*-|| Hello and welcome to Mart's Charter Wifi Password Generator ||-*-*-");

        //Calls getAjdChoice Method and asks user to pick a file
        File adjFile = new File(WordMasherWork.getAdjChoice());
        //If said file doesn't exist it keeps looping til the file exists. (to prevent crashing later)
        while(!adjFile.exists()) {
            System.out.println("Sorry Bruh -- File doesn't exist");
            adjFile = new File(WordMasherWork.getAdjChoice());
        }
        //Calls getNounChoice Method and asks user to pick a file
        File nounFile = new File(WordMasherWork.getNounChoice());
        //If said file doesn't exist it keeps looping til the file exists. (to prevent crashing later)
        while(!nounFile.exists()) {
            System.out.println("Sorry Bruh -- File doesn't exist -- Try again");
            nounFile = new File(WordMasherWork.getNounChoice());
        }
        //Calls arrMaker Method and passes the adjective file. Sends back an array with
        //all the adjectives and stores it in "adjList". Same for nounFile--->nounList
        ArrayList<String> adjList =  WordMasherWork.arrMaker(adjFile);
        ArrayList<String> nounList = WordMasherWork.arrMaker(nounFile);

        //Calls getNumChoice method asking the user for number between 100-999.
        //Stores result in userNum
        int userNum = WordMasherWork.getNumChoice();

        // Calls runningNums int WordMasherWork and passes the adjective and noun array
        // Along with the user's number from getNumChoice
        String[] numResults  = WordMasherWork.runningNums(adjList, nounList, userNum);

        //Sends all info in another array to display results to show user all the fancy numbers
        WordMasherWork.displayResults(numResults);

        //Calls printString in WordMasherWork -- seeing if they want to print the huge file of word combos
        WordMasherWork.printString(adjList,nounList,userNum);


    }



}
