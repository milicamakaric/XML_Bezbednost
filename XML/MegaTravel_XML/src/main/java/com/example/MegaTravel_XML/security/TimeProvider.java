package com.example.MegaTravel_XML.security;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider {

	   public Date now() {
	        return new Date();
	    }
}
