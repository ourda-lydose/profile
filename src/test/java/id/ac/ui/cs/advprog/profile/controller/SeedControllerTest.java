package id.ac.ui.cs.advprog.profile.controller;

import id.ac.ui.cs.advprog.profile.service.SeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SeedControllerTest {

    @Mock
    private SeedService seedService;

    @InjectMocks
    private SeedController seedController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSeedMaster() {
        doNothing().when(seedService).seed();

        ResponseEntity<String> responseEntity = seedController.seedMaster();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Seeding data master completed successfully.", responseEntity.getBody());
        verify(seedService, times(1)).seed();
    }

    @Test
    void testSeedMaster_WhenExceptionOccurs() {
        doThrow(new RuntimeException()).when(seedService).seed();

        try {
            seedController.seedMaster();
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
        verify(seedService, times(1)).seed();
    }
}