package com.platzi.fundamentos;

import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.repository.UserRepository;
import com.platzi.fundamentos.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@CommonsLog
public class FundamentosApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	public FundamentosApplication(UserRepository userRepository,UserService userService) {

		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {


	 SpringApplication.run(FundamentosApplication.class, args);

	}

	private void saveUsersInDB(){
		try{
			log.info("Inicio de datos dummi");
			User user1 = new User("John", "john@domain.com", LocalDate.of(2016, 3, 13));
			User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2017, 12, 8));
			User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2019, 9, 8));
			User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2020, 6, 18));
			User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
			User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2018, 7, 7));
			User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
			User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2016, 2, 27));
			User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2019, 4, 10));
			User user10 = new User("Luis", "luis2@domain.com", LocalDate.of(2021, 8, 27));
			User user11 = new User("Luis carlos", "luiscarlos@domain.com", LocalDate.of(2021, 5, 27));
			List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11);
			list.forEach(userRepository::save);
			log.info("Final de datos dunni");
		}catch (Exception e){
			log.error("Se ha generado un error en la carga de datos dummi "+e);

		}

	}

	private void getInfoJPQLFromUser(){
		log.info( "Esta es la información del usuario buscado"+userRepository.findByEmail("luis@domain.com")
				.orElseThrow(() ->new RuntimeException("No hay usuarios con ese mail")));


	}

	private void getAndSort(){
		try{
			userRepository.findAndSort("Luis", Sort.by("birthday").descending())
					.forEach(user -> log.info("Información de la lista "+user));

		}catch (Exception e){
			log.debug("Error en el método buscar y ordenar "+e);

		}
	}
	private void getByName(){
		try{
			userRepository.findByName("Luis")
					.forEach(user -> log.info(("Usuario por nombre "+user)));

	}catch (Exception e){
		log.error(e);
		}
	}
	//para usar Like se debe pasar la sentencia en medio de %nombre%
   private void getByNameLike(){
	   userRepository.findByNameLike("%Ma%").forEach(user -> log.info("Nombre like "+user));
	   userRepository.findByNameOrEmail(null,"luis2@domain.com")
			   .forEach(user -> log.info("User by mail or name"+user));
	   userRepository.findByBirthdayBetween(LocalDate.of(2016,05,15),
					   LocalDate.of(2019,03,18))
			   .forEach(user -> log.info("Usuarios por fecha "+user));
	  log.info("Usuario con parametros "+userRepository.getAllByBirthdayAndEmail(LocalDate.of(2019, 9, 8),"daniela@domain.com")
			  .orElseThrow(() -> new RuntimeException("No hay usuario que coincida")));
   }

   private void saveWithErrorTransactional(){
	   User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
	   User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
	   User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
	   User test4 = new User("TestTransactional4", "TestTransactional1@domain.com", LocalDate.now());

	   List<User> users = Arrays.asList(test1,test2,test3,test4);
	   try{
		   userService.saveTransactional(users);
	   }
	   catch (RuntimeException r){
		   log.error(r);
	   }

		userService.getAllUsers().forEach(user -> log.info("Usuarios: "+user));
	}

	@Override
	public void run(String... args) throws Exception {
		saveUsersInDB();
		/**getInfoJPQLFromUser();
		getAndSort();
		getByName();
		getByNameLike();*/
		saveWithErrorTransactional();
	}
}
