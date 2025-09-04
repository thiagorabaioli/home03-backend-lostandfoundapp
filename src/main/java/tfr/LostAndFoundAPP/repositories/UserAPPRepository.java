package tfr.LostAndFoundAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.projections.UserDetailsProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAPPRepository  extends JpaRepository<UserAPP,Long> {



	@Query(nativeQuery = true, value = """
		SELECT tb_user_app.email AS username, tb_user_app.password, tb_role.id AS roleId, tb_role.authority
		FROM tb_user_app
		INNER JOIN tb_user_role ON tb_user_app.id = tb_user_role.user_id
		INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
		WHERE tb_user_app.email = :email
	""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

	Optional<UserAPP> findByEmail(String email);

	// NOVO MÉTODO ADICIONADO AQUI
	@Query("SELECT obj FROM UserAPP obj JOIN FETCH obj.roles r WHERE r.authority IN :roleNames")
	List<UserAPP> findUsersByRoles(List<String> roleNames);

}