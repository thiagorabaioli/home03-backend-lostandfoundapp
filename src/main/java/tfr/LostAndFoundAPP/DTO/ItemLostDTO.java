package tfr.LostAndFoundAPP.DTO;

import tfr.LostAndFoundAPP.entities.ItemLost;

import java.time.LocalDate;

public class ItemLostDTO {

    private Long id;
    private boolean status;
    private String location;
    private String whoFind;
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
