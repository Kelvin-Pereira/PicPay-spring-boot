package com.koldex.picpay.api.user.domain.entity;

import com.koldex.picpay.api.user.domain.dto.UserDTO;
import com.koldex.picpay.api.user.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "USERS")
@Table(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fristName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;

    private User(UserDTO userDTO) {
        this.fristName = userDTO.fristName();
        this.lastName = userDTO.lastName();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.balance = userDTO.balance();
        this.password = userDTO.password();
        this.userType = userDTO.userType();
    }

    public static User of(UserDTO userDTO) {
        return new User(userDTO);
    }

}
