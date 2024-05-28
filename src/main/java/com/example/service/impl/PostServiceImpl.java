// PostServiceImpl.java
package com.example.service.impl;

import com.example.entity.Post;
import com.example.exception.ResourceNotFound;
import com.example.payload.PostDto;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    @Override
    public Optional<PostDto> getPostById(long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.map(this::convertToDto);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFound("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }
    @Override
    public void updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id: " + id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        postRepository.save(post);
    }
    
    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
    }
}
