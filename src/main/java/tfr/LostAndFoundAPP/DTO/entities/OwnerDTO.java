package tfr.LostAndFoundAPP.DTO.entities;

import tfr.LostAndFoundAPP.entities.Owner;

public class OwnerDTO {

    private String name;
    private String email;
    private String contact;
    private String location;
    private boolean conditionAccepted;

    public OwnerDTO() {}

    public OwnerDTO(String name, String email, String contact, String location, boolean conditionAccepted) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.location = location;
        this.conditionAccepted = conditionAccepted;
    }

    public OwnerDTO(Owner entity) {
        name = entity.getName();
        email = entity.getEmail();
        contact = entity.getContact();
        location = entity.getLocation();
        conditionAccepted = entity.isConditionAccepted();

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getLocation() {
        return location;
    }

    public boolean isConditionAccepted() {return conditionAccepted;}
}
