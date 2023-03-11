package com.platzi.fundamentos.repository;

import com.platzi.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {
    //JPQL
    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
    List<User> findAndSort(String name, Sort sort);



}
