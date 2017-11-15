package com.sysensor.data.repository;

import com.sysensor.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User> {

    List<User> findAllByUserName(String userName);
}
