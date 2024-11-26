package academy.jairo.springboot.springcrud.service;

import academy.jairo.springboot.springcrud.exception.RecordNotFoundException;
import academy.jairo.springboot.springcrud.model.Course;
import academy.jairo.springboot.springcrud.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course update(Long id, Course courseDetails) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setName(courseDetails.getName());
                    existingCourse.setCategory(courseDetails.getCategory());
                    return courseRepository.save(existingCourse);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(Long id) {
        courseRepository.delete(
            courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );
        
    }
} 