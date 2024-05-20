package id.ac.ui.cs.advprog.profile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    UserProfile userProfile;
    UserProfile.UserBuilder builder;

    @BeforeEach
    void setUp() {
        this.builder = new UserProfile.UserBuilder("John Doe", "password123");
        this.userProfile = builder.setEmail("john.doe@example.com")
                .build();
    }

    @Test
    void testGetUserId() {
        assertNotNull(this.userProfile.getId());
    }

    @Test
    void testGetFullName() {
        assertEquals("John Doe", this.userProfile.getFullName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", this.userProfile.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", this.userProfile.getPassword());
    }

    @Test
    void testSetUserId() {
        String newId = UUID.randomUUID().toString();
        this.userProfile.setId(newId);
        assertEquals(newId, this.userProfile.getId());
    }

    @Test
    void testSetFullName() {
        this.userProfile.setFullName("Jane Doe");
        assertEquals("Jane Doe", this.userProfile.getFullName());
    }

    @Test
    void testSetEmail() {
        this.userProfile.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", this.userProfile.getEmail());
    }

    @Test
    void testSetPassword() {
        this.userProfile.setPassword("newpassword123");
        assertEquals("newpassword123", this.userProfile.getPassword());
    }
}
