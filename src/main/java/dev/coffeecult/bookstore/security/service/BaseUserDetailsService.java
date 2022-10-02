package dev.coffeecult.bookstore.security.service;

import dev.coffeecult.bookstore.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BaseUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public BaseUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User with username "+ username + " wasn't found")
        );
        return BaseUserDetails.build(user);
    }
}
