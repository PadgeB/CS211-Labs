//Airport class to hold Airport details
public class Airport{
    private static final double eRadius=6386.000;
    private static final double Deg_to_rad=Math.PI/180D;
    private double lon;
    private double lat;
    private String name;

    public Airport(String name,double lat,double lon){
        this.name=name;
        this.lon=lon*Deg_to_rad;
        this.lat=lat*Deg_to_rad;

    }

    public double getLon(){//get longitude
        return this.lon;
    }

    public double getLat(){
        return this.lat;
    }

    public double getDistance(Airport airport){ //calculating distance using formula
        double deltaLongitude=airport.getLon()-this.getLon();
        double deltaLatitude=airport.getLat()-this.getLat();
        double a= Math.pow(Math.sin(deltaLatitude/2D),2D)+
                Math.cos(this.getLat())*Math.cos(airport.getLat())*Math.pow(Math.sin(deltaLongitude/2D),2D);

        return eRadius*2D*Math.atan2(Math.sqrt(a),Math.sqrt(1D-a));
    }
}