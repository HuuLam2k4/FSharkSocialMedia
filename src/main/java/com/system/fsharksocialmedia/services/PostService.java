package com.system.fsharksocialmedia.services;

import com.system.fsharksocialmedia.dtos.PostDto;
import com.system.fsharksocialmedia.entities.Post;
import com.system.fsharksocialmedia.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

//code Mẫu
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    //    convert entity post to dto
    public PostDto convertToDto(Post post) {
        if (post == null) return null;
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setCreatedate(post.getCreatedate());
//        dto.setUsername(post.getUsername());
        return dto;
    }

    //    convert dto post to entity
    public Post convertToEntity(PostDto dto) {
        if (dto == null) return null;
        Post post = new Post();
        post.setId(dto.getId());
        post.setContent(dto.getContent());
        post.setCreatedate(dto.getCreatedate());
        return post;
    }

    public List<PostDto> convertToDTOList(List<Post> positions) {
        return positions.stream()
                .map(this::convertToDto) // Sử dụng phương thức convertToDTO
                .collect(Collectors.toList());
    }

    public List<PostDto> getAll() {
        try {
            List<Post> positions = postRepository.findAll();
            return positions.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách bài đăng: " + e.getMessage());
        }
    }

}
