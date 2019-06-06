package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.PriceForNightService;

@RestController
@RequestMapping(value="api/price")
public class PriceForNightController {
	@Autowired
	private PriceForNightService priceService;
}
