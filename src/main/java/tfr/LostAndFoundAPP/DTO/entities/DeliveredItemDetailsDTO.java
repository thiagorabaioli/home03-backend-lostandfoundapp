package tfr.LostAndFoundAPP.DTO.entities;

import tfr.LostAndFoundAPP.entities.CollectionCenter;
import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.entities.Owner;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class DeliveredItemDetailsDTO {

    // Dados do ItemLost
    private Long itemId;
    private String description;
    private String imgUrl;
    private LocalDate foundDate;

    // Dados da Entrega (pode vir de Owner ou CollectionCenter)
    private String deliveredToName;
    private String deliveredToEmail;
    private String deliveredToContact;
    private LocalDate deliveryDate;

    // Histórico de Interações (OrderItem)
    private Set<OrderItemDTO> interactions;

    public DeliveredItemDetailsDTO(ItemLost entity) {
        this.itemId = entity.getId();
        this.description = entity.getDescription();
        this.imgUrl = entity.getImgUrl();
        this.foundDate = entity.getFoundDate();

        // --- INÍCIO DA CORREÇÃO ---
        // 1. Verifica se a entrega foi individual (para um Owner)
        if (entity.getDelivery() != null && entity.getDelivery() instanceof Owner) {
            Owner owner = (Owner) entity.getDelivery();
            this.deliveredToName = owner.getName();
            this.deliveredToEmail = owner.getEmail();
            this.deliveredToContact = owner.getContact();
            this.deliveryDate = owner.getDeliveryDate();
        }
        // 2. Se não foi, verifica se foi uma entrega em lote (para um CollectionCenter)
        else if (entity.getCollectionCenter() != null) {
            CollectionCenter center = entity.getCollectionCenter();
            this.deliveredToName = center.getName();
            this.deliveryDate = center.getDeliveryDate();
            // Para entregas em lote, os campos de email e contacto ficam vazios
            this.deliveredToEmail = null;
            this.deliveredToContact = null;
        }
        // --- FIM DA CORREÇÃO ---

        if (entity.getOrderItems() != null) {
            this.interactions = entity.getOrderItems().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toSet());
        }
    }

    // Getters
    public Long getItemId() { return itemId; }
    public String getDescription() { return description; }
    public String getImgUrl() { return imgUrl; }
    public LocalDate getFoundDate() { return foundDate; }
    public String getDeliveredToName() { return deliveredToName; }
    public String getDeliveredToEmail() { return deliveredToEmail; }
    public String getDeliveredToContact() { return deliveredToContact; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public Set<OrderItemDTO> getInteractions() { return interactions; }
}