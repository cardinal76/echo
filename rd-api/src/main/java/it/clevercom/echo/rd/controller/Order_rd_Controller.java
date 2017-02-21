package it.clevercom.echo.rd.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("rd/assets/order")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Order_rd_Controller {

}
