package id.ac.ui.cs.advprog.profile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "userpprofile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public UserProfile() {

    }
    public UserProfile(UserBuilder builder){
        this.id = UUID.randomUUID().toString();
        this.fullName = builder.fullName;
        this.password = builder.password;
        this.email = builder.email;
    }

    public static class UserBuilder {
        // Required parameters
        private String fullName;
        private String password;

        // Optional parameters
        private String email;

        public UserBuilder(String name, String password) {
            this.fullName = name;
            this.password = password;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
