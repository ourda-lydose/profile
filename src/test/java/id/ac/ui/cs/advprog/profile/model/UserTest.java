package id.ac.ui.cs.advprog.profile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        this.user = new User();
        this.user.setId(1);
        this.user.setFullName("John Doe");
        this.user.setEmail("john@example.com");
        this.user.setPassword("password");
        this.user.setCreatedAt(new Date());
        this.user.setUpdatedAt(new Date());
    }

    @Test
    void testGetId() {
        assertEquals(1, this.user.getId());
    }

    @Test
    void testGetFullName() {
        assertEquals("John Doe", this.user.getFullName());
    }

    @Test
    void testGetEmail() {
        assertEquals("john@example.com", this.user.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", this.user.getPassword());
    }

    @Test
    void testGetCreatedAt() {
        assertNotNull(this.user.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        assertNotNull(this.user.getUpdatedAt());
    }

    @Test
    void testToString() {
        String expectedString = "User{id=1, fullName='John Doe', email='john@example.com', password='password', createdAt=";
        assertTrue(this.user.toString().startsWith(expectedString));
    }
}
