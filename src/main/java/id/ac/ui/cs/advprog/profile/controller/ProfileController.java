package id.ac.ui.cs.advprog.profile.controller;

import id.ac.ui.cs.advprog.profile.model.User;
import id.ac.ui.cs.advprog.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try{
            User createdUser = profileService.create(user);
            res.put("User", createdUser);
            res.put("message", "User Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> User = profileService.findById(id);
            if (User.isEmpty()){
                response.put("code", HttpStatus.NOT_FOUND.value());
                response.put("message", "User with ID " + id + " not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(User);
        }catch (Exception e){
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", e.getMessage());
            response.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") int id, @RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try{
            User updatedUser = profileService.edit(user);
            res.put("User", updatedUser);
            res.put("message", "User" + updatedUser.getId() +" updated Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        Map<String, Object> res = new HashMap<>();
        try{
            profileService.delete(id);
            res.put("code", HttpStatus.OK.value());
            res.put("message", "User Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Something Wrong With Server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
