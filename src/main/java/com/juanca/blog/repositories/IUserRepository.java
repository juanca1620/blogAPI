package com.juanca.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanca.blog.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer>{

}
