package it.clevercom.echo.rd.controller.toimplement;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;

@Controller
@RestController
@RequestMapping("rd/types/icd9patology")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class ICD9Patology_rd_Controller extends EchoController {

	@Override
	protected CreateRequestProcessor<?, ?, ?> getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UpdateRequestProcessor<?, ?, ?> getUpdater() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CriteriaRequestProcessor<?, ?, ?> getProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

}
