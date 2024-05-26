package id.ac.ui.cs.advprog.profile.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import id.ac.ui.cs.advprog.profile.controller.ProfileController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import id.ac.ui.cs.advprog.profile.service.ProfileService;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @Test
    void testCreateUser_HappyPath() throws ExecutionException, InterruptedException {
        UserProfile user = new UserProfile();
        user.setId("1");

        when(profileService.create(user)).thenReturn(CompletableFuture.completedFuture(user));

        CompletableFuture<ResponseEntity<Map<String, Object>>> futureResponse = profileController.createUser(user);

        assertEquals(HttpStatus.CREATED, futureResponse.get().getStatusCode());
        assertNotNull(futureResponse.get().getBody());
        assertEquals("User Created Successfully", futureResponse.get().getBody().get("message"));
        assertEquals(user, futureResponse.get().getBody().get("User"));
    }

    @Test
    public void testCreateUserUnhappyPath() {
        // Arrange
        UserProfile user = new UserProfile();
        CompletableFuture<UserProfile> future = CompletableFuture.failedFuture(new RuntimeException("Error"));
        when(profileService.create(user)).thenReturn(future);

        // Act
        ResponseEntity<?> response = profileController.createUser(user).join();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetUserById_HappyPath() throws ExecutionException, InterruptedException {
        String id = "1";
        UserProfile user = new UserProfile();
        user.setId(id);

        when(profileService.findById(id)).thenReturn(CompletableFuture.completedFuture(Optional.of(user)));

        CompletableFuture<ResponseEntity<?>> futureResponse = profileController.getUserById(id);

        assertEquals(HttpStatus.OK, futureResponse.get().getStatusCode());
        assertNotNull(futureResponse.get().getBody());
        assertEquals(user, futureResponse.get().getBody());
    }

    @Test
    void testGetUserById_UnhappyPath() throws ExecutionException, InterruptedException {
        String id = "1";

        when(profileService.findById(id)).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        CompletableFuture<ResponseEntity<?>> futureResponse = profileController.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, futureResponse.get().getStatusCode());
        assertNotNull(futureResponse.get().getBody());

        // Mengonversi objek badan respons menjadi tipe Map<String, Object>
        Map<String, Object> responseBody = (Map<String, Object>) futureResponse.get().getBody();

        // Menguji pesan kesalahan
        assertEquals("User with ID " + id + " not found.", responseBody.get("message"));
    }


    @Test
    void testEditUser_HappyPath() throws ExecutionException, InterruptedException {
        String id = "1";
        UserProfile user = new UserProfile();
        user.setId(id);

        when(profileService.edit(user)).thenReturn(CompletableFuture.completedFuture(user));

        CompletableFuture<ResponseEntity<Map<String, Object>>> futureResponse = profileController.editUser(id, user);

        assertEquals(HttpStatus.CREATED, futureResponse.get().getStatusCode());
        assertNotNull(futureResponse.get().getBody());
        assertEquals("User " + id +" updated Successfully", futureResponse.get().getBody().get("message"));
        assertEquals(user, futureResponse.get().getBody().get("User"));
    }

    @Test
    public void testEditUserUnhappyPath() {
        // Arrange
        UserProfile user = new UserProfile();
        CompletableFuture<UserProfile> future = CompletableFuture.failedFuture(new RuntimeException("Error"));
        when(profileService.edit(user)).thenReturn(future);

        // Act
        ResponseEntity<?> response = profileController.editUser("1", user).join();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testDeleteUser_HappyPath() throws ExecutionException, InterruptedException {
        String id = "1";

        when(profileService.delete(id)).thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<ResponseEntity<Map<String, Object>>> futureResponse = profileController.deleteUser(id);

        assertEquals(HttpStatus.OK, futureResponse.get().getStatusCode());
        assertNotNull(futureResponse.get().getBody());
        assertEquals("User Deleted Successfully", futureResponse.get().getBody().get("message"));
    }

    @Test
    void testDeleteUserUnhappyPath() throws ExecutionException, InterruptedException {
        // Arrange
        String userId = "1";
        when(profileService.delete(userId)).thenReturn(CompletableFuture.completedFuture(null));

        // Act
        ResponseEntity<Map<String, Object>> responseEntity = profileController.deleteUser(userId).get();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("User with ID 1 not found.", responseEntity.getBody().get("message"));
    }


    @Test
    void testFindAllListings_HappyPath() throws ExecutionException, InterruptedException {
        List<UserProfile> userList = new ArrayList<>();
        userList.add(new UserProfile());
        userList.add(new UserProfile());

        when(profileService.findAll()).thenReturn(CompletableFuture.completedFuture(userList));

        CompletableFuture<ResponseEntity<List<UserProfile>>> futureResponse = profileController.findAllListings();

        assertEquals(HttpStatus.OK, futureResponse.get().getStatusCode());
        assertNotNull(futureResponse.get().getBody());
        assertEquals(userList, futureResponse.get().getBody());
    }

    @Test
    void testFindAllUnhappyPath() throws ExecutionException, InterruptedException {
        // Arrange
        when(profileService.findAll()).thenReturn(CompletableFuture.failedFuture(new RuntimeException("Failed to fetch users")));

        // Act
        ResponseEntity<List<UserProfile>> responseEntity = profileController.findAllListings().get();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size());
    }
}
