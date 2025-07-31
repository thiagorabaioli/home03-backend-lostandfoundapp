package tfr.LostAndFoundAPP.DTO.entities;

import tfr.LostAndFoundAPP.entities.Delivery;

import java.time.LocalDate;

public class DeliveryDTO {

    private Long id;
    private LocalDate deliveryDate;

    public DeliveryDTO() {}

    public DeliveryDTO(Long id, LocalDate deliveryDate) {
        this.id = id;
        this.deliveryDate = deliveryDate;
    }

    public DeliveryDTO(Delivery entity) {
        id = entity.getId();
        deliveryDate = entity.getDeliveryDate();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
}
