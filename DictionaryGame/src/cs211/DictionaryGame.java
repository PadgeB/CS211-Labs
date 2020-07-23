package cs211;

import java.util.*;

public class DictionaryGame {

    public static void main(String[] args) throws Exception{

        FileIO reader = new FileIO();
        Random rand = new Random();
        Scanner sc=new Scanner(System.in);
        String[] contents = reader.load("C:\\Users\\padge\\Documents\\dictionary\\dictionary.txt");

        int ran = rand.nextInt(contents.length-1); //getting rand num between 0 and length of array

        String word =contents[ran];

        word = word.replace("\n", "").replace("\r", "");
        System.out.println(word);

        String mixed=(jumbleword(word));;
        mixed = mixed.replace("\n", "").replace("\r", ""); // gets rid of break lines
        System.out.println("The jumbled up word is: "+mixed);

        System.out.println("Try and guess the word!");

        for(int i=1;i<=5;i++){
            String input=sc.next();
            String guess=input;

            if(guess.equals(word)){
                System.out.println("YOU ARE CORRECT");
                i=5;
            }
            else{
                System.out.println("INCORRECT "+i+"/5 attempts used");
            }
        }
    }

    public static String jumbleword(String word){
        List<Character> characters = new ArrayList<Character>();
        for(char c:word.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(word.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return(output.toString());

    }
}
