package userdept.userdept.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import userdept.userdept.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    

}
