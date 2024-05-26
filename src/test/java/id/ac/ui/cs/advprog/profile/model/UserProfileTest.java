package id.ac.ui.cs.advprog.profile.model;

import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import org.junit.jupiter.api.Test;

public class UserProfileTest {

    @Test
    void testUserProfileBuilder_HappyPath() {
        UserProfile.UserBuilder builder = new UserProfile.UserBuilder();

        UserProfile userProfile = builder
                .setId("1")
                .setUserName("testUser")
                .setPassword("password")
                .setIsAdmin(false)
                .setRole("User")
                .setEmail("test@example.com")
                .setGender("Male")
                .setNoTelp("123456789")
                .setBio("Test bio")
                .setAlamat("Test address")
                .build();

        assertNotNull(userProfile);
        assertEquals("1", userProfile.getId());
        assertEquals("testUser", userProfile.getUserName());
        assertEquals("password", userProfile.getPassword());
        assertEquals("User", userProfile.getRole());
        assertEquals("test@example.com", userProfile.getEmail());
        assertEquals("Male", userProfile.getGender());
        assertEquals("123456789", userProfile.getNoTelp());
        assertEquals("Test bio", userProfile.getBio());
        assertEquals("Test address", userProfile.getAlamat());
        assertFalse(userProfile.getIsAdmin());
    }

    @Test
    void testUserProfileBuilder_UnhappyPath() {
        UserProfile.UserBuilder builder = new UserProfile.UserBuilder();

        UserProfile userProfile = builder
                .setId("1")
                .setUserName("testUser")
                .setPassword("password")
                .setIsAdmin(false)
                .setRole("Admin") // Incorrect role
                .setEmail("test@example.com")
                .setGender("Male")
                .setNoTelp("123456789")
                .setBio("Test bio")
                .setAlamat("Test address")
                .build();

        assertNotNull(userProfile);
        assertEquals("1", userProfile.getId());
        assertEquals("testUser", userProfile.getUserName());
        assertEquals("password", userProfile.getPassword());
        assertEquals("Admin", userProfile.getRole()); // Even if set to Admin, isAdmin should be true
        assertEquals("test@example.com", userProfile.getEmail());
        assertEquals("Male", userProfile.getGender());
        assertEquals("123456789", userProfile.getNoTelp());
        assertEquals("Test bio", userProfile.getBio());
        assertEquals("Test address", userProfile.getAlamat());
        assertTrue(userProfile.getIsAdmin());
    }
}
