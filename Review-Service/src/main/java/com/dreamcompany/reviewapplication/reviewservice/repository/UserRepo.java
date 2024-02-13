package com.dreamcompany.reviewapplication.reviewservice.repository;

import com.dreamcompany.reviewapplication.reviewservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {
}
