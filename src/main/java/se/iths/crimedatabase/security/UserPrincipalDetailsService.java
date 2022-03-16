package se.iths.crimedatabase.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.User;
import se.iths.crimedatabase.repository.UserRepository;

import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isEmpty())
            throw new UsernameNotFoundException("Can't find user with username " + username);
        return new UserPrincipal(user.get());
    }
}
