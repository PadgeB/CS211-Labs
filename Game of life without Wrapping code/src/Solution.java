import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//Have some sort of check to see if it goes into the boundary and if it does wrap it to the opposite side


public class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int runs= sc.nextInt();
        sc.nextLine();
        int x=22;int y=22;
        int[][] board = new int[x][y];


//filling original board, Taking strings and creating a 2d int array
        for (int i = 1; i <= 20; i++) {
            String input1=sc.nextLine();
            String input="0"+input1+"0";
            //char c=input.charAt(i);
            //int num= Character.getNumericValue(c);
            for (int j = 1; j <=20 ; j++) {
                //String input=sc.nextLine();
                char c=input.charAt(j);
                int num= Character.getNumericValue(c);
                board[i][j] = num;
            }
        }

       // printBoard(board);
        int[][] finalG=new int[x][y];
        finalG=nextGen(board, x, y,runs);
        boardPrint(finalG);
        System.out.println(countAlive(finalG));

    }


    static int[][] nextGen(int board[][], int x, int y,int runs)
    {
        //int[][] future = new int[x][y];
        for(int k=1;k<=runs;k++) {

            int[][] future = new int[x][y];

            for (int i = 1; i < x-1 ; i++)
            {
                for (int j = 1; j < y-1 ;j++)
                {
                    int neighbours = 0;
                    neighbours += board[i - 1][j - 1];
                    neighbours += board[i][j - 1];
                    neighbours += board[i + 1][j - 1];
                    neighbours += board[i - 1][j];
                    neighbours += board[i + 1][j];
                    neighbours += board[i - 1][j + 1];
                    neighbours += board[i][j + 1];
                    neighbours += board[i + 1][j + 1];

                    //System.out.println(neighbours);

                    if ((board[i][j] == 1) && (neighbours < 2))
                        future[i][j] = 0;

                    else if ((board[i][j] == 1) && (neighbours > 3))
                        future[i][j] = 0;

                    else if ((board[i][j] == 0) && (neighbours == 3))
                        future[i][j] = 1;
                    else
                        future[i][j] = board[i][j];
                }

            }
            board=future;
        }
        return board;
    }

    static int countAlive(int[][] finalG) {
        int count = 0;
        for (int i = 1; i < 21; i++) {
            for (int j = 1; j < 21; j++) {
                if (finalG[i][j] == 0) {

                } else {
                    count++;
                }
            }
        }
        return count;
    }

    //to print board
    static void printBoard(int board[][]){
        System.out.print(Arrays.deepToString(board));
    }

    //printing board in table form
    static void boardPrint(int board[][]){
        System.out.println("---");
        for (int i = 1; i < 21; i++) {
            String line= "|";
            for (int j = 1; j < 21; j++) {
                if(board[i][j]==0){
                    line+=".";
                }else {
                    line += "*";
                }
            }
            line+="|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }
}
