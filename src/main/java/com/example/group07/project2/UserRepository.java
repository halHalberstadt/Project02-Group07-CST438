package com.example.group07.project2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This is a repository that is a repository for our User object that we
 * just created.
 * Used for accessing info from our database. Pairs up with the User that we just created
 * and the ID that we use to uniquely identify that this is a repository, (Integer)
 * We will have an empty interface and it will work
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM User u WHERE u.name like %:name%", countQuery = "SELECT COUNT(*) FROM User", nativeQuery = true)
    List<User> findUserByName(
            @Param("name") String name
    );
}
