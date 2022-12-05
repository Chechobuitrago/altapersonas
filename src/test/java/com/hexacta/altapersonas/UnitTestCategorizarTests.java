package com.hexacta.altapersonas;

import com.hexacta.altapersonas.model.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UnitTestCategorizarTests {

	private Persona persona;

	@BeforeEach
	void setup(){
		persona = new Persona();
	}

	@Test
	void categorizarTest() {
		persona.setEdad(10);
		persona.categorizar();
		Assertions.assertEquals(persona.getCategoria(),"Niños");
		persona.setEdad(16);
		persona.categorizar();
		Assertions.assertEquals(persona.getCategoria(),"Adolescentes");
		persona.setEdad(18);
		persona.categorizar();
		Assertions.assertEquals(persona.getCategoria(),"Adultos");
		persona.setEdad(80);
		persona.categorizar();
		Assertions.assertEquals(persona.getCategoria(),"Octogenario");
	}
	@Test
	void categorizarFailTest() {
		Assertions.assertThrows(ArithmeticException.class,()->{
			persona.setEdad(-1);
			persona.categorizar();
		});
	}


}
