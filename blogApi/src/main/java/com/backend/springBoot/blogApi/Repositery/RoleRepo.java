package com.backend.springBoot.blogApi.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springBoot.blogApi.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
