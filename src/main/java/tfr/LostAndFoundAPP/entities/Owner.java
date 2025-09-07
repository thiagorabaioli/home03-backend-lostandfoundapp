package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("OWNER")
public class Owner extends Delivery {

    private String name;
    private String email;
    private String contact;
    private String location;
    private boolean conditionAccepted;
    private Boolean sameCondition;

    public Owner(){}

    public Owner(Long id, LocalDate deliveryDate, String name, String location, String contact, String email, boolean conditionAccepted, Boolean sameCondition) {
        super(id, deliveryDate);
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.email = email;
        this.sameCondition = sameCondition;
        this.conditionAccepted = conditionAccepted;
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

    public boolean isConditionAccepted() {
        return conditionAccepted;
    }

    public Boolean isSameCondition() {
        return sameCondition;
    }

    public void setSameCondition(Boolean sameCondition) {
        this.sameCondition = sameCondition;
    }

    public void setConditionAccepted(boolean conditionAccepted) {
        this.conditionAccepted = conditionAccepted;
    }
}
