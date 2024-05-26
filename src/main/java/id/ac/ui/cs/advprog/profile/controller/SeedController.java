package id.ac.ui.cs.advprog.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import id.ac.ui.cs.advprog.profile.service.SeedService;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
public class SeedController {
    @Autowired
    private SeedService seedService;

    @GetMapping("/seed-data")
    public ResponseEntity<String> seedMaster() {
        seedService.seed();
        return ResponseEntity.ok("Seeding data master completed successfully.");
    }
}
