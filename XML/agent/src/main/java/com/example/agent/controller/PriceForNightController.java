package com.example.agent.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agent.model.PriceForNight;
import com.example.agent.model.Room;
import com.example.agent.model.SaveRoomResponse;
import com.example.agent.services.PriceForNightService;
import com.example.agent.services.RoomService;
import com.example.agent.soap.UpdateClient;

@RestController
@RequestMapping(value="price")
@CrossOrigin(origins = "http://localhost:4202")
public class PriceForNightController {
	
	@Autowired
	private PriceForNightService priceForNightService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private UpdateClient updateClient;
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAuthority('addSpecialPrice')")
	@RequestMapping(value = "/addSpecialPrice/{id}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSpecialPrice(@PathVariable("id") Long room_id, @RequestBody PriceForNight sp) {
		sp.getStartDate().setHours(0);
		sp.getEndDate().setHours(0);
		Date beginDate = sp.getStartDate();
		Date endDate = sp.getEndDate();
		
		System.out.println("add price entered"+sp.getEndDate()+sp.getStartDate());
		PriceForNight saved =new PriceForNight();
		Room room = roomService.findById(room_id);
		List<PriceForNight> prices = room.getPrices();
		
		if(prices.isEmpty()) {
			System.out.println("usao u empty");
			//saved = this.priceService.savePriceForNight(sp);
			room.getPrices().add(sp);
			
			SaveRoomResponse response = updateClient.saveRoom(room);
			System.out.println("saved in response: " + response.isSaved());
			
			if(response.isSaved()) {
				System.out.println("soba sa specijalnim cenama je sacuvana u glavnom beku");
				roomService.saveRoom(response.getRoom());
				return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
			}else {
				System.out.println("soba sa specijalnim cenama nije sacuvana u glavnom beku");
				return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
			}
			
		}else {
			PriceForNight found = null;
			for(PriceForNight price :prices) {
				
				Date beginDateBase = price.getStartDate();
				Date endDateBase = price.getEndDate();
				System.out.println(" baza begin date "+beginDateBase + " baza end date "+endDateBase);
				if(beginDate.before(beginDateBase)) {
					if(endDate.before(endDateBase) && endDate.after(beginDateBase)) {
						System.out.println("usao u prvi if");
						price.setStartDate(sp.getEndDate());
						//this.priceService.savePriceForNight(price);
						room.getPrices().remove(price);
						room.getPrices().add(price);
						
						//saved = this.priceService.savePriceForNight(sp);
						room.getPrices().add(sp);
						
						SaveRoomResponse response = updateClient.saveRoom(room);
						System.out.println("saved in response: " + response.isSaved());
						
						if(response.isSaved()) {
							System.out.println("soba sa specijalnim cenama je sacuvana u glavnom beku");
							roomService.saveRoom(response.getRoom());
							return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
						}else {
							System.out.println("soba sa specijalnim cenama nije sacuvana u glavnom beku");
							return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
						}
					
					}
				}
				
				if(beginDate.after(beginDateBase)  && beginDate.before(endDateBase)) {
					if(endDate.before(endDateBase)) {
						System.out.println("usao u drugi if");
						
						PriceForNight newPrice = new PriceForNight();
						newPrice.setStartDate(sp.getEndDate());
						newPrice.setEndDate(price.getEndDate());
						newPrice.setPrice(price.getPrice());
						
						price.setEndDate(sp.getStartDate());
						//this.priceService.savePriceForNight(price);
						room.getPrices().remove(price);
						
						room.getPrices().add(price);
						
						
						//this.priceService.savePriceForNight(newPrice);
						room.getPrices().add(newPrice);
						
						//saved = this.priceService.savePriceForNight(sp);
						room.getPrices().add(sp);
						
						SaveRoomResponse response = updateClient.saveRoom(room);
						System.out.println("saved in response: " + response.isSaved());
						
						if(response.isSaved()) {
							System.out.println("soba sa specijalnim cenama je sacuvana u glavnom beku");
							roomService.saveRoom(response.getRoom());
							return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
						}else {
							System.out.println("soba sa specijalnim cenama nije sacuvana u glavnom beku");
							return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
						}
					
					}
					
				}
				if(beginDate.after(beginDateBase) && beginDate.before(endDateBase)) {
					if(endDate.after(endDateBase)) {
						price.setEndDate(sp.getStartDate());
						
						System.out.println("usao u treci if");
						
						//this.priceService.savePriceForNight(price);
						room.getPrices().remove(price);
						
						room.getPrices().add(price);
						
						//saved = this.priceService.savePriceForNight(sp);
						room.getPrices().add(sp);
						
						SaveRoomResponse response = updateClient.saveRoom(room);
						System.out.println("saved in response: " + response.isSaved());
						
						if(response.isSaved()) {
							System.out.println("soba sa specijalnim cenama je sacuvana u glavnom beku");
							roomService.saveRoom(response.getRoom());
							return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
						}else {
							System.out.println("soba sa specijalnim cenama nije sacuvana u glavnom beku");
							return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
						}
					
					}	
				}
			}
			
			//saved = this.priceService.savePriceForNight(sp);
			room.getPrices().add(sp);
			
			SaveRoomResponse response = updateClient.saveRoom(room);
			System.out.println("saved in response: " + response.isSaved());
			
			if(response.isSaved()) {
				System.out.println("soba sa specijalnim cenama je sacuvana u glavnom beku");
				roomService.saveRoom(response.getRoom());
				return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
			}else {
				System.out.println("nista od ovih");
				return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
			}
			
		}
		
	}
}
