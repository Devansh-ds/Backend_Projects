package com.devansh.cart.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.devansh.cart.dto.ImageDto;
import com.devansh.cart.model.Image;

public interface IImageService {

	Image getImageById(Long id);
	void deleteImaegById(Long id);
	List<ImageDto> saveImage(List<MultipartFile> file, Long productId);
	void updateImage(MultipartFile file, Long imageId);
}
