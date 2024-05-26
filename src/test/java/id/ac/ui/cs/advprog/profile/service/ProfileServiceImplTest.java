package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {
    @InjectMocks
    ProfileServiceImpl profileService;

    @Mock
    ProfileRepository profileRepository;

    private UserProfile createUserProfile(String name, String email, String password) {
        return new UserProfile.UserBuilder()
                .setUserName(name)
                .setPassword(password)
                .setEmail(email)
                .build();
    }

    @BeforeEach
    void setUp() {
        // Setup can be done here if needed
    }

    @Test
    void testCreateAndFind() throws ExecutionException, InterruptedException {
        UserProfile userProfile = createUserProfile("John Doe", "john@example.com", "password");

        Mockito.when(profileRepository.save(userProfile)).thenReturn(userProfile);
        profileService.create(userProfile);

        Mockito.when(profileRepository.findAll()).thenReturn(List.of(userProfile));
        CompletableFuture<List<UserProfile>> userListFuture = profileService.findAll();
        List<UserProfile> userList = userListFuture.get();

        assertFalse(userList.isEmpty());
        UserProfile savedUser = userList.get(0);
        assertEquals(userProfile.getId(), savedUser.getId());
        assertEquals(userProfile.getUserName(), savedUser.getUserName());
        assertEquals(userProfile.getEmail(), savedUser.getEmail());
    }

    @Test
    void testFindAllIfEmpty() throws ExecutionException, InterruptedException {
        List<UserProfile> userList = new ArrayList<>();
        Mockito.when(profileRepository.findAll()).thenReturn(userList);

        CompletableFuture<List<UserProfile>> usersFuture = profileService.findAll();
        List<UserProfile> users = usersFuture.get();

        assertTrue(users.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanOneUser() throws ExecutionException, InterruptedException {
        UserProfile userProfile1 = createUserProfile("John Doe", "john@example.com", "password");
        UserProfile userProfile2 = createUserProfile("Jane Doe", "jane@example.com", "password");

        Mockito.when(profileRepository.save(userProfile1)).thenReturn(userProfile1);
        profileService.create(userProfile1);

        Mockito.when(profileRepository.save(userProfile2)).thenReturn(userProfile2);
        profileService.create(userProfile2);

        Mockito.when(profileRepository.findAll()).thenReturn(List.of(userProfile1, userProfile2));
        CompletableFuture<List<UserProfile>> userListFuture = profileService.findAll();
        List<UserProfile> userList = userListFuture.get();

        assertFalse(userList.isEmpty());
        assertEquals(userProfile1.getId(), userList.get(0).getId());
        assertEquals(userProfile2.getId(), userList.get(1).getId());
    }

    @Test
    void testEditUser() throws ExecutionException, InterruptedException {
        UserProfile userProfile = createUserProfile("John Doe", "john@example.com", "password");
        Mockito.when(profileRepository.save(userProfile)).thenReturn(userProfile);
        profileService.create(userProfile);

        UserProfile editedUserProfile = new UserProfile.UserBuilder()
                .setEmail("johnsmith@example.com")
                .build();
        profileService.edit(editedUserProfile);

        Mockito.when(profileRepository.findById(userProfile.getId())).thenReturn(Optional.of(editedUserProfile));
        CompletableFuture<Optional<UserProfile>> resultUserFuture = profileService.findById(userProfile.getId());
        Optional<UserProfile> resultUser = resultUserFuture.get();

        assertTrue(resultUser.isPresent());
        assertEquals(editedUserProfile.getId(), resultUser.get().getId());
        assertEquals(editedUserProfile.getUserName(), resultUser.get().getUserName());
        assertEquals(editedUserProfile.getEmail(), resultUser.get().getEmail());
        Mockito.verify(profileRepository).save(editedUserProfile);
    }

    @Test
    void testDeleteUser() throws ExecutionException, InterruptedException {
        UserProfile userProfile = createUserProfile("John Doe", "john@example.com", "password");
        Mockito.when(profileRepository.save(userProfile)).thenReturn(userProfile);
        profileService.create(userProfile);

        profileService.delete(userProfile.getId());

        Mockito.verify(profileRepository).deleteById(userProfile.getId());
    }
}
