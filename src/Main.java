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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> magicWordsArr = new ArrayList<String>(); 
		File gameFile = null;
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		Boolean exit = false;
		System.out.println("********Welcome to HANGMAN GAME********");
		do {
			Boolean fileExist = false;
			System.out.println("\n------Please choose one of the options------\n1. Play Game\n2. Exit\n");
			int mainOption = input.nextInt();
			switch(mainOption) {
			case 1:
				do {
					System.out.print("Please enter a file name you want to load: ");
					String fileName = input.next();
					 gameFile = new File(fileName);
					try {
						gameFile.ParseWordsFromFile();
						fileExist = true;
						break;
					}catch(IOException IOE) {
						System.out.println("File doesn't exist, please try a different file name...\n");
					}
				}while(!fileExist);
				magicWordsArr = gameFile.getWordList();
				Hangman hangMan = new Hangman(magicWordsArr);
				System.out.println("\n***** Now the game starts *****");
				hangMan.play();
				gameFile.appendWords(hangMan.getWordsArr());
				
				break;
			case 2:
				System.out.println("\nThank you for playing Hangman, GoodBye!");
				exit = true;
				break;
			default:
				System.out.println("\nInvalid Option, please try again...\n");
			}
		}while(!exit);	
		gameFile.closeStream();	
	}
}
