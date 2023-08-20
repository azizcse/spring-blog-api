package com.example.simplecrud.service;


import com.example.simplecrud.payload.ApiResponse;
import com.example.simplecrud.payload.PagedResponse;
import com.example.simplecrud.payload.PhotoRequest;
import com.example.simplecrud.payload.PhotoResponse;
import com.example.simplecrud.security.UserPrincipal;

public interface PhotoService {

	PagedResponse<PhotoResponse> getAllPhotos(int page, int size);

	PhotoResponse getPhoto(Long id);

	PhotoResponse updatePhoto(Long id, PhotoRequest photoRequest, UserPrincipal currentUser);

	PhotoResponse addPhoto(PhotoRequest photoRequest, UserPrincipal currentUser);

	ApiResponse deletePhoto(Long id, UserPrincipal currentUser);

	PagedResponse<PhotoResponse> getAllPhotosByAlbum(Long albumId, int page, int size);

}