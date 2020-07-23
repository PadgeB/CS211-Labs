import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class cs211_lab9 {
    public static void main(String args[]) throws Exception{
        Scanner sc=new Scanner (System.in);
        int radius=6371;
        double lat1=sc.nextDouble();
        double lon1=sc.nextDouble();
        double lat2=sc.nextDouble();
        double lon2=sc.nextDouble();
        double latinRad= Math.toRadians(lat2-lat1);
        double loninRad= Math.toRadians(lon2-lon1);

       double p1=Math.sin(latinRad/2);
       double p2=Math.sin(loninRad/2);
        p1=p1*p1;
        p2=p2*p2;


        double f1= p1+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * p2;
        double f2= 2*Math.atan2(Math.sqrt(f1),Math.sqrt(1-f1));
        double sol= radius*f2;


        int ans=(int)sol;
        int rounded=((ans+50)/100)*100;
        System.out.println(rounded);


    }
}
