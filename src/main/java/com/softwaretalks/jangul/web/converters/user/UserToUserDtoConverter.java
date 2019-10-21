package com.softwaretalks.jangul.web.converters.user;

import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.web.dto.user.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter to convert from {@linkplain User} to {@linkplain UserDto}.
 *
 * @author sajad
 */
@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    /**
     * Converter.
     *
     * @param user Origin object.
     * @return Newly instantiated object of type {@linkplain UserDto} with the origin object's attributes.
     */
    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername);

        return userDto;
    }
}
