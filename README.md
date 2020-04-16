# CharterWifiPasswordCreator
In my experience all Charter Wifi Passwords consist of an adjective + noun + 3 digit number. 
This code prompts the user to choose from a selections of word files
- The first containing all adjectives
- The second containing all nouns
Then it asks the user for a 3 digit number ranging from 100-999.

It then asks if they would like to see some sample word combonations -- and if so how many (1-100).
It also prints out the amount of possible combinations (typically ranging in the billions) along with the estimated file size (in GBs) and the amount of examples they requsted.
Lastly, it asks if they would like to print the absurdly large pool of strings to a file or not. If they select yes it goes about its business producing all possible combinations.
If not it simply says goodbye and ends.

That is all for now. I learned that printing a file with anywhere near these amount of word combos is extremely over the top.
And if i want to go about trying to crack Charter Wifi Passwords it would probably be best to just utilize the different adj/noun word lists LIVE. 


To use:
1. Download all the files into a new folder in your IdeaProjects folder.
2. Move the .java files into the src folder
3. Make sure the txt files are in your parent folder
4. Load up the 2 java files - "WordMasher" has the main() in it.
5. Run it...and whatever you do don't print the files :)
