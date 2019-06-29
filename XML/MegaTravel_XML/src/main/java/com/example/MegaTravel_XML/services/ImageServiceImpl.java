package com.example.MegaTravel_XML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Image;
import com.example.MegaTravel_XML.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public Image saveImage(Image img) {
		System.out.println("In saveImage function");
		return imageRepository.save(img);
	}

}
