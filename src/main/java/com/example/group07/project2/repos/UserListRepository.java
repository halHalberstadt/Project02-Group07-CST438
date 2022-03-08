package com.example.group07.project2.repos;

import com.example.group07.project2.models.UserList;
import org.springframework.data.repository.CrudRepository;

/**
 * This is a repository that is a repository for our UserList object that we
 * just created.
 * Used for accessing info from our database. Pairs up with the UserList that we just created
 * and the ID that we use to uniquely identify that this is a repository, (Integer)
 * We will have an empty interface and it will work
 */
public interface UserListRepository extends CrudRepository<UserList, Integer> {

}
