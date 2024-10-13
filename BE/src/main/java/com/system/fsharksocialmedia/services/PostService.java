package com.system.fsharksocialmedia.services;

import com.system.fsharksocialmedia.dtos.PostDto;
import com.system.fsharksocialmedia.entities.Post;
import com.system.fsharksocialmedia.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Page<PostDto> getPost(int page, int size, String search) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Post> posts = (search == null || search.isEmpty())
                    ? postRepository.findAll(pageable)
                    : postRepository.findByContentContainingIgnoreCase(search, pageable);
            return posts.map(this::convertToDto);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving post list: " + e.getMessage());
        }
    }

    public PostDto updatePost(int postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
        try {

            post.setContent(postDto.getContent());
            post.setCreatedate(postDto.getCreatedate());
            Post updatedPost = postRepository.save(post);
            return convertToDto(updatedPost);
        } catch (Exception e) {
            throw new RuntimeException("Error updating post: " + e.getMessage());
        }
    }

    // Delete a post by its ID
    public void deletePost(int postId) {
        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found with ID: " + postId);
        }
        postRepository.deleteById(postId);
    }

}
