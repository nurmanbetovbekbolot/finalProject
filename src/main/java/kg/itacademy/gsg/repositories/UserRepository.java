package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "select u from User u join u.role r where r.roleName = :name")
    List<User> getByRole(@Param("name") String name);

    @Query("select new kg.itacademy.gsg.models.UserModel(user.id,user.email,user.password,user.firstName,user.lastName,user.role) FROM User user ORDER BY user.firstName ASC")
    Page<UserModel> findAllUsersWithPagination(Pageable pageable);
}
