package it.clevercom.echo.common.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.common.util.JwtTokenUtils;

@Controller
@RestController
@PropertySource("classpath:rest.platform.properties")

/**
 * Echo Generic Controller
 * @author luca
 */

public class EchoController {
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	@Value("${echo.config.development.mode}")
	private String developmentMode;
	
	@Value("${echo.config.development.user}")
	private String developmentUser;
	
	@Value("${echo.config.development.application}")
	private String developmentApp;
	
	@Value("${echo.config.development.apikey}")
	private String apiKey;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		
	    final CustomNumberEditor all_date_options = new CustomNumberEditor(Long.class, true) {
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
	    
	    binder.registerCustomEditor(Long.class, all_date_options);
	}
	
	/**
	 * Get logged user using header contained into HttpServletRequest
	 * @param request
	 * @return
	 */
	protected String getLoggedUser(HttpServletRequest request) {
		String username = null;
		
		if (developmentMode.equals("true") && tokenUtils.validateApiKey(apiKey)) {
			// get username from token
			username = developmentUser;
		} else {
			// get username from token
			username = this.tokenUtils.getUsernameFromToken(request.getHeader(this.tokenHeader));
		}
		
		return username;
	}
}
