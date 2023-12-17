package com.sept_g4.sept_project;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserService service;

    @Autowired
    UserController(UserService service){
        this.service = service;
    }
    
    // Register new user
    @PostMapping("/user")
    Users newUser(@RequestBody Users newUser ){
        return userRepository.save(newUser);
    }
    // Handle login logic
    // LoginDto takes email and password 
    @PostMapping("/login")
    ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        //Logic part
        LoginResponse loginResponse = service.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping("/login/{userEmail}")
    Users getUser(@PathVariable String userEmail){
        return userRepository.findByEmail(userEmail);
    }

    @GetMapping("/users")
    List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @PutMapping("/wishlist/{userId}")
    public ResponseEntity<?> updateUserWishlist(@PathVariable Long userId, @RequestBody String wishlist) {
        Users user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build(); // User not found
        }

        // Update the user's wishlist
        user.setWishlist(wishlist);
        userRepository.save(user);

        return ResponseEntity.ok("Wishlist updated successfully");
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<String> getUserWishlist(@PathVariable Long userId) {
        Users user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build(); // User not found
        }

        String wishlist = user.getWishlist(); // Get the user's wishlist

        return ResponseEntity.ok(wishlist);
    }

    

    

    

}
