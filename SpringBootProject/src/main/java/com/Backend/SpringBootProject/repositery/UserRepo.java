package com.Backend.SpringBootProject.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Backend.SpringBootProject.Entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
