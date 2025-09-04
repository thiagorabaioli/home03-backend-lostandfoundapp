package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_item_lost")
public class ItemLost {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private boolean status;
    private String location;
    private String whoFind;
    private String description;
    private LocalDate foundDate;
    private String imgUrl;

    @OneToOne(mappedBy = "itemLost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Delivery delivery;

    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "collection_center_id")
    private CollectionCenter collectionCenter;

    @OneToMany(mappedBy = "itemLost")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public ItemLost(){
        
    }

    public ItemLost(Long id, boolean status, String location, String whoFind, String description, LocalDate foundDate, String imgUrl) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.whoFind = whoFind;
        this.description = description;
        this.foundDate = foundDate;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWhoFind() {
        return whoFind;
    }

    public void setWhoFind(String whoFind) {
        this.whoFind = whoFind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // Adicionar Getter e Setter para o novo campo
    public CollectionCenter getCollectionCenter() {
        return collectionCenter;
    }

    public void setCollectionCenter(CollectionCenter collectionCenter) {
        this.collectionCenter = collectionCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemLost itemLost = (ItemLost) o;
        return Objects.equals(id, itemLost.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
