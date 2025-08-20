package tfr.LostAndFoundAPP.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.projections.UserDetailsProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemLostRepository extends JpaRepository<ItemLost,Long> {


    Page<ItemLost> findByStatusTrue(Pageable pageable);


    List<ItemLost> findByStatusFalse();

    // SEARCH BAR method
    @Query("SELECT obj FROM ItemLost obj WHERE LOWER(obj.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    Page<ItemLost> searchByDescription(String description, Pageable pageable);
}

