package tfr.LostAndFoundAPP.DTO.entities;

import tfr.LostAndFoundAPP.entities.Role;

public class RoleDTO {

    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(String authority) {
        this.authority = authority;
    }

    public RoleDTO(Role entity) {
        authority = entity.getAuthority();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}