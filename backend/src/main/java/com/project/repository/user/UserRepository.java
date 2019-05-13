package com.project.repository.user;

import com.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findById(Long id);
    User findByUsername(String username);
    Page<User> findAll(Pageable pageable);

    Page<User> findAllByUsernameLikeOrFirstnameLikeOrLastnameLikeOrEmailLike(String username, String firstname, String lastname, String email, Pageable pageable);
    Integer countAllByUsernameLikeOrFirstnameLikeOrLastnameLikeOrEmailLike(String username, String firstname, String lastname, String email);

    @Query("SELECT u FROM User u WHERE banned = ?1 AND (username LIKE ?2 OR firstname LIKE ?3 OR lastname LIKE ?4 OR email LIKE ?5)")
    Page<User> findAllByBannedAndUsernameLikeOrFirstnameLikeOrLastnameLikeOrEmailLike(Boolean banned, String username, String firstname, String lastname, String email, Pageable pageable);
    @Query("SELECT COUNT(u) FROM User u WHERE banned = ?1 AND (username LIKE ?2 OR firstname LIKE ?3 OR lastname LIKE ?4 OR email LIKE ?5)")
    Integer countAllByBannedAndUsernameLikeOrFirstnameLikeOrLastnameLikeOrEmailLike(Boolean banned ,String username, String firstname, String lastname, String email);

}
