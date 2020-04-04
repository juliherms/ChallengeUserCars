package br.com.b3.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Classe responsável por testar as chamadas de endpoint da api/signin
 * 
 * @author j.a.vasconcelos
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SessionControllerTest {

	@Autowired
	private MockMvc mvc;

	/**
	 * Metodo responsável por testar uma tentativa de login inválida
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser
	public void testLoginInvalido() throws Exception {

		String content = "{\"login\" : \"hello.world\",\"password\": \"h3ll0\"}";

		mvc.perform(MockMvcRequestBuilders.post("/api/signin").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content)).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	/**
	 * Metodo responsável por testar uma tentativa de login valida
	 * @throws Exception
	 */
	@Test
	@WithMockUser
	public void testLoginValido() throws Exception {
		
		String content = "{\"firstName\": \"Hello\",\"lastName\": \"World\",\"email\": \"hello@world.com\",\"birthday\": \"1990-05-01\",\"login\": \"hello.world\",\"password\": \"h3ll0\",\"phone\": \"988888888\",\"cars\": [{\"year\": 2018,\"licensePlate\": \"PDV-0625\",\"model\": \"Audi\",\"color\": \"White\"}]}";

		mvc.perform(MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content)).andExpect(MockMvcResultMatchers.status().isCreated());
		
		
		String contentLogin = "{\"login\" : \"hello.world\",\"password\": \"h3ll0\"}";

		mvc.perform(MockMvcRequestBuilders.post("/api/signin").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(contentLogin)).andExpect(MockMvcResultMatchers.status().isOk());

		
	}
}
