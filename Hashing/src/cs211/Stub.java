package cs211;
import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.lang.Object.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Stub{

    public static void main (String[] args){
        Scanner myscanner = new Scanner(System.in);
        int items = myscanner.nextInt()-1;
        myscanner.nextLine();
        String[] contents = new String[items];
        for(int i=0;i<items;i++){
            contents[i]=myscanner.nextLine();
        }
        String hash = myscanner.nextLine();
        int size=99991;
        Solution mysolution = new Solution();
        String[] hashtable=mysolution.fill(size, contents);
        HashTable mytable = new HashTable(hashtable);

        Solution mysolution2 = new Solution(); //prevents cheating by using memory
        for(int i=0;i<items;i++){
            int rand = (int)(Math.random()*items);
            String temp = contents[i];
            contents[i]=contents[rand];
            contents[rand]=temp;
        }
        int total=0;
        for(int i=0;i<items;i++){
            int slot = mysolution2.find(size, mytable, contents[i]);
            if(!hashtable[slot].equals(contents[i])){
                System.out.println("error!");
            }
        }
        System.out.println("Collisions: " + mytable.gettotal());
        try{
            System.out.println("Your Receipt: "+sha256(hash+mytable.gettotal()));
        }catch(NoSuchAlgorithmException e){};
    }

    public static String sha256(String input) throws NoSuchAlgorithmException {
        byte[] in = hexStringToByteArray(input);
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(in);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if(len%2==1){
            s=s+"@";
            len++;
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}


class HashTable{

    private String[] hashTable;
    private int total=0;

    public HashTable(String[] input){
        hashTable = input;
    }

    public boolean check(int slot, String check){
        if(hashTable[slot].equals(check)){
            return true;
        }else{
            total++;
            return false;
        }
    }

    public int gettotal(){
        return total;
    }
}


class Solution{

    public int find(int size, HashTable mytable, String word){

        int key = 0;
        int tries=0;
        int mult=1;


        for(int i=0;i<word.length();i++){ //CALCULATING KEY
            int temp=0;
            temp=(int)word.charAt(i)*(int)Math.pow(27,tries);
            key+=temp;
            tries++;
        }



        key=key%size; //MOD KEY


        if(key<0){
            key+=size;
        }


        while(mytable.check(key, word)==false){//quad probing

            key=key+(mult*mult);
            mult++;
            if(key>=size-1){
                key=0;
            }

        }

        return key;
    }

    public String[] fill(int size, String[] array){

        //takes in the size of the hashtable, and the array of Strings to be placed in the hashtable
        //this should add all the words into the hashtable using some system
        //then it should return the hashtable array
        int total=0;

        String[] hashtable = new String[size];

        for(int i=0;i<array.length;i++){
            int tot=0;
            int collisions=0;
            int tries =1;
            int mult =0;
            String word=array[i];


            for(int j=0;j<word.length();j++) {
                int temp=0;
                temp = (int) word.charAt(j)*(int)Math.pow(27,mult);
                tot+=temp;
                mult++;
            }

            tot=tot%size;

            if(tot<0)
            {
                tot+=size;
            }

            while(hashtable[tot]!=null){ //quad probing

                tot=tot+(tries*tries);
                tries++;

                if(tot>=size-1){
                    tot=0;
                }

            }


            hashtable[tot]=word;


        }

        return hashtable;

    }
}