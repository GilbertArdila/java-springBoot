package com.platzi.fundamentos.repository;

import com.platzi.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {

}
