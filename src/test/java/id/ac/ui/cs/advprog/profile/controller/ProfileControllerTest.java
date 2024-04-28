package id.ac.ui.cs.advprog.profile.controller;

import id.ac.ui.cs.advprog.profile.model.User;
import id.ac.ui.cs.advprog.profile.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        when(profileService.create(user)).thenReturn(user);

        ResponseEntity<?> response = profileController.createUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testCreateUserAlreadyExists() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        when(profileService.create(user)).thenReturn(null);

        ResponseEntity<?> response = profileController.createUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User with ID 1 already exists.", response.getBody());
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password"));
        userList.add(new User().setId(2).setFullName("Jane Doe").setEmail("jane@example.com").setPassword("password123"));
        when(profileService.findAll()).thenReturn(userList);

        ResponseEntity<List<User>> response = profileController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void testGetUserById() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        when(profileService.findById(1)).thenReturn(user);

        ResponseEntity<?> response = profileController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(profileService.findById(1)).thenReturn(null);

        ResponseEntity<?> response = profileController.getUserById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateUser() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        when(profileService.findById(1)).thenReturn(user);

        User updatedUser = new User().setId(1).setFullName("John Smith").setEmail("john.smith@example.com").setPassword("newpassword");
        when(profileService.edit(updatedUser)).thenReturn(updatedUser);

        ResponseEntity<?> response = profileController.updateUser(1, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void testUpdateUserNotFound() {
        when(profileService.findById(1)).thenReturn(null);

        User updatedUser = new User().setId(1).setFullName("John Smith").setEmail("john.smith@example.com").setPassword("newpassword");

        ResponseEntity<?> response = profileController.updateUser(1, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteUser() {
        User user = new User().setId(1).setFullName("John Doe").setEmail("john@example.com").setPassword("password");
        when(profileService.findById(1)).thenReturn(user);

        ResponseEntity<?> response = profileController.deleteUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(profileService, times(1)).delete(user);
    }

    @Test
    void testDeleteUserNotFound() {
        when(profileService.findById(1)).thenReturn(null);

        ResponseEntity<?> response = profileController.deleteUser(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(profileService, never()).delete(any());
    }
}
