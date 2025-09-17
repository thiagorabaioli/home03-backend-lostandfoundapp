package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("COLLECTION_CENTER")
public class CollectionCenter extends Delivery {

    private String name;
    private String location;
    private String receiverName;
    private String receiverEmail;
    private Boolean conditionAccepted;

    @OneToMany(mappedBy = "collectionCenter")
    private Set<ItemLost> items = new HashSet<>();

    public CollectionCenter() {}



    public void setItems(Set<ItemLost> items) {
        this.items = items;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
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

    public Set<ItemLost> getItems() {
        return items;
    }

    public Boolean isConditionAccepted() {
        return conditionAccepted;
    }

    public void setConditionAccepted(Boolean conditionAccepted) {
        this.conditionAccepted = conditionAccepted;
    }
}