/********************************************** 
Workshop # 5
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-08
**********************************************/ 

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
	//for generatign random words for player
	Random rand = new Random();
	Scanner input = new Scanner(System.in);
	ArrayList<String> magicWordsArr;
	public Hangman(ArrayList<String> magicWordsArr) {
		this.magicWordsArr = magicWordsArr;
	};
	
	public void play() {
		Boolean quit = false;
		do {
			int missedCount = 0;
			String triedLetters = "";
			String userInput;
			// randomly select a word from the arraylist
			String gameWord = magicWordsArr.get(rand.nextInt(magicWordsArr.size()));
			String answer = "";
			for(int i = 0; i < gameWord.length(); i++) {
				answer += "*";
			}
			do {
				int inTheWordCount = 0;
				
				do {
					System.out.print("(Guess) Enter a letter in word " + answer + "> ");
					 userInput = input.next();
				}while(userInput.length() != 1);
				//convert the input into a char
				char letter = userInput.charAt(0);			
				for(int i = 0; i < gameWord.length(); i++) {
					if(answer.charAt(i) == letter) {
						System.out.println("       " + letter + " is already in the word");
						inTheWordCount++;
						break;
						
					}else {
						if(gameWord.charAt(i) == letter) {
							answer = answer.substring(0,i) + letter + answer.substring(i+1, answer.length());
							inTheWordCount++;
						}				
					}				
				}	
				
				if(inTheWordCount == 0) {
					if(triedLetters.contains(userInput)) {
						System.out.println("Your have already tried " + letter + ", try a new letter");
					}else {
						System.out.println("      " + letter + " is not in the word");
						triedLetters+=letter;
						//System.out.println(triedLetters);
						missedCount++;
					}		
				}
			}while(!answer.equals(gameWord));
			
			System.out.println("The word is " + gameWord + ". You missed " + missedCount + " time");
			getNewWord();
			String qInput;
			do {
				System.out.print("Do you want to guess another word? Enter Y or N > ");
				qInput = input.next();
			}while(!qInput.toLowerCase().equals("y") && !qInput.toLowerCase().equals("n"));
			if(qInput.toLowerCase().equals("n")) {
				quit = true;
			}
		}while(!quit);	
	}
	
	public ArrayList<String> getWordsArr(){
		return this.magicWordsArr;
	}
	public void getNewWord() { 
		String newWord;
		Boolean duplicate = false;
		do {
			System.out.print("Enter a new word to be added in the memory> ");
			newWord = input.next();
			for(String str : magicWordsArr) {
				if(str.equals(newWord)) {
					System.out.println("This word already exists in the file");
					duplicate = true;
					break;
			}
			duplicate = false;
			//System.out.println();
			}
		}while(duplicate);
		this.magicWordsArr.add(newWord);		
	}
}
