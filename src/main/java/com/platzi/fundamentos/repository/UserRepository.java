package com.platzi.fundamentos.repository;

import com.platzi.fundamentos.dto.UserDto;
import com.platzi.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User ,Long> {
    //JPQL
    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
    List<User> findAndSort(String name, Sort sort);
//query methods
    List<User> findByName(String name);

    List<User> findByNameLike(String name);
   List<User> findByNameOrEmail(String name,String email);

   List<User> findByBirthdayBetween(LocalDate begin,LocalDate end);

  // List<User>findByNameLikeOrderByDesc(String name);

   @Query("SELECT new com.platzi.fundamentos.dto.UserDto(u.id,u.name,u.birthday)"+
   "FROM User u "+
   "WHERE u.birthday =:fecha "+
   "AND u.email=:email")
  Optional<UserDto>getAllByBirthdayAndEmail(@Param("fecha") LocalDate birthday,@Param("email") String email);
    List<User> findAll();

}
