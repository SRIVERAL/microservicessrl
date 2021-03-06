package co.com.escuelait.microservicessrl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImplTest {

	@BeforeAll
	static void setup() {
		log.info("@BeforeAll - executes once before all test methods in this class.");
	}
	
	@BeforeEach
	public void setUpBefore() {
		log.info("@BEforeEach - executed before each test method.");
	}
	
	@Test
	public void test1() {
		log.info("Ejecutando test 1");
	}
	
	@Test
	public void test2() {
		log.info("Ejecutando test 2");
	}
	
	@Test
	public void test3() {
		log.info("Ejecutando test 3");
	}
	
	@AfterEach
	void tearDown() {
		log.info("@AfterEach - executed after each test method.");
	}
	
	@AfterAll
	static void done() {
		log.info("@AfterAll - executed after all test methods.");
	}
	
}
