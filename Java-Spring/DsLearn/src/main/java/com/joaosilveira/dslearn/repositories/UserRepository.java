package com.joaosilveira.dslearn.repositories;

import com.joaosilveira.dslearn.dto.UserDTO;
import com.joaosilveira.dslearn.entities.User;
import com.joaosilveira.dslearn.projections.UserDetailsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
			SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
			FROM tb_user
			INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
			INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
			WHERE tb_user.email = :email
		""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

	Optional<User> findByEmail(String email);

	@Query(value = "SELECT new com.joaosilveira.dslearn.dto.UserDTO(obj) FROM User obj",
			countQuery = "SELECT COUNT(obj) FROM User obj JOIN obj.roles")
	Page<UserDTO> findAllPageable(Pageable pageable);

}
