package academy.jairo.springboot.springcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import academy.jairo.springboot.springcrud.enums.Category;
import academy.jairo.springboot.springcrud.model.Course;
import academy.jairo.springboot.springcrud.model.Lesson;
import academy.jairo.springboot.springcrud.repository.CourseRepository;

import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import java.util.ArrayList;

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
				.category(Category.FRONT_END)
				.lessons(new ArrayList<>())
				.build();
			
			c1.getLessons().add(Lesson.builder()
				.name("Introdução ao Angular")
				.youtubeUrl("12345678901")
				.course(c1)
				.build());
			
			c1.getLessons().add(Lesson.builder()
				.name("Componentes em Angular")
				.youtubeUrl("12345678902")
				.course(c1)
				.build());
			
			Course c2 = Course.builder()
				.name("Spring")
				.category(Category.BACK_END)
				.build();
			
			courseRepository.saveAll(Arrays.asList(c1, c2));
		};
	}
}
