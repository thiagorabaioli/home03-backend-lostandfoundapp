package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import tfr.LostAndFoundAPP.entities.enums.TYPEOFINTERACTION;

import java.time.Instant;

@Entity
@Table(name="order_item")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer type;
    private String notes;
    private Instant interactionDate;

    public OrderItem(){

    }

    public OrderItem(UserAPP userapp,ItemLost itemLost, TYPEOFINTERACTION type, String notes, Instant interactionDate) {
        id.setUserAPP(userapp);
        id.setItemLost(itemLost);
        this.type = type.getCod();
        this.notes = notes;
        this.interactionDate = interactionDate;
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
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

    public UserAPP getUserAPP() {
        return id.getUserAPP();
    }

   public ItemLost getItemLost() {
        return id.getItemLost();
   }


    public TYPEOFINTERACTION getType() {
        return TYPEOFINTERACTION.toEnum(type);
    }

    public void setType(TYPEOFINTERACTION type) {
        this.type = type.getCod();
    }
}
