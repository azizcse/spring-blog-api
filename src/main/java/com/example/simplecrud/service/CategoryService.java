package com.example.simplecrud.service;

import com.example.simplecrud.exception.UnauthorizedException;
import com.example.simplecrud.model.Category;
import com.example.simplecrud.payload.ApiResponse;
import com.example.simplecrud.payload.PagedResponse;
import com.example.simplecrud.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

	PagedResponse<Category> getAllCategories(int page, int size);

	ResponseEntity<Category> getCategory(Long id);

	ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser);

	ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserPrincipal currentUser)
			throws UnauthorizedException;

	ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException;

}
