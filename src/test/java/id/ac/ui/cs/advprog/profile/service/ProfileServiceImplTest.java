package id.ac.ui.cs.advprog.profile.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.profile.service.ProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    void testCreate_HappyPath() {
        UserProfile user = new UserProfile();
        user.setId("1");

        when(profileRepository.save(user)).thenReturn(user);

        CompletableFuture<UserProfile> futureUser = profileService.create(user);

        assertTrue(futureUser.join() != null);
        assertEquals("1", futureUser.join().getId());
        verify(profileRepository, times(1)).save(user);
    }

    @Test
    void testFindAll_HappyPath() {
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile());
        users.add(new UserProfile());

        when(profileRepository.findAll()).thenReturn(users);

        CompletableFuture<List<UserProfile>> futureUsers = profileService.findAll();

        assertEquals(2, futureUsers.join().size());
        verify(profileRepository, times(1)).findAll();
    }

    @Test
    void testEdit_HappyPath() {
        UserProfile user = new UserProfile();
        user.setId("1");

        when(profileRepository.save(user)).thenReturn(user);

        CompletableFuture<UserProfile> futureUser = profileService.edit(user);

        assertTrue(futureUser.join() != null);
        assertEquals("1", futureUser.join().getId());
        verify(profileRepository, times(1)).save(user);
    }

    @Test
    void testDelete_HappyPath() {
        String id = "1";

        CompletableFuture<Void> futureVoid = profileService.delete(id);

        assertNull(futureVoid.join());
        verify(profileRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById_HappyPath() {
        String id = "1";
        UserProfile user = new UserProfile();
        user.setId(id);

        when(profileRepository.findById(id)).thenReturn(Optional.of(user));

        CompletableFuture<Optional<UserProfile>> futureUser = profileService.findById(id);

        assertTrue(futureUser.join().isPresent());
        assertEquals("1", futureUser.join().get().getId());
        verify(profileRepository, times(1)).findById(id);
    }

    @Test
    void testFindById_UnhappyPath() {
        String id = "1";

        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        CompletableFuture<Optional<UserProfile>> futureUser = profileService.findById(id);

        assertFalse(futureUser.join().isPresent());
        verify(profileRepository, times(1)).findById(id);
    }
}
