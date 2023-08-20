package com.example.simplecrud.service;


import com.example.simplecrud.model.Post;
import com.example.simplecrud.payload.ApiResponse;
import com.example.simplecrud.payload.PagedResponse;
import com.example.simplecrud.payload.PostRequest;
import com.example.simplecrud.payload.PostResponse;
import com.example.simplecrud.security.UserPrincipal;

public interface PostService {

	PagedResponse<Post> getAllPosts(int page, int size);

	PagedResponse<Post> getPostsByCreatedBy(String username, int page, int size);

	PagedResponse<Post> getPostsByCategory(Long id, int page, int size);

	PagedResponse<Post> getPostsByTag(Long id, int page, int size);

	Post updatePost(Long id, PostRequest newPostRequest, UserPrincipal currentUser);

	ApiResponse deletePost(Long id, UserPrincipal currentUser);

	PostResponse addPost(PostRequest postRequest, UserPrincipal currentUser);

	Post getPost(Long id);

}
