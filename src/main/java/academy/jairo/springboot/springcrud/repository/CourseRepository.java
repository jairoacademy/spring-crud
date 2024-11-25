package academy.jairo.springboot.springcrud.repository;

import academy.jairo.springboot.springcrud.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  
} 