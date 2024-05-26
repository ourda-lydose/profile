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
    @Column(name = "user_id", nullable = false)
    private String id;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "box_id")
    private String boxId;

    @Column(name = "user_role", nullable = false)
    private String role;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", length = 100, nullable = false)
    private String email;

    @Column(name = "user_pass", nullable = false)
    private String password;

    @Column(name = "user_gender")
    private String gender;

    @Column(name = "user_telp")
    private String noTelp;

    @Column(name = "user_bio")
    private String bio;

    @Column(name = "user_alamat")
    private String alamat;

    @Column(name = "user_admin", nullable = false)
    private Boolean isAdmin;
    public UserProfile() {

    }
    public UserProfile(UserBuilder builder){
        this.id = builder.id.toString();
        this.userName = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
        this.role = builder.role;
        this.gender = builder.gender;
        this.noTelp = builder.noTelp;
        this.bio = builder.bio;
        this.alamat = builder.alamat;
        this.isAdmin = builder.isAdmin;
    }

    public static class UserBuilder {
        private String id;
        private String userName;
        private String password;
        private String role;
        private String email;
        private String gender;
        private String noTelp;
        private String bio;
        private String alamat;
        private Boolean isAdmin;

        public UserBuilder() {

        }

        public UserBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder setUserName(String username) {
            this.userName = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRole(String role){
            this.role = role;
            if (this.role == "Admin"){
                setIsAdmin(true);

            }else{
                setIsAdmin(false);
            }

            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setGender(String gender){
            this.gender = gender;
            return this;
        }

        public UserBuilder setNoTelp(String noTelp){
            this.noTelp = noTelp;
            return this;
        }

        public UserBuilder setBio(String bio){
            this.bio = bio;
            return this;
        }

        public UserBuilder setAlamat(String alamat){
            this.alamat = alamat;
            return this;
        }

        public UserBuilder setIsAdmin(Boolean isAdmin){
            this.isAdmin = isAdmin;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
