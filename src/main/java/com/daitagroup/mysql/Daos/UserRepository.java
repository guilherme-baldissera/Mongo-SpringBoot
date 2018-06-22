package com.daitagroup.mysql.Daos;

import com.daitagroup.mysql.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);
}
