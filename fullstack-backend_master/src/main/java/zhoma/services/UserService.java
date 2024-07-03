package zhoma.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhoma.exception.UserNotFoundException;
import zhoma.model.User;
import zhoma.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save_user(User user) {
        return repository.save(user);
    }

    public User findById(int id) {
        Optional<User> user = Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " doesn't exist!!!")));
        return user.get();
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public String deleteUser(int id) {
        Optional<User> user = Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " doesn't exist!!!")));
        repository.deleteById(id);
        return "User with id = " + id + " deleted!!!";
    }

    public User editUser(int id, User newuser) {
        repository.findById(id).map(
                user -> {
                    user.setName(newuser.getName());
                    user.setEmail(newuser.getEmail());
                    user.setUsername(newuser.getUsername());
                    return repository.save(user);
                }).orElseThrow(() -> new UserNotFoundException("User with id " + id + " doesn't exist!!!"));

        return repository.findById(id).get();
    }

}
