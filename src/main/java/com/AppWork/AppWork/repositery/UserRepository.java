package com.AppWork.AppWork.repositery;

import com.AppWork.AppWork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPasswordIgnoreCase(String email, String password);
    User findByApiKey(String apiKey);
}
