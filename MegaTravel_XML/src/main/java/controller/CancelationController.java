package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.CancelationService;

@RestController
@RequestMapping(value="cancelation")
public class CancelationController {
	
	@Autowired
	private CancelationService cancelationService;

}
