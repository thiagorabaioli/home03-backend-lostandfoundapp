package tfr.LostAndFoundAPP.DTO.entities;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class BatchDeliveryDTO {

    @NotBlank(message = "O nome do centro de recolha é obrigatório.")
    private String centerName;


    @NotBlank(message = "O nome de quem recebe é obrigatório.")
    private String receiverName;

    @Email(message = "Por favor, insira um email válido.")
    @NotBlank(message = "O email de quem recebe é obrigatório.")
    private String receiverEmail;

    @NotNull(message = "A data da entrega é obrigatória.")
    private LocalDate deliveryDate;

    @AssertTrue(message = "É necessário aceitar os termos de recebimento.")
    private boolean termsAccepted;

    @NotEmpty(message = "É necessário selecionar pelo menos um item.")
    private List<Long> itemIds;


    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }


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