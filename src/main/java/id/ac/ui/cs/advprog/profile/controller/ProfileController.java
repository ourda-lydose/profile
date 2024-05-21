package id.ac.ui.cs.advprog.profile.controller;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import id.ac.ui.cs.advprog.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/userprofile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public CompletableFuture<ResponseEntity<Map<String, Object>>> createUser(@RequestBody UserProfile user) {
        Map<String, Object> res = new HashMap<>();
        return profileService.create(user)
                .thenApply(createdUser -> {
                    res.put("User", createdUser);
                    res.put("message", "User Created Successfully");
                    return ResponseEntity.status(HttpStatus.CREATED).body(res);
                })
                .exceptionally(e -> {
                    res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    res.put("error", e.getMessage());
                    res.put("message", "Something Wrong With Server");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
                });
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> getUserById(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        return profileService.findById(id)
                .thenApply(User -> {
                    if (User.isEmpty()){
                        response.put("code", HttpStatus.NOT_FOUND.value());
                        response.put("message", "User with ID " + id + " not found.");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                    }
                    return ResponseEntity.ok(User.get());
                })
                .exceptionally(e -> {
                    response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    response.put("error", e.getMessage());
                    response.put("message", "Something Wrong With Server");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                });
    }


    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> editUser(@PathVariable("id") String id, @RequestBody UserProfile user) {
        Map<String, Object> res = new HashMap<>();
        return profileService.edit(user)
                .thenApply(updatedUser -> {
                    res.put("User", updatedUser);
                    res.put("message", "User " + updatedUser.getId() +" updated Successfully");
                    return ResponseEntity.status(HttpStatus.CREATED).body(res);
                })
                .exceptionally(e -> {
                    res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    res.put("error", e.getMessage());
                    res.put("message", "Something Wrong With Server");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> deleteUser(@PathVariable("id") String id){
        Map<String, Object> res = new HashMap<>();
        return profileService.delete(id)
                .thenApply(result -> {
                    res.put("code", HttpStatus.OK.value());
                    res.put("message", "User Deleted Successfully");
                    return ResponseEntity.status(HttpStatus.OK).body(res);
                })
                .exceptionally(e -> {
                    res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    res.put("error", e.getMessage());
                    res.put("message", "Something Wrong With Server");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
                });
    }
}
