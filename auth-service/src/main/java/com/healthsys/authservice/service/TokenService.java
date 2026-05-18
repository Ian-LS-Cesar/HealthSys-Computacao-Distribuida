package com.healthsys.authservice.service;

import com.healthsys.authservice.model.Token;
import com.healthsys.authservice.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void salvarToken(String tokenHash, String email, Instant issuedAt, Instant expiresAt){
        Token token = new Token();
        token.setTokenHash(tokenHash);
        token.setEmail(email);
        token.setIssuedAt(issuedAt);
        token.setExpiresAt(expiresAt);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    public boolean isRevoked(String tokenHash){
        return tokenRepository.findByTokenHash(tokenHash)
                .map(Token::isRevoked)
                .orElse(true); // Se não encontrar, considerar como revogado
    }

    public void revoke(String tokenHash){
        tokenRepository.findByTokenHash(tokenHash).ifPresent(token -> {
            token.setRevoked(true);
            tokenRepository.save(token);
        });
    }
}
