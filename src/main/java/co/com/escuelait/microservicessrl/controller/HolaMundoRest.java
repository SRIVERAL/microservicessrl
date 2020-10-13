package co.com.escuelait.microservicessrl.controller;

import org.jboss.logging.MDC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.com.escuelait.microservicessrl.configuration.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HolaMundoRest {

	//private Logger log = LoggerFactory.getLogger(HolaMundoRest.class);
	
	@Autowired
	private ApplicationConfig appConfig;
	
	@GetMapping("/holaMundo/{name}")
	public String saludo(@PathVariable String name) {
		
		MDC.put("userid", name);
		
		System.out.println(appConfig.toString());
		
		log.trace("Ejecutando hola mundo trace");
		log.debug("Ejecutando hola mundo debug");
		log.info("Ejecutando hola mundo info");
		log.warn("Ejecutando hola mundo warn");
		log.error("Ejecutando hola mundo error");
		
		return "Hola Mundo Servicio Rest Java";
		
	}
}
