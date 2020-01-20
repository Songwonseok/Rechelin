// 디비 셋팅 주석 
 package com.web.curation.model.user;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;

 import com.fasterxml.jackson.annotation.JsonIgnore;

 import java.time.LocalDateTime;

 @Entity
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int uid;
    
     @JsonIgnore
     private String password;
     private String email;
     private String nickname;

     @Column(insertable = false, updatable = false)
     private LocalDateTime createDate;
    
 }
