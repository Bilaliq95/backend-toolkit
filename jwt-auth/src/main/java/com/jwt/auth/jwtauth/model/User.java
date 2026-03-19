package com.jwt.auth.jwtauth.model;

import java.time.LocalDate;
public class User {
        private String userId;
        private LocalDate dateCreated;
        private String email;
        private String name;
        private String password;
        private String phoneNumber;
        private int taskCount;


        public User(){

        }

        public String getUserId()
        {
            return this.userId;
        }

        public void setUserId(String userId)
        {
            this.userId=userId;
        }
        public LocalDate getDateCreated()
        {
            return this.dateCreated;
        }

        public void setDateCreated(LocalDate dateCreated)
        {
            this.dateCreated=dateCreated;
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

        public String getPhoneNumber(){
            return this.phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber=phoneNumber;
        }

        public int getTaskCount(){
            return this.taskCount;
        }

        public void setTaskCount(int taskCount){
            this.taskCount=taskCount;
        }

}
