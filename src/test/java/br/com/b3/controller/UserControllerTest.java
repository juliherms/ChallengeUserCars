package br.com.b3.controller;

import org.junit.After;
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

import br.com.b3.repository.UserRepository;

/**
 * Classe responsável por testar as chamadas de endpoint da api/users
 * 
 * @author j.a.vasconcelos
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserRepository repo;

	/**
	 * Metodo responsável por testar a criação de um usuario via endpoint
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser
	public void testCriarUsuarioValido() throws Exception {

		String content = "{\"firstName\": \"Hello\",\"lastName\": \"World\",\"email\": \"hello@world.com\",\"birthday\": \"1990-05-01\",\"login\": \"hello.world\",\"password\": \"h3ll0\",\"phone\": \"988888888\",\"cars\": [{\"year\": 2018,\"licensePlate\": \"PDV-0625\",\"model\": \"Audi\",\"color\": \"White\"}]}";

		mvc.perform(MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Metodo responsável por testar a criação de um usuario via endpoint com o
	 * mesmo login.
	 * 
	 * @throws Exception
	 */
	@Test
	@WithMockUser
	public void testCriarUsuarioComMesmoLogin() throws Exception {

		String content = "{\"firstName\": \"Juliherms\",\"lastName\": \"Vasconcelos\",\"email\": \"j.a.vasconcelos321@gmail.com\",\"birthday\": \"1990-05-01\",\"login\": \"juliherms\",\"password\": \"h3ll0\",\"phone\": \"988888888\",\"cars\": [{\"year\": 2018,\"licensePlate\": \"PDV-0626\",\"model\": \"Audi\",\"color\": \"White\"}]}";

		mvc.perform(MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content)).andExpect(MockMvcResultMatchers.status().isCreated());

		String content1 = "{\"firstName\": \"Hello\",\"lastName\": \"World\",\"email\": \"juliherms@gmail.com\",\"birthday\": \"1991-05-01\",\"login\": \"juliherms\",\"password\": \"h3ll0\",\"phone\": \"988888888\",\"cars\": [{\"year\": 2018,\"licensePlate\": \"PDV-0626\",\"model\": \"Audi\",\"color\": \"White\"}]}";

		mvc.perform(MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	/**
	 * Apaga todos os registros após cada ciclo de teste
	 */
	@After
	public final void tearDown() {
		repo.deleteAll();
	}
}
