package br.com.b3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.b3.entity.User;

/**
 * Classe responsavel por manipular os dados de um usuario
 * 
 * @author j.a.vasconcelos
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
