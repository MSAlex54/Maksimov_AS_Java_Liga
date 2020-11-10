package com.liga.backend.repository;

import com.liga.backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>, JpaSpecificationExecutor<User> {

    User getUserById(UUID id);

    @Query(value = "SELECT u FROM User u WHERE u.id IN :ids")
    Set<User> findUserByIdList(@Param("ids") Collection<UUID> ids);

    Set<User> findAllByFirstNameLike(String firstName);

    Set<User> findAllByLastNameLike(String lastName);

    Set<User> findAllByFirstNameLikeAndLastNameLike(String firstName, String lastName);

    Set<User> findAllByFirstNameLikeAndLastNameLikeAndBirthDayIsBetween(String firstName, String lastName, LocalDate localDate, LocalDate date);

    Set<User> findAllByFirstNameLikeAndBirthDayIsBetween(String firstName, LocalDate minusYears, LocalDate minusYears1);

    Set<User> findAllByLastNameLikeAndBirthDayIsBetween(String lastName, LocalDate minusYears, LocalDate minusYears1);

    Set<User> findAllByBirthDayIsBetween(LocalDate minusYears, LocalDate minusYears1);
}
