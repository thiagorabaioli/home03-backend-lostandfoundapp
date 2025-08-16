package tfr.LostAndFoundAPP.DTO.entities;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class BatchDeliveryDTO {

    @NotBlank(message = "O nome do centro de recolha é obrigatório.")
    private String centerName;

    @NotNull(message = "A data da entrega é obrigatória.")
    private LocalDate deliveryDate;

    @AssertTrue(message = "É necessário aceitar os termos de recebimento.")
    private boolean termsAccepted;

    @NotEmpty(message = "É necessário selecionar pelo menos um item.")
    private List<Long> itemIds;

    // Getters
    public String getCenterName() {
        return centerName;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public boolean isTermsAccepted() {
        return termsAccepted;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }
}