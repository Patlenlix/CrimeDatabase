package se.iths.crimedatabase.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.User;
import se.iths.crimedatabase.repository.UserRepository;

import java.util.List;

@Service
public class UserInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        User user = new User("user", passwordEncoder.encode("user123"), "USER");
        User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN");
        this.userRepository.saveAll(List.of(user, admin));
    }
}
