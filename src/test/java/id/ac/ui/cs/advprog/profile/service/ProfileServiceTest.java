package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @InjectMocks
    ProfileServiceImpl profileService;

    @Mock
    ProfileRepository profileRepository;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        userList.add(new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password"));
        userList.add(new User().setId(2).setFullName("Jane Doe").setEmail("jane@example.com").setPassword("password123"));
    }

    @Test
    void testCreateUser() {
        User newUser = new User().setId(3).setFullName("Alice Smith").setEmail("alice@example.com").setPassword("alicepassword");
        when(profileRepository.findById(newUser.getId())).thenReturn(null);
        when(profileRepository.create(newUser)).thenReturn(newUser);

        User createdUser = profileService.create(newUser);

        assertNotNull(createdUser);
        assertEquals(newUser, createdUser);
    }

    @Test
    void testCreateExistingUser() {
        User existingUser = userList.get(0);
        when(profileRepository.findById(existingUser.getId())).thenReturn(existingUser);

        User createdUser = profileService.create(existingUser);

        assertNull(createdUser);
    }

    @Test
    void testFindAllUsers() {
        when(profileRepository.findAll()).thenReturn(userList);

        List<User> foundUsers = profileService.findAll();

        assertNotNull(foundUsers);
        assertEquals(userList.size(), foundUsers.size());
    }

    @Test
    void testEditUser() {
        User editedUser = new User().setId(1).setFullName("John Smith").setEmail("john.smith@example.com").setPassword("newpassword");
        when(profileRepository.findById(editedUser.getId())).thenReturn(editedUser);
        when(profileRepository.edit(editedUser)).thenReturn(editedUser);

        User updatedUser = profileService.edit(editedUser);

        assertNotNull(updatedUser);
        assertEquals(editedUser, updatedUser);
    }

    @Test
    void testEditNonexistentUser() {
        User nonExistentUser = new User().setId(100).setFullName("Non Existent").setEmail("non@example.com").setPassword("nonpassword");
        when(profileRepository.findById(nonExistentUser.getId())).thenReturn(null);

        User updatedUser = profileService.edit(nonExistentUser);

        assertNull(updatedUser);
    }

    @Test
    void testDeleteUser() {
        User userToDelete = userList.get(0);
        when(profileRepository.findById(userToDelete.getId())).thenReturn(userToDelete);
        when(profileRepository.delete(userToDelete)).thenReturn(userToDelete);

        User deletedUser = profileService.delete(userToDelete);

        assertNotNull(deletedUser);
        assertEquals(userToDelete, deletedUser);
    }

    @Test
    void testDeleteNonexistentUser() {
        User nonExistentUser = new User().setId(100).setFullName("Non Existent").setEmail("non@example.com").setPassword("nonpassword");
        when(profileRepository.findById(nonExistentUser.getId())).thenReturn(null);

        User deletedUser = profileService.delete(nonExistentUser);

        assertNull(deletedUser);
    }

    @Test
    void testFindUserById() {
        User userToFind = userList.get(0);
        when(profileRepository.findById(userToFind.getId())).thenReturn(userToFind);

        User foundUser = profileService.findById(userToFind.getId());

        assertNotNull(foundUser);
        assertEquals(userToFind, foundUser);
    }

    @Test
    void testFindNonexistentUserById() {
        int nonexistentId = 100;
        when(profileRepository.findById(nonexistentId)).thenReturn(null);

        User foundUser = profileService.findById(nonexistentId);

        assertNull(foundUser);
    }
}
