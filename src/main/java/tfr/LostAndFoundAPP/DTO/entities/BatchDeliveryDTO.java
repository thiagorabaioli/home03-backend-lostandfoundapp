package tfr.LostAndFoundAPP.DTO.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class BatchDeliveryDTO {

    @NotBlank(message = "O nome do centro de recolha é obrigatório.")
    private String centerName;

    @NotEmpty(message = "É necessário selecionar pelo menos um item.")
    private List<Long> itemIds;

    // Getters
    public String getCenterName() {
        return centerName;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }
}