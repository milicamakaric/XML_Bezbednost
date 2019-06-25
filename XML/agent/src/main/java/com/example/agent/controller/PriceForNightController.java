package com.example.agent.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agent.model.PriceForNight;
import com.example.agent.model.Room;
import com.example.agent.repository.RoomRepository;
import com.example.agent.services.PriceForNightService;
import com.example.agent.services.RoomService;

@RestController
@RequestMapping(value="price")
@CrossOrigin(origins = "http://localhost:4202")
public class PriceForNightController {
	
	@Autowired
	private PriceForNightService priceService;
	
	@Autowired
	private RoomService roomService;
	
	@PreAuthorize("hasAuthority('addSpecialPrice')")
	@RequestMapping(value = "/addSpecialPrice/{id}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addSpecialPrice(@PathVariable("id") Long room_id, @RequestBody PriceForNight sp) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		Room room = roomService.findById(room_id);
		
		System.out.println("add price entered"+sp.getEndDate()+sp.getStartDate());
		PriceForNight saved =new PriceForNight();
		List<PriceForNight> prices = room.getPrices();
		Date beginDate = sp.getStartDate();
				
		int year=beginDate.getYear();
		//meseci u javi od 0
		int month=beginDate.getMonth();
		int day=beginDate.getDay();
		calendar.clear();
		calendar.set(year+1900, month-1, day+1);
		
		beginDate = calendar.getTime();
		calendar.clear();
		Date endDate = sp.getEndDate();
		year = endDate.getYear();
		month = endDate.getMonth()-1;
		day = endDate.getDay();
		calendar.set(year, month, day);
		endDate = calendar.getTime();
		
		System.out.println("datum string j e "+endDate.toString()+" god "+year+" mje "+month+"dani"+day);
		
		if(prices.isEmpty()) {
		System.out.println("usao u empty");
			saved = this.priceService.savePriceForNight(sp);
			room.getPrices().add(saved);
			roomService.saveRoom(room);
		}else {
			PriceForNight found = null;
			for(PriceForNight price :prices) {
				Date beginDateBase = price.getStartDate();
				Date endDateBase = price.getEndDate();
				calendar.clear();
				
				year = beginDateBase.getYear()+1900;
				month = beginDateBase.getMonth()-1;
				day = beginDateBase.getDay();
				calendar.set(year, month, day);
				beginDateBase = calendar.getTime();
				calendar.clear();
				
				year = endDateBase.getYear()+1900;
				month = endDateBase.getMonth()-1;
				day = endDateBase.getDay();
				calendar.set(year, month, day);
				endDateBase = calendar.getTime();
				
				System.out.println("datum string j e BAZA "+beginDateBase);
				System.out.println("end datum string j e BAZA"+endDateBase);
				
				if(beginDate.compareTo(beginDateBase)<0 ) {
					if(endDate.compareTo(endDateBase)<0 && endDate.compareTo(beginDateBase)>0) {
						System.out.println("usao u prvi if");
						price.setStartDate(sp.getEndDate());
						this.priceService.savePriceForNight(price);
						room.getPrices().add(price);
						
						saved = this.priceService.savePriceForNight(sp);
						room.getPrices().add(saved);
						roomService.saveRoom(room);
				
						return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
					
					}
				}
				
				if(beginDate.compareTo(beginDateBase) > 0 && beginDate.compareTo(endDateBase)<0) {
					if(endDate.compareTo(endDateBase)<0) {
						System.out.println("usao u drugi if");
						
						price.setEndDate(sp.getStartDate());
						this.priceService.savePriceForNight(price);
						room.getPrices().add(price);
						
						PriceForNight newPrice = new PriceForNight();
						newPrice.setStartDate(sp.getEndDate());
						newPrice.setEndDate(price.getEndDate());
						newPrice.setPrice(price.getPrice());
						
						this.priceService.savePriceForNight(newPrice);
						room.getPrices().add(newPrice);
						
						saved = this.priceService.savePriceForNight(sp);
						room.getPrices().add(saved);
						roomService.saveRoom(room);
				
						return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
					
					}
					
				}
				if(beginDate.compareTo(beginDateBase) >0 && beginDate.compareTo(endDateBase)<0) {
					if(endDate.compareTo(endDateBase)>0) {
						price.setEndDate(sp.getStartDate());
						
						System.out.println("usao u treci if");
						
						this.priceService.savePriceForNight(price);
						room.getPrices().add(price);
						
						saved = this.priceService.savePriceForNight(sp);
						room.getPrices().add(saved);
						roomService.saveRoom(room);
				
						return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
					
					}	
				}
			}
			
			saved = this.priceService.savePriceForNight(sp);
			room.getPrices().add(saved);
			roomService.saveRoom(room);

			System.out.println("nista od ovih");
			return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
			
		}
		return  new ResponseEntity<PriceForNight>(saved, HttpStatus.OK);
		
	}
}
