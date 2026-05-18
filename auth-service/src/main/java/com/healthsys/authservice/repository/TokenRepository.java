package com.healthsys.authservice.repository;

import com.healthsys.authservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByTokenHash(String tokenHash);
}
