package it.clevercom.echo.rd.controller.toimplement;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.controller.EchoController;

@Controller
@RestController
@RequestMapping("rd/icd9patologygroup")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class OrderLog_rd_Controller extends EchoController {

}
