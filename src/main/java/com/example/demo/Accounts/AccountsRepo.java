package com.example.demo.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findAccountsByUsername(String username);
    Optional<Accounts> findByUsernameAndPassword(String username, String password);

}
