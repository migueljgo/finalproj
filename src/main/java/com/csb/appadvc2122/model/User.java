package com.csb.appadvc2122.model;

import com.csb.appadvc2122.dto.UserDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false, updatable= false)
    private String passwordHash;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public User() {}

    public User(Long id) {
        this.id = id;
    }

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.emailAddress = userDTO.getEmailAddress();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String email) {
    	this.emailAddress = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

}
