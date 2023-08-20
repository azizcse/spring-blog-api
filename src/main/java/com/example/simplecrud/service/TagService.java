package com.example.simplecrud.service;


import com.example.simplecrud.model.Tag;
import com.example.simplecrud.payload.ApiResponse;
import com.example.simplecrud.payload.PagedResponse;
import com.example.simplecrud.security.UserPrincipal;

public interface TagService {

	PagedResponse<Tag> getAllTags(int page, int size);

	Tag getTag(Long id);

	Tag addTag(Tag tag, UserPrincipal currentUser);

	Tag updateTag(Long id, Tag newTag, UserPrincipal currentUser);

	ApiResponse deleteTag(Long id, UserPrincipal currentUser);

}
