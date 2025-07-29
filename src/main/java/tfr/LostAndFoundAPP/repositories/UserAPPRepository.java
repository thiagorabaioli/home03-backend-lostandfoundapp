package tfr.LostAndFoundAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfr.LostAndFoundAPP.entities.UserAPP;

@Repository
public interface UserAPPRepository  extends JpaRepository<UserAPP,Long> {
}

