package com.example.simplecrud.service;


import com.example.simplecrud.model.Comment;
import com.example.simplecrud.payload.ApiResponse;
import com.example.simplecrud.payload.CommentRequest;
import com.example.simplecrud.payload.PagedResponse;
import com.example.simplecrud.security.UserPrincipal;

public interface CommentService {

	PagedResponse<Comment> getAllComments(Long postId, int page, int size);

	Comment addComment(CommentRequest commentRequest, Long postId, UserPrincipal currentUser);

	Comment getComment(Long postId, Long id);

	Comment updateComment(Long postId, Long id, CommentRequest commentRequest, UserPrincipal currentUser);

	ApiResponse deleteComment(Long postId, Long id, UserPrincipal currentUser);

}
