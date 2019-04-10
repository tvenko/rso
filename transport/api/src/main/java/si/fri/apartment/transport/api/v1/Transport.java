package si.fri.apartment.transport.api.v1;

public class Transport {

    private String apartmentLocation;
    private String trainStation;
    private String busStation;
    private int trainStationDistance;
    private int busStationDistance;

    public Transport(String loc,String train,String bus,int trDist,int busDis){
        apartmentLocation=loc;
        trainStation=train;
        busStation=bus;
        trainStationDistance=trDist;
        busStationDistance=busDis;
    }

    public String getApartmentLocation(){
        return apartmentLocation;
    }
    public int getTrainStationDistance(){
        return trainStationDistance;
    }
    public int getBusStationDistance(){
        return busStationDistance;
    }
    public String getTrainStation(){
        return trainStation;
    }
    public String getBusStation(){
        return busStation;
    }

}
