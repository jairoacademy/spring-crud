package academy.jairo.springboot.springcrud.service;

import academy.jairo.springboot.springcrud.model.Course;
import academy.jairo.springboot.springcrud.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> update(Long id, Course courseDetails) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setName(courseDetails.getName());
                    existingCourse.setCategory(courseDetails.getCategory());
                    return courseRepository.save(existingCourse);
                });
    }

    public boolean delete(Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    courseRepository.delete(course);
                    return true;
                })
                .orElse(false);
    }
} 