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
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int noTelp;

    @Column(nullable = true)
    private String alamat;
    public UserProfile() {

    }
    public UserProfile(UserBuilder builder){
        this.id = UUID.randomUUID().toString();
        this.userName = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
        this.noTelp = builder.noTelp;
        this.alamat = builder.alamat;
    }

    public static class UserBuilder {
        // Required parameters
        private String userName;
        private String password;

        // Optional parameters
        private String email;
        private int noTelp;
        private String alamat;

        public UserBuilder(String name, String password) {
            this.userName = name;
            this.password = password;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setNoTelp(int noTelp){
            this.noTelp = noTelp;
            return this;
        }

        public UserBuilder setAlamat(String alamat) {
            this.alamat = alamat;
            return this;
        }
        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
