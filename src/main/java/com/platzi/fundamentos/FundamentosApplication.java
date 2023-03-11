package com.platzi.fundamentos;

import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.repository.UserRepository;
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
	public FundamentosApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {


	 SpringApplication.run(FundamentosApplication.class, args);

	}

	private void saveUsersInDB(){
		try{
			log.info("Inicio de datos dummi");
			User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
			User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
			User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
			User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
			User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
			User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
			User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
			User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
			User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
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
			userRepository.findAndSort("Luis", Sort.by("birthday").descending()).stream()
					.forEach(user -> log.info("Información de la lista "+user));
		}catch (Exception e){
			log.debug("Error en el método buscar y ordenar "+e);

		}
	}


	@Override
	public void run(String... args) throws Exception {
		saveUsersInDB();
		getInfoJPQLFromUser();
		getAndSort();
	}
}
