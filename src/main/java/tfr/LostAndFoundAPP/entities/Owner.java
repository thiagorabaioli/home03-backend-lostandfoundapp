package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tb_owner")
public class Owner extends Delivery {

    private String name;
    private String email;
    private String contact;
    private String location;

    public Owner(){}

    public Owner(Long id, LocalDate deliveryDate, String name, String location, String contact, String email) {
        super(id, deliveryDate);
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
