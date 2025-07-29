package tfr.LostAndFoundAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.entities.Owner;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
