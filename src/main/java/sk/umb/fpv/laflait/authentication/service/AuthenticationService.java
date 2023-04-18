package sk.umb.fpv.laflait.authentication.service;

import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.umb.fpv.laflait.authentication.persistance.entity.RoleEntity;
import sk.umb.fpv.laflait.authentication.persistance.entity.TokenEntity;
import sk.umb.fpv.laflait.authentication.persistance.entity.UserEntity;
import sk.umb.fpv.laflait.authentication.persistance.repository.TokenRepository;
import sk.umb.fpv.laflait.authentication.persistance.repository.UserRepository;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private static final int TOKEN_VALIDITY_IN_MINUTES = 15;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public String authenticate(String username, String password) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        if ( ! passwordEncoder.matches(password,
                optionalUser.get().getPasswordHash())) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        TokenEntity token = new TokenEntity();
        String randomString = UUID.randomUUID().toString();
        token.setToken(randomString);
        token.setUser(optionalUser.get());
        token.setCreated(LocalDateTime.now());
        token.setId((long) 1);

        tokenRepository.save(token);

        return token.getToken();
    }

    @Transactional
    public UserRolesDTO authenticate(String token) {
        Optional<TokenEntity> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }

        validateTokenExpiration(optionalToken.get());

        Set<RoleEntity> roles = optionalToken.get().getUser().getRoles();
        Set<String> roleNames = roles.stream()
                .map( entry -> entry.getRoleName())
                .collect(Collectors.toSet());

        UserRolesDTO dto = new UserRolesDTO(optionalToken.get().getUser().getUsername(), roleNames);
        System.out.println(dto.getUserName());
        System.out.println(dto.getRoles());

        return new UserRolesDTO(optionalToken.get().getUser().getUsername(), roleNames);
    }

    private void validateTokenExpiration(TokenEntity token) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenExpiration = token.getCreated().plus(TOKEN_VALIDITY_IN_MINUTES, ChronoUnit.MINUTES);

        if ( now.isAfter(tokenExpiration) ) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }
    }

    @Transactional
    public void tokenRemove(String token) {
        tokenRepository.deleteByToken(token);
    }
}