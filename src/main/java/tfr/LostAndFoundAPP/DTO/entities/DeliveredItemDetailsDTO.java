package tfr.LostAndFoundAPP.DTO.entities;

import tfr.LostAndFoundAPP.entities.CollectionCenter;
import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.entities.Owner;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class DeliveredItemDetailsDTO {


    private Long itemId;
    private String description;
    private String imgUrl;
    private LocalDate foundDate;


    private String deliveredToName;
    private String deliveredToEmail;
    private String deliveredToContact;
    private String deliveredToLocation;
    private LocalDate deliveryDate;
    private boolean conditionAccepted;
    private Boolean sameCondition;


    private Set<OrderItemDTO> interactions;

    public DeliveredItemDetailsDTO(ItemLost entity) {
        this.itemId = entity.getId();
        this.description = entity.getDescription();
        this.imgUrl = entity.getImgUrl();
        this.foundDate = entity.getFoundDate();

        if (entity.getDelivery() != null && entity.getDelivery() instanceof Owner) {
            Owner owner = (Owner) entity.getDelivery();
            this.deliveredToName = owner.getName();
            this.deliveredToEmail = owner.getEmail();
            this.deliveredToContact = owner.getContact();
            this.deliveryDate = owner.getDeliveryDate();

            this.deliveredToLocation = owner.getLocation();
            this.conditionAccepted = owner.isConditionAccepted();

            this.sameCondition = owner.isSameCondition();

        } else if (entity.getCollectionCenter() != null) {
            CollectionCenter center = entity.getCollectionCenter();
            this.deliveredToName = center.getName();
            this.deliveredToName = center.getReceiverName();
            this.deliveredToEmail = center.getReceiverEmail();
            this.deliveryDate = center.getDeliveryDate();
            this.conditionAccepted = center.isConditionAccepted();
        }

        if (entity.getOrderItems() != null) {
            this.interactions = entity.getOrderItems().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toSet());
        }
    }


    public Long getItemId() { return itemId; }
    public String getDescription() { return description; }
    public String getImgUrl() { return imgUrl; }
    public LocalDate getFoundDate() { return foundDate; }
    public String getDeliveredToName() { return deliveredToName; }
    public String getDeliveredToEmail() { return deliveredToEmail; }
    public String getDeliveredToContact() { return deliveredToContact; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public String getDeliveredToLocation() { return deliveredToLocation; }
    public boolean isConditionAccepted() { return conditionAccepted; }
    public Boolean isSameCondition() { return sameCondition; }
    public Set<OrderItemDTO> getInteractions() { return interactions; }
}