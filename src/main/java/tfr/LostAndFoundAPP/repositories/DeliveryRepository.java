package tfr.LostAndFoundAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfr.LostAndFoundAPP.entities.Delivery;
import tfr.LostAndFoundAPP.entities.ItemLost;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {


}

