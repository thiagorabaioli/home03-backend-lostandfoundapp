package tfr.LostAndFoundAPP.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tfr.LostAndFoundAPP.entities.ItemLost;

import java.time.LocalDate;

public class ItemLostDTO {

    private Long id;
    private boolean status;

    @Size(min = 8, max = 80, message = "Local precisa ter de 8 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String location;

    @Size(min = 8, max = 80, message = "Quem encontrou precisa ter de 8 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String whoFind;

    @Size(min = 15, max = 80, message = "Descrição precisa ter de 15 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;


    private LocalDate foundDate;
    private String imgUrl;

    public ItemLostDTO() {}

    public ItemLostDTO(Long id, boolean status, String location, String whoFind, String description, LocalDate foundDate, String imgUrl) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.whoFind = whoFind;
        this.description = description;
        this.foundDate = foundDate;
        this.imgUrl = imgUrl;
    }

    public ItemLostDTO(ItemLost entity) {
        id = entity.getId();
        status = entity.isStatus();
        location = entity.getLocation();
        whoFind = entity.getWhoFind();
        description = entity.getDescription();
        foundDate = entity.getFoundDate();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getWhoFind() {
        return whoFind;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
