package com.jwt.auth.jwtauth.model;

import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users")
public class User {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(name = "user_id")
        private Long userId;
        @Column(name="email")
        private String email;
        @Column(name="name")
        private String name;
        @Column(name="password")
        private String password;


        public User(){

        }

        public Long getUserId()
        {
            return this.userId;
        }

        public void setUserId(Long userId)
        {
            this.userId=userId;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email){
            this.email=email;
        }

        public String getName(){
            return this.name;
        }
        public void setName(String name)
        {
            this.name=name;
        }

        public String getPassword(){
            return this.password;
        }

        public void setPassword(String password){
            this.password=password;
        }


}
