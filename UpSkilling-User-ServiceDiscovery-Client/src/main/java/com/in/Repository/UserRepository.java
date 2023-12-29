package com.in.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.in.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserid(String userid);

}