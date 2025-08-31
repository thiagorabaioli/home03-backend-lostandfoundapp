package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("COLLECTION_CENTER")
public class CollectionCenter extends Delivery {

    private String name;
    private String location;


    @OneToMany(mappedBy = "collectionCenter")
    private Set<ItemLost> items = new HashSet<>();

    public CollectionCenter() {}

    // Getters e Setters
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
}