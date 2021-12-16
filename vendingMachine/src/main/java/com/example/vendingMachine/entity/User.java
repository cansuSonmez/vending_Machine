package com.example.vendingMachine.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private boolean role;


     public User (int user_id,String username,String password,boolean role){
         this.user_id=user_id;
         this.username=username;
         this.password=password;
         this.role=role;
     }


}
