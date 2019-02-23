package sam.has.parking;

public class UserInformation {

    public String name;
    public double lati;
    public double longi;
    public String id;



    public UserInformation()
    {

    }

    UserInformation(String id,String name,double lati,double longi)
    {
        this.name = name;
        this.lati = lati;
        this.longi = longi;
        this.id=id;
    }
}
