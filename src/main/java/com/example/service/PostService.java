// PostService.java
package com.example.service;

import com.example.payload.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDto savePost(PostDto postDto);

    Optional<PostDto> getPostById(long id);

    List<PostDto> getAllPosts();

    void deletePost(long id);

    void updatePost(long id, PostDto postDto);

    List<PostDto> getPosts();
}
