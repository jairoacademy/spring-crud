package academy.jairo.springboot.springcrud.service;

import academy.jairo.springboot.springcrud.dto.CourseDTO;
import academy.jairo.springboot.springcrud.dto.CoursePageDTO;
import academy.jairo.springboot.springcrud.dto.mapper.CourseMapper;
import academy.jairo.springboot.springcrud.exception.RecordNotFoundException;
import academy.jairo.springboot.springcrud.model.Course;
import academy.jairo.springboot.springcrud.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    public CoursePageDTO findAllPageable(
            @PositiveOrZero int page,
            @Positive @Max(100) int pageSize) {
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, pageSize));
        List<CourseDTO> courses = pageCourse.get()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
    }

    public CourseDTO update(@NotNull @Positive Long id, CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    existingCourse.setName(courseDTO.getName());
                    existingCourse.setCategory(courseMapper.convertCategoryValue(courseDTO.getCategory()));
                    existingCourse.getLessons().clear();
                    course.getLessons().forEach(existingCourse.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(existingCourse));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(
            courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }

    
} 