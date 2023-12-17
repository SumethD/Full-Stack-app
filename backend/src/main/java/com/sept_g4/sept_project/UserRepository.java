package com.sept_g4.sept_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// A repository interface for managing user data, extending JpaRepository for CRUD operations.
@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    // Method to find a user by email and password, returning an optional user.
    Optional<Users> findOneByEmailAndPassword(String email, String password);

    // Method to find a user by email, returning the user.
    Users findByEmail(String email);

    // Method to find a user by their unique id, returning an optional user.
    Optional<Users> findById(Long id);

}
