package com.system.fsharksocialmedia.services;

import com.system.fsharksocialmedia.dtos.*;
import com.system.fsharksocialmedia.entities.*;
import com.system.fsharksocialmedia.models.ConversationModel;
import com.system.fsharksocialmedia.models.MessageModel;
import com.system.fsharksocialmedia.repositories.MessageRepository;
import com.system.fsharksocialmedia.repositories.ConversationRepository;
import com.system.fsharksocialmedia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Gửi tin nhắn
    public MessageDto sendMessage(MessageModel messageModel) {
        Message message = new Message();
        message.setContent(messageModel.getContent());
        Message savedMessage = messageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/messages", savedMessage);
        return convertMessageToDto(savedMessage);
    }


    // Lấy tin nhắn theo ID cuộc trò chuyện
    public List<MessageDto> getMessagesByConversationId(Integer conversationId) {
        List<Message> messages = messageRepository.findByConversationId(conversationId);
        return messages.stream()
                .map(this::convertMessageToDto)
                .collect(Collectors.toList());
    }

    // Tạo cuộc trò chuyện
    public ConversationDto createConversation(ConversationModel conversationModel) {
        Conversation conversation = new Conversation();
        conversation.setName(conversationModel.getName());
//        // Thiết lập avatar nếu cần
//        if (conversationDto.getAvatar() != null) {
//            conversation.setAvatar(convertImageDtoToEntity(conversationDto.getAvatar()));
//        }
        conversation = conversationRepository.save(conversation);
        return convertConversationToDto(conversation);
    }


    // Chuyển đổi từ Message sang MessageDto
    public MessageDto convertMessageToDto(Message message) {
        if (message == null) return null;

        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setCreatedate(message.getCreatedate());

        // Chuyển đổi userSrc
        if (message.getUsersrc() != null) {
            UserDto userDto = convertUserToDto(message.getUsersrc());
            dto.setUsersrc(userDto);
        }

        // Chuyển đổi conversation
        if (message.getConversation() != null) {
            ConversationDto conversationDto = convertConversationToDto(message.getConversation());
            dto.setConversation(conversationDto);
        }

        return dto;
    }

    // Chuyển đổi từ MessageDto sang Message
    public Message convertMessageDtoToEntity(MessageDto dto) {
        if (dto == null) return null;

        Message message = new Message();
        message.setId(dto.getId());
        message.setContent(dto.getContent());
        message.setCreatedate(dto.getCreatedate());

        // Chuyển đổi userSrc
        if (dto.getUsersrc() != null) {
            User user = convertToEntity(dto.getUsersrc());
            message.setUsersrc(user);
        }

        // Chuyển đổi conversation
        if (dto.getConversation() != null) {
            Conversation conversation = convertToEntity(dto.getConversation());
            message.setConversation(conversation);
        }

        return message;
    }

    // Chuyển đổi từ Conversation sang ConversationDto
    private ConversationDto convertConversationToDto(Conversation conversation) {
        if (conversation == null) return null;

        ConversationDto dto = new ConversationDto();
        dto.setId(conversation.getId());
        dto.setName(conversation.getName());
        dto.setCreatedat(conversation.getCreatedat());

        // Chuyển đổi avatar nếu cần
        if (conversation.getAvatar() != null) {
            ImageDto avatarDto = new ImageDto();
            avatarDto.setImage(conversation.getAvatar().getImage());
            dto.setAvatar(avatarDto);
        }

        return dto;
    }

    // Chuyển đổi từ ConversationDto sang Conversation
    private Conversation convertToEntity(ConversationDto dto) {
        if (dto == null) return null;

        Conversation conversation = new Conversation();
        conversation.setId(dto.getId());
        conversation.setName(dto.getName());
        conversation.setCreatedat(dto.getCreatedat());

        // Chuyển đổi avatar nếu cần
        if (dto.getAvatar() != null) {
            Image image = new Image();
            image.setImage(dto.getAvatar().getImage());
            conversation.setAvatar(image);
        }

        return conversation;
    }

    // Chuyển đổi từ User sang UserDto
    private UserDto convertUserToDto(User user) {
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

    // Chuyển đổi từ UserDto sang User
    private User convertToEntity(UserDto dto) {
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
            Userrole userRole = new Userrole();
            userRole.setRole(dto.getRoles().getRole());
            user.setRoles(userRole);
        }

        if (dto.getAvatar() != null) {
            Image avatar = new Image();
            avatar.setImage(dto.getAvatar().getImage());
            user.setAvatar(avatar);
        }

        if (dto.getCover() != null) {
            Image cover = new Image();
            cover.setImage(dto.getCover().getImage());
            user.setCover(cover);
        }

        return user;
    }
}
