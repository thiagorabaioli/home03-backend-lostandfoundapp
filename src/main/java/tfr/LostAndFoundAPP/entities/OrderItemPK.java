package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK {

    @ManyToOne
    @JoinColumn(name="userapp_id")
    private UserAPP userAPP;

    @ManyToOne
    @JoinColumn(name="itemLost_id")
    private ItemLost itemLost;

    public OrderItemPK(){

    }

    public UserAPP getUserAPP() {
        return userAPP;
    }

    public void setUserAPP(UserAPP userAPP) {
        this.userAPP = userAPP;
    }

    public ItemLost getItemLost() {
        return itemLost;
    }

    public void setItemLost(ItemLost itemLost) {
        this.itemLost = itemLost;
    }


    
}
