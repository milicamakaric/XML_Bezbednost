package com.example.MegaTravel_XML.services;

import org.springframework.stereotype.Service;

import com.example.MegaTravel_XML.model.Image;

@Service
public interface ImageService {

	Image saveImage(Image img);
}
