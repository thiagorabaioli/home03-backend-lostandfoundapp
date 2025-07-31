package tfr.LostAndFoundAPP.DTO.entities;



import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.entities.UserAPP;

import java.time.Instant;

public class OrderItemDTO {

    private Long id;
    private Integer type;
    private String notes;
    private Instant interactionDate;
    private UserAPP userAPP;


    private ItemLost itemLost;

    public OrderItemDTO() {}

    public OrderItemDTO(Long id, Integer type, String notes, Instant interactionDate, UserAPP userAPP, ItemLost itemLost) {
        this.id = id;
        this.type = type;
        this.notes = notes;
        this.interactionDate = interactionDate;
        this.userAPP = userAPP;
        this.itemLost = itemLost;
    }

    public OrderItemDTO(OrderItem entity) {
        id = entity.getId();
        type = entity.getType().getCod();
        notes = entity.getNotes();
        interactionDate = entity.getInteractionDate();
        userAPP = entity.getUserAPP();
        itemLost = entity.getItemLost();

    }

    public Long getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getInteractionDate() {
        return interactionDate;
    }

    public UserAPP getUserAPP() {
        return userAPP;
    }


    public ItemLost getItemLost() {
        return itemLost;
    }
}
