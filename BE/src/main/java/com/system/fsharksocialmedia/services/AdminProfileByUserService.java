package com.system.fsharksocialmedia.services;

import com.system.fsharksocialmedia.dtos.ImageDto;
import com.system.fsharksocialmedia.dtos.UserDto;
import com.system.fsharksocialmedia.dtos.UserroleDto;
import com.system.fsharksocialmedia.entities.Image;
import com.system.fsharksocialmedia.entities.User;
import com.system.fsharksocialmedia.entities.Userrole;
import com.system.fsharksocialmedia.exceptions.UserNotFoundException;
import com.system.fsharksocialmedia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminProfileByUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public UserDto convertToDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setActive(user.getActive());
        dto.setBio(user.getBio());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        dto.setLastname(user.getLastname());
        dto.setFirstname(user.getFirstname());
        dto.setBirthday(user.getBirthday());
        dto.setHometown(user.getHometown());
        dto.setCurrency(user.getCurrency());

        if (user.getRoles() != null) {
            UserroleDto userRoleDto = new UserroleDto();
            userRoleDto.setRole(user.getRoles().getRole());
            dto.setRoles(userRoleDto);
        }

        if (user.getAvatar() != null) {
            ImageDto avatarDto = new ImageDto();
            avatarDto.setImage(user.getAvatar().getImage());
            dto.setAvatar(avatarDto);
        }

        if (user.getCover() != null) {
            ImageDto coverDto = new ImageDto();
            coverDto.setImage(user.getCover().getImage());
            dto.setCover(coverDto);
        }
        return dto;
    }

    // Convert UserDto to User entity (optional if needed)
    public User convertToEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setActive(dto.getActive());
        user.setBio(dto.getBio());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setLastname(dto.getLastname());
        user.setFirstname(dto.getFirstname());
        user.setBirthday(dto.getBirthday());
        user.setHometown(dto.getHometown());
        user.setCurrency(dto.getCurrency());
        if (dto.getRoles() != null) {
            user.setRoles(convertToEntity(dto.getRoles()));
        }

        if (dto.getAvatar() != null) {
            user.setAvatar(convertToEntity(dto.getAvatar()));
        }
        if (dto.getCover() != null) {
            user.setCover(convertToEntity(dto.getCover()));
        }
        return user;
    }


    private Userrole convertToEntity(UserroleDto dto) {
        Userrole userRole = new Userrole();
        userRole.setRole(dto.getRole());
        return userRole;
    }

    private Image convertToEntity(ImageDto dto) {
        Image image = new Image();
        image.setImage(dto.getImage());
        return image;
    }

    public UserDto getByUsername(String username) {
        if (username == null) return null;
        User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException("Không tìm thấy username: " + username));
        return convertToDto(user);
    }
}
