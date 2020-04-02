package br.com.b3.repository;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b3.entity.User;

/**
 * Classe responsavel por implementar os cenários de testes do repositório de
 * usuario.
 * 
 * @author j.a.vasconcelos
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UserRepository repo;

	/**
	 * Metodo responsável por preparar o objeto.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("juliherms@gmail.com");
		user.setFirstName("Juliherms");
		user.setLastName("Vasconcelos");
		user.setLogin("juliherms");
		user.setPassword("123456");
		user.setPhone("5581999999999");
		
		repo.save(user);
		
	}
	
	/**
	 * Apaga todos os registros após cada ciclo de teste
	 */
	@After
    public final void tearDown() {
		repo.deleteAll();
	}
	
	/**
	 * Metodo responsavel por testar a pesquisa de um usuario por login
	 */
	@Test
	public void testPesquisarUsuarioPorLogin() {
		
		User obj = repo.findByLogin("juliherms");
		
		assertTrue(obj.getLogin().equals("juliherms"));
		
	}
	
	
	
	/**
	 * Metodo responsávle por tentar realizar um cadastro de um usuario com login ja existente
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testCadastroUsuarioComMesmoLogin() {
		
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("Marcos@gmail.com");
		user.setFirstName("Marcos");
		user.setLastName("Paulo");
		user.setLogin("juliherms");
		user.setPassword("123456");
		user.setPhone("5581999999999");

		repo.save(user);
	}
	
	/**
	 * Método responsável por tentar cadastrar um usuario com um email ja existente
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testCadastroUsuarioComMesmoEmail() {
		
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("juliherms@gmail.com");
		user.setFirstName("Marcos");
		user.setLastName("Paulo");
		user.setLogin("juliherms");
		user.setPassword("123456");
		user.setPhone("5581999999999");

		repo.save(user);

	}
	
	/**
	 * Testa a inclusão de um usuario no sistema
	 */
	@Test
	public void testCadastroUsuarioSucesso() {
		
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("juliherms1@gmail.com");
		user.setFirstName("Marcos1");
		user.setLastName("Paulo1");
		user.setLogin("juliherms1");
		user.setPassword("1234561");
		user.setPhone("5581999999999");
		
		repo.save(user);
		
		assertTrue(user.getId() > 0);
	}

}
