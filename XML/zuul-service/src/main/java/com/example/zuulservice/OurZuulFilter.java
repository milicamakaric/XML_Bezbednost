package com.example.zuulservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class OurZuulFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		// izvuci token iz zahteva i dodaj ga u zuul
		try {
			if(request.getHeader("Authorization") != ""){
				String token = request.getHeader("Authorization");
				System.out.println("token:" + token);
				ctx.addZuulRequestHeader("Authorization", token);
			}
		} catch(Exception e) {
			System.out.println("null kod filterType");
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return RequestContext.getCurrentContext().getRequest().getHeader("Authorization") != null;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
      	return "pre";
	}

}
