package com.codecool.helpinghands.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String firstName;
    private String lastName;
    private String userNickname;
    private String userEmail;
    private String password;
    private LocalDateTime dateJoined;
    private String userImagePath;
    private boolean isModerator;
    @ManyToMany
    @JoinTable
    private Set<Slot> userSlots;

    public User(String userNickname, String userEmail, String password) {
        this.firstName = "";
        this.lastName = "";
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.password = password;
        this.dateJoined = LocalDateTime.now();
        this.userImagePath = "path";
        this.isModerator = false;
    }

    public void addSlot(Slot slot){
        this.userSlots.add(slot);
    }
}
