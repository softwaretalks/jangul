package com.softwaretalks.jangul.security;

import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.repositories.UserRepository;
import com.softwaretalks.jangul.services.NotFoundUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtUtil {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() throws NotFoundUserException {

        Optional<User> user = userRepository.findByEmail(getEmailCurrentUser());
        if (user.isEmpty())
            throw new NotFoundUserException();
        return user.get();
    }

    public String getEmailCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof User) {
            username = ((User) principal).getEmail();
        } else if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else
            username = principal.toString();

        return username;
    }

}
