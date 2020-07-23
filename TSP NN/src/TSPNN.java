//TSP By Patrick Brennan - 17473946
import java.util.*;

public class TSPNN {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        //array list for airport objects
        ArrayList<Airport> list = new ArrayList<Airport>();
        //array list for tracking index in NN
        ArrayList<Integer> index=new ArrayList<Integer>();

        //filling index
        for (int i = 0; i <1001 ; i++) {
            index.add(i);
        }

        //filling airport list
        for (int i = 0; i <1001 ; i++) {
            Airport air=new Airport(Integer.toString(i),sc.nextDouble(),sc.nextDouble());
            list.add(air);
        }

        //adj matrix to store distances
        double[][] graph = new double[1001][1001];

        //filling adj matrix with distances
        for (int i = 0; i <graph.length ; i++) {
            Airport a=list.get(i);
            for (int j = 0; j <graph.length ; j++) {
                if(i==j){
                    graph[i][j]=0;//distance from an airport to itself set to 0
                }else {
                    Airport b = list.get(j);
                    graph[j][i]=a.getDistance(b);
                }
            }
        }

        index.set(0,null);
        int count=0;
        int pos=0;//initial pos is 0
        String ans="0,";//starts with 0
        while(count!=1001){


            int nn=getNN(pos,index,graph);//calling NN
            index.set(pos,null);//once a pos is visited set it to null in index so its not repeated
            ans+=nn+",";
            count++;
            pos=nn;

        }


        //Some airports have an issue with the 100k min distance, this adds those missing airports in at the end(only 2)
        for (int i = 0; i <index.size() ; i++) {
            if(index.get(i)!=null){
                String fix=Integer.toString(i);
                ans+="0,"+fix+",";
                index.set(i,null);

            }
        }
        ans+="0"; //ends with 0
        System.out.println(ans);
    }


    //getting Nearest neighbour
    public static int getNN(int pos,ArrayList index,double[][] graph){
        double nn=Integer.MAX_VALUE;//big number to ensure its always replaced
        int newpos=0;//int to track the NN
        for (int i = 0; i <graph.length ; i++) {
            if(index.contains(i)){//if i is a valid index perform NN
                if(graph[pos][i]>101&&graph[pos][i]<nn){ //making sure its over 100km
                    nn=graph[pos][i];
                    newpos=i;
                }
            }else{ //otherwise continue on
                continue;
            }
        }
        return newpos;//returning NN
    }
}