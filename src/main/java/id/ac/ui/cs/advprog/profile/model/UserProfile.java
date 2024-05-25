package id.ac.ui.cs.advprog.profile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "userprofile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "box_id")
    private String boxId;

    @Column(name = "user_role", nullable = false)
    private String role;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", unique = true, length = 100, nullable = false)
    private String email;

    @Column(name = "user_pass", nullable = false)
    private String password;

    public UserProfile() {

    }
    public UserProfile(UserBuilder builder){
        this.userName = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
        this.role = builder.role;
    }

    public static class UserBuilder {
        private String userName;
        private String password;
        private String role;
        private String email;

        public UserBuilder(String name, String password) {
            this.userName = name;
            this.password = password;
        }

        public UserBuilder setRole(String role){
            this.role = role;
            return this;
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
