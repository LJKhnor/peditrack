package joachim.lejeune.peditrack.repository;

import joachim.lejeune.peditrack.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select * from \"user\" u where u.email= :email and u.name= :username", nativeQuery = true)
    User findUserByEmailAndName(@Param("email") String email, @Param("username") String username);
}
