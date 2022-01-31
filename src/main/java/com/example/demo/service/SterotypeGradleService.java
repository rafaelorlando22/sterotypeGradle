package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.dao.LoginDTO;
import com.example.demo.repository.dao.UserDTO;
import com.example.demo.repository.entities.User;
import com.example.demo.repository.entities.UserRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SterotypeGradleService {

    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;


    @Autowired
    public SterotypeGradleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<LoginDTO> getUserByEmail(String email ){

        User userExisted = userRepository.findUserByEmail(email);

        if(userExisted != null){
           return Optional.of(LoginDTO.builder()
                   .id(userExisted.getId())
                   .created(userExisted.getCreated())
                   .lastlogin(userExisted.getLastLogin())
                   .token(userExisted.getToken())
                   .isActive(true)
                   .name(userExisted.getName())
                   .email(userExisted.getEmail())
                   .password(userExisted.getPassword())
                   .phones(userExisted.getPhones())
                   .build());
        }
        return Optional.empty();
    }

    public Optional<UserDTO> createNewUser(UserRequest userRequest) {

        User userExisted = userRepository.findUserByNameAndAndEmail(userRequest.getName(), userRequest.getEmail());

        if(userExisted != null){
            return Optional.empty();
        }
        final String token = getJWTToken(userRequest.getName());

        User userRercod = User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .phones(userRequest.getPhones())
                .created(LocalDate.now())
                .lastLogin(LocalDate.now())
                .token(token)
                .build();
        User user = userRepository.save(userRercod);


        //NO ENTENDI LO DEL CAMPO isActive
        return Optional.of(UserDTO.builder().id(user.getId()).date(LocalDate.now()).token(token).isActive(true).build());
    }

    private String getJWTToken(String username) {
        //String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}

