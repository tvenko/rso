package si.fri.apartment.apartments.models;

import javax.persistence.*;

@Entity(name = "apartments")
@NamedQueries({
        @NamedQuery(name = "Apartments.getAll", query = "SELECT a FROM apartments a"),
})
public class ApartmentEntity {

    private long id;
    private String description;
    private String location;
    private int persons;
    private int squareMeters;

    public  ApartmentEntity() {}

    public ApartmentEntity(String description, String location, int persons,int squareMeters) {

        this.location = location;
        this.description=description;
        this.persons=persons;
        this.squareMeters=squareMeters;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 128)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "location", nullable = false, length = 128)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "persons", nullable = false, length = 30)
    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    @Basic
    @Column(name = "squareMeters", nullable = false, length = 30)
    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public String toString() {
        return String.format("%d %s, %s, %d persons %d m2\n", id, description, location, persons, squareMeters);
    }
}
