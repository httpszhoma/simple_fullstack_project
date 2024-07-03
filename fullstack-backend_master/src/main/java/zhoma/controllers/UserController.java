package zhoma.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zhoma.model.User;
import zhoma.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @PostMapping
    public User saveNewUser(@RequestBody User user) {
        return service.save_user(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User editUser(@PathVariable int id, @RequestBody User user) {
        return service.editUser(id, user);
    }
}
