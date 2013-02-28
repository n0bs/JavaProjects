//Pratique en Futur Irregulier is a program to aid in the memorization of irregular French future tense verb stems
//Copyright (C) 2013  Norberto Monarrez
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.

package oui;

import java.util.*;

public class Main {
	
	//declare the verbs infinitives and their future tense stems
	public static String[] inf = {"aller", "avoir", "etre", "faire", "savoir", "falloir", "valoir", "vouloir", "pouvoir", "cueillir", "s'asseoir", "apercevoir", "decevoir", "devoir", "pleuvoir", "recevoir", "courir", "mourir", "voir", "envoyer", "tenir", "venir"};
	public static String[] root = {"ir", "aur", "ser", "fer", "saur", "faudr", "vaudr", "voudr", "pourr", "cueiller", "s'asseier", "apercevr", "decevr", "devr", "pleuvr", "recevr", "courr", "mourr", "verr", "enverr", "tiendr", "viendr"};
	
	static ArrayList<Integer> used = new ArrayList<Integer>();//This ArrayList tracks the already used verbs
	static int score;//this int keeps track of the user's number of correct answers
	static int over;//and this one keeps track of the number of verbs quizzed
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);//initializing scanner
		int choice;//int to store user menu choices
		
		System.out.println("Bienvenue sur la pratique pour les verbs irreguliers au futur!\n");
		
		do{
			
			System.out.println("1) Donnez-moi un verbe!");
			System.out.println("2) J'ai fini avec la pratique!");
			if(used.size()==22)	//Checks for completion of all verbs
				choice = 2;		//forces user to quit if all verbs are done
			else
				choice = in.nextInt();//instructs user to choose if not yet completed
			
			switch(choice){
			case 1://case to respond to user entry of wanting another verb
					over++;//adds one to the amount of verbs quizzed
					if(verb(in))//A verb is given, user asked to give stem, and the checked for accuracy
						score++;//if answer is right, adds one to score
					break;
			case 2://responds to quit by doing nothing
					break;
			default://default to catch any stray entries
					System.out.println("Ce n'est pas une entrée valide. Essayez à nouveau.");
					break;
			}//ending bracket for switch/case
			
		}while(choice != 2);//end of do-while
		
		System.out.println("Votre score est "+score+"/"+over);//gives user score
		System.out.println("Merci pour avoir utilisé ce programme!");
		in.next();//allows user to read their score
		in.close();//closes Scanner to prevent resource leak
	}//end bracket for Main

	public static boolean verb(Scanner in){//method to choose random verb, ask for stem, check validity, and return the outcome of question
		
		Random gen = new Random();//initialize random num generator
		int infit;//int to store number of random verb
		String base;//string to store user answer
		
		infit = gen.nextInt(22);//generates random number from 0 to 21
		
		while(check(infit) == true)	//this while statement is to prevent repetition
			infit = gen.nextInt(22);//of verbs. Repeats until unique verb is chose
		used.add(infit);
		
		System.out.println(inf[infit]);//print the verb for the user to find the stem of
		
		base = in.next();//takes in user input for answer
		
		if(root[infit].equals(base)){				//if statement checks user answer against accepted answer
			System.out.println("\nC'est exact!\n");	//gives feedback to user that answer was correct
			return true;
		}
		else{//if answer is not correct, will inform user and return a false
			System.out.println("\nC'est incorrect. La bonne réponse est "+ root[infit]+"\n");
			return false;
		}
	}//end bracket for verb(Scanner)
	
	public static boolean check(int infit){//method checks the chosen verb of verb(Scanner) against previoudly used verbs
		int size = used.size();//obtains and stores size of ArrayList containing used verbs
		
		for(int i=0; i < size; i++){//for that runs from 0 to last ArrayList element
			if(used.get(i)==infit)	//compares proposed number of verb against number of used verb in slot i
				return true;		//returns true if match is found
		}
		return false;//if no match is found after checking all ArrayList slots, will return false to signal unique verb
	}//end bracket for check(int)
}//final ending bracket
