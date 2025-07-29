package tfr.LostAndFoundAPP.DTO;

import jakarta.persistence.*;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.entities.Role;
import tfr.LostAndFoundAPP.entities.UserAPP;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserAPPDTO {

    private Long id;
    private String name;
    private String email;
    private String porNumber;
    private LocalDate birthDate;
    private String password;

    @ElementCollection
    @CollectionTable(name = "phone_number")
    private Set<String> phoneNumber = new HashSet<>();

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
}
