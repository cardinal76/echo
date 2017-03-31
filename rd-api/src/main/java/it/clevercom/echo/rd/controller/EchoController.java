package it.clevercom.echo.rd.controller;

import java.util.Date;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.util.DateUtil;

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
		
	    final CustomNumberEditor today_tomorrow = new CustomNumberEditor(Long.class, true) {
	    	@Override
	        public void setAsText(String text) throws IllegalArgumentException {
	    		if ("today_start".equals(text)) { 
	    			Date today = new Date();
	    			setValue(DateUtil.getStartOfDay(today).getTime());
	    		} else if ("today_end".equals(text)) {
	    			Date today = new Date();
	    			setValue(DateUtil.getEndOfDay(today).getTime());
	    		} else {
	    			super.setAsText(text);
	    		}
	    	}
	    };
	    
	    binder.registerCustomEditor(Long.class, today_tomorrow);
	}
}
