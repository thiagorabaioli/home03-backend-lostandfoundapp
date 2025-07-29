package tfr.LostAndFoundAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfr.LostAndFoundAPP.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
