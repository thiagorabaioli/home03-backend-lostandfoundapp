package tfr.LostAndFoundAPP.entities;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_delivery")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "delivery_type", discriminatorType = DiscriminatorType.STRING) // <-- ADICIONE ESTA LINHA
public abstract class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate deliveryDate;

    @OneToOne
    @JoinColumn(name = "item_lost_id", unique = true)
    private ItemLost itemLost;

    public Delivery() {}

    public Delivery(Long id, LocalDate deliveryDate) {
        this.id = id;
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
