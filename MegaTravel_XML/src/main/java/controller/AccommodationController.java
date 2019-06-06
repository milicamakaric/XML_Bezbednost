package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.AccommodationService;

@RestController
@RequestMapping(value="api/accommodation")
public class AccommodationController {

	@Autowired
	private AccommodationService accommodationService;
}
