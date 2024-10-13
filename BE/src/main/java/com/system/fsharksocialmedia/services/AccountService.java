package com.system.fsharksocialmedia.services;

import com.system.fsharksocialmedia.dtos.UserDto;
import com.system.fsharksocialmedia.dtos.UserroleDto;
import com.system.fsharksocialmedia.dtos.ImageDto;
import com.system.fsharksocialmedia.entities.Image;
import com.system.fsharksocialmedia.entities.User;
import com.system.fsharksocialmedia.entities.Userrole;
import com.system.fsharksocialmedia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

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

    // Helper method to convert UserroleDto to UserRole entity (if needed)
    private Userrole convertToEntity(UserroleDto dto) {
        Userrole userRole = new Userrole();
        userRole.setRole(dto.getRole());
        return userRole;
    }

    // Helper method to convert ImageDto to Image entity (if needed)
    private Image convertToEntity(ImageDto dto) {
        Image image = new Image();
        image.setImage(dto.getImage());
        return image;
    }
    public Page<UserDto> getUsers(int page, int size, String search) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<User> users = (search == null || search.isEmpty())
                    ? userRepository.findAll(pageable)
                    : userRepository.findByUsernameContainingIgnoreCase(search, pageable);
            return users.map(this::convertToDto);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user list: " + e.getMessage());
        }
    }

//    public void updateAccountStatus(String username, Boolean active) {
//        User user = userRepository.findById(username)
//                .orElseThrow(() -> new UserNotFoundException(username));
//        user.setActive(active);
//        userDAO.save(user);
//        messagingTemplate.convertAndSend("/topic/account-status", convertToDTO(user));
//    }
    // Delete a user by their ID
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
