package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.ReservationService;

@RestController
@RequestMapping(value="api/reservation")
public class ReservationController {
 @Autowired
 private ReservationService reservationService;
}
