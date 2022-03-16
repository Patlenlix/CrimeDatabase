package se.iths.crimedatabase.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.User;
import se.iths.crimedatabase.repository.UserRepository;

import java.util.List;

@Service
public class UserInit implements CommandLineRunner {
    UserRepository userRepository;

    public UserInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("user", "user123", "USER");
        User admin = new User("admin", "admin123", "ADMIN");
        this.userRepository.saveAll(List.of(user, admin));
    }
}
