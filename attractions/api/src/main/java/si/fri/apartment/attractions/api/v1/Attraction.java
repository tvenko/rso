package si.fri.apartment.attractions.api.v1;

public class Attraction {

    private int id;

    private String cityName;

    private int population;

    private String area;

    private String[] listOfAttractions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String[] getListOfAttractions() {
        return listOfAttractions;
    }

    public void setListOfAttractions(String[] listOfAttractions) {
        this.listOfAttractions = listOfAttractions;
    }
}
