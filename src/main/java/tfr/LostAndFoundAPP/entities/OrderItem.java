package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.*;
import tfr.LostAndFoundAPP.entities.enums.TYPEOFINTERACTION;

import java.time.Instant;

@Entity
@Table(name="order_item")
public class OrderItem {

   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Integer type;
    private String notes;
    private Instant interactionDate;

    @ManyToOne
    @JoinColumn(name = "userapp_id")
    private UserAPP userAPP;



    @ManyToOne
    @JoinColumn(name = "item_lost_id")
    private ItemLost itemLost;

    public OrderItem(){

    }

    public OrderItem(UserAPP userAPP, ItemLost itemLost, TYPEOFINTERACTION type, String notes, Instant interactionDate) {
        this.userAPP = userAPP;
        this.itemLost = itemLost;
        this.type = type.getCod();
        this.notes = notes;
        this.interactionDate = interactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemLost getItemLost() {
        return itemLost;
    }

    public void setItemLost(ItemLost itemLost) {
        this.itemLost = itemLost;
    }

    public UserAPP getUserAPP() {
        return userAPP;
    }

    public void setUserAPP(UserAPP userAPP) {
        this.userAPP = userAPP;
    }


    public Instant getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(Instant interactionDate) {
        this.interactionDate = interactionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    public TYPEOFINTERACTION getType() {
        return TYPEOFINTERACTION.toEnum(type);
    }

    public void setType(TYPEOFINTERACTION type) {
        this.type = type.getCod();
    }
}
