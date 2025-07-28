package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import tfr.LostAndFoundAPP.entities.enums.TYPEOFINTERACTION;

@Entity
@Table(name="order_item")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer type;

    public OrderItem(){

    }

    public OrderItem(UserAPP userapp,ItemLost itemLost, TYPEOFINTERACTION type) {
        id.setUserAPP(userapp);
        id.setItemLost(itemLost);
        this.type = type.getCod();
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
