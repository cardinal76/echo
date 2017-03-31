package it.clevercom.echo.rd.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Order Controller
 * @author luca
 */

public class EchoController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
	        
	    final CustomNumberEditor today = new CustomNumberEditor(Long.class, true) {
	    	@Override
	        public void setAsText(String text) throws IllegalArgumentException {
	    		if ("today".equals(text)) {
	    			Date today = new Date();
	    			setValue(today.getTime());
	    		} else {
	    			super.setAsText(text);
	    		}
	    	}
	    };
	    
	    final CustomNumberEditor tomorrow = new CustomNumberEditor(Long.class, true) {
	    	@Override
	        public void setAsText(String text) throws IllegalArgumentException {
	    		if ("tomorrow".equals(text)) {
	    			Date today = new Date();
	    			Calendar c = Calendar.getInstance(); 
	    			c.setTime(today); 
	    			c.add(Calendar.DATE, 1);
	    			Date tomorrow = c.getTime();
	    			setValue(tomorrow.getTime());
	    		} else {
	    			super.setAsText(text);
	    		}
	    	}
	    };
	    
	    binder.registerCustomEditor(Long.class, today);
	    binder.registerCustomEditor(Long.class, tomorrow);

	}
	
}
