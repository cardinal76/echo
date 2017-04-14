package it.clevercom.echo.common.controller;

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

/**
 * Echo Generic Controller
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
	    		} else if ("system_start".equals(text)) {
	    			Date sysStart = new Date(-3600000l);
	    			setValue(DateUtil.getEndOfDay(sysStart).getTime());
	    		} else if ("system_end".equals(text)) {
	    			Date sysEnd = new Date(2524604400000l);
	    			setValue(DateUtil.getEndOfDay(sysEnd).getTime());
	    		} else {
	    			super.setAsText(text);
	    		}
	    	}
	    };
	    
	    binder.registerCustomEditor(Long.class, today_tomorrow);
	}
}
