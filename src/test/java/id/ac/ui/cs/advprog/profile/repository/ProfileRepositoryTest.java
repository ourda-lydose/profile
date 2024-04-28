package id.ac.ui.cs.advprog.profile.repository;

import id.ac.ui.cs.advprog.profile.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProfileRepositoryTest {

    @InjectMocks
    ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateAndFind() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        profileRepository.create(user);

        User foundUser = profileRepository.findById(1);

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getFullName(), foundUser.getFullName());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<User> userIterator = profileRepository.findAll();

        assertFalse(userIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneUser() {
        User user1 = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        profileRepository.create(user1);

        User user2 = new User().setId(2).setFullName("Jane Doe").setEmail("jane@example.com").setPassword("password123");
        profileRepository.create(user2);

        Iterator<User> userIterator = profileRepository.findAll();

        assertTrue(userIterator.hasNext());
        assertEquals(user1.getId(), userIterator.next().getId());
        assertEquals(user2.getId(), userIterator.next().getId());
        assertFalse(userIterator.hasNext());
    }

    @Test
    void testEditUser() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        profileRepository.create(user);

        User updatedUser = new User().setId(1).setFullName("John Smith").setEmail("john.smith@example.com").setPassword("newpassword");
        profileRepository.edit(updatedUser);

        User foundUser = profileRepository.findById(1);

        assertNotNull(foundUser);
        assertEquals(updatedUser.getId(), foundUser.getId());
        assertEquals(updatedUser.getFullName(), foundUser.getFullName());
        assertEquals(updatedUser.getEmail(), foundUser.getEmail());
        assertEquals(updatedUser.getPassword(), foundUser.getPassword());
    }

    @Test
    void testDeleteUser() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        profileRepository.create(user);

        profileRepository.delete(user);

        assertNull(profileRepository.findById(1));
    }
}
