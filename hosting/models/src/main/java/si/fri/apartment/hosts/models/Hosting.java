package si.fri.apartment.hosts.models;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "hosting")
@NamedQueries({
        @NamedQuery(name = "Hosts.getAll", query = "SELECT h FROM hosting h"),
})
@UuidGenerator(name = "idGenerator")
public class Hosting {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "valid_from")
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;

    @Column
    private String description;

    @Transient
    private UserEntity owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}
