package com.example.calorietracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters")
    @Column(nullable = false, length = 64)
    private String password;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 1, max = 20, message = "First name must be between 1 and 20 characters")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 1, max = 20, message = "Last name must be between 1 and 20 characters")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    // getters and setters are not show
}