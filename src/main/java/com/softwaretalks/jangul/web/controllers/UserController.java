package com.softwaretalks.jangul.web.controllers;

import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.repositories.UserRepository;
import com.softwaretalks.jangul.web.dto.user.UserDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ConversionService conversionService;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          ConversionService conversionService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.conversionService = conversionService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> postUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User persisted = userRepository.save(user);

        UserDto userDto = conversionService.convert(persisted, UserDto.class);

        return ResponseEntity.ok(userDto);
    }
}
