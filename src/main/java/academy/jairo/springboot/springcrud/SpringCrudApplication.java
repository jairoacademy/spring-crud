package academy.jairo.springboot.springcrud;

import academy.jairo.springboot.springcrud.enums.Category;
import academy.jairo.springboot.springcrud.model.Course;
import academy.jairo.springboot.springcrud.model.Lesson;
import academy.jairo.springboot.springcrud.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@Bean
	@Profile("dev")
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

			courseRepository.save(c1);

			for (int i = 0; i < 20; i++) {
				Course c2 = Course.builder()
						.name("Spring " + i)
						.category(Category.BACK_END)
						.lessons(new ArrayList<>())
						.build();

				c2.getLessons().add(Lesson.builder()
						.name("Introdução ao Spring")
						.youtubeUrl("12345678901")
						.course(c2)
						.build());

				courseRepository.save(c2);
			}

		};
	}
}
