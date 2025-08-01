package tfr.LostAndFoundAPP.DTO.entities;



import tfr.LostAndFoundAPP.entities.OrderItem;

import java.time.Instant;

public class OrderItemDTO {

    private Long id;
    private Integer type;
    private String notes;
    private Instant interactionDate;



    public OrderItemDTO() {}

    public OrderItemDTO(Long id, Integer type, String notes, Instant interactionDate) {
        this.id = id;
        this.type = type;
        this.notes = notes;
        this.interactionDate = interactionDate;

    }

    public OrderItemDTO(OrderItem entity) {
        id = entity.getId();

        // Adicionamos uma verificação para evitar o NullPointerException
        if (entity.getType() != null) {
            type = entity.getType().getCod();
        } else {
            type = null;
        }
        notes = entity.getNotes();
        interactionDate = entity.getInteractionDate();

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

}
