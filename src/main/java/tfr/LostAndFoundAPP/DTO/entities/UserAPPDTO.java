package tfr.LostAndFoundAPP.DTO.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import tfr.LostAndFoundAPP.entities.UserAPP;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserAPPDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @NotBlank
    @Email
    private String email;


    private String porNumber;
    private LocalDate birthDate;
    private String password;

    @ElementCollection
    @CollectionTable(name = "phone_number")
    private Set<String> phoneNumber = new HashSet<>();


    private List<RoleDTO> roles = new ArrayList<>();

    public UserAPPDTO(){}

    public UserAPPDTO(Long id, String name, String email, String porNumber, LocalDate birthDate, String password, Set<String> phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.porNumber = porNumber;
        this.birthDate = birthDate;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public UserAPPDTO(UserAPP entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        porNumber = entity.getPorNumber();
        birthDate = entity.getBirthDate();
        password = entity.getPassword();
        roles = entity.getRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPorNumber() {
        return porNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getPhoneNumber() {
        return phoneNumber;
    }

    public List<RoleDTO> getRoles() { // MODIFICAR ESTE MÉTODO
        return roles;
    }
}
