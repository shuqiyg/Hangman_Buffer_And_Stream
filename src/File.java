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
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
//File class is for handling incoming and outgoing guessing words associated with files
public class File {
	private ArrayList<String> wordsArr;
	private String fileName;
	private BufferedReader br;
	private BufferedWriter bw;
	
	public File(String fileName) {
		setFileName(fileName);
	};
	
	public ArrayList<String> getWordList(){
		return this.wordsArr;
	}
	
	public void appendWords(ArrayList<String> wordsList) {
		this.wordsArr = wordsList;
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), StandardCharsets.UTF_8)))
	    {
	    	for(String str: wordsArr) {
	    		bw.write(str);
	    		bw.write("\n");
	    	}
	    } catch (IOException e) {
			System.out.println("Can not write to the file. Terminating....\n");
			System.exit(1);
		}
		
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void appendWord(String word) {
		wordsArr.add(word);
	}
	public void ParseWordsFromFile() throws IOException {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), StandardCharsets.UTF_8)))
	    {
	    	this.wordsArr = new ArrayList<String>();
	    	//byte[] bytes = new byte[8192];
	    	String word;
	    	while((word = br.readLine()) != null) {
	    		wordsArr.add(word);
	    	}
	    }
	}
	
	public void closeStream() {
		try {
			if(br != null || bw != null) {
				br.close();
				bw.close();
			}
		}catch (IOException ioException) {
			System.err.println("Error Closing files.Terminating...");
			System.exit(1);
		}
	}	
}
