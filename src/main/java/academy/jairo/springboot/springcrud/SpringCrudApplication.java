package academy.jairo.springboot.springcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import academy.jairo.springboot.springcrud.model.Course;
import academy.jairo.springboot.springcrud.repository.CourseRepository;

import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			
			Course c1 = Course.builder()
				.name("Angular")
				.category("Front-end")
				.build();
			
			Course c2 = Course.builder()
				.name("Spring")
				.category("Back-end")
				.build();
			
			courseRepository.saveAll(Arrays.asList(c1, c2));
		};
	}
}
