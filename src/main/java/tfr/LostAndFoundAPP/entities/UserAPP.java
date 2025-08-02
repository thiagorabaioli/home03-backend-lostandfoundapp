package tfr.LostAndFoundAPP.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="tb_user_app")
public class UserAPP implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String porNumber;
    private LocalDate birthDate;
    private String password;

    @Column(columnDefinition = "TIMESTAMP")
    private Instant creatAt;

     @Column(columnDefinition = "TIMESTAMP")
    private Instant updateSet;

    @ElementCollection
    @CollectionTable(name = "phone_number")
    private Set<String> phoneNumber = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "userAPP")
    private Set<OrderItem> items = new HashSet<OrderItem>();



    public UserAPP(){

    }

    public UserAPP(Long id, String name, String email, LocalDate birthDate, String porNumber, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.porNumber = porNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPorNumber() {
        return porNumber;
    }

    public void setPorNumber(String porNumber) {
        this.porNumber = porNumber;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public boolean hasRole(String roleName){
        for(Role role : roles){
            if (role.getAuthority().equals(roleName)){
                return true;
            }
        }
        return false;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    

    public Instant getCreatAt() {
        return creatAt;
    }

    @PrePersist
     public void prePersist(){
        this.creatAt = Instant.now();
     }

     @PreUpdate
     public void preUpdate(){
        this.updateSet = Instant.now();
     }


    public Instant getUpdateSet() {
        return updateSet;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserAPP other = (UserAPP) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
