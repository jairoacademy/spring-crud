package academy.jairo.springboot.springcrud.dto.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import academy.jairo.springboot.springcrud.enums.Category;
import academy.jairo.springboot.springcrud.model.Lesson;
import org.springframework.stereotype.Component;

import academy.jairo.springboot.springcrud.dto.CourseDTO;
import academy.jairo.springboot.springcrud.dto.LessonDTO;
import academy.jairo.springboot.springcrud.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (Objects.isNull(course)) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
            .stream()
            .map(lesson -> LessonDTO.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .youtubeUrl(lesson.getYoutubeUrl())
                .build())
            .toList();
        return CourseDTO.builder()
            .id(course.getId())
            .name(course.getName())
            .category(course.getCategory().getValue())
            .lessons(lessons)
            .build();
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (Objects.isNull(courseDTO)) {
            return null;
        }
        Course course = Course.builder()
                .id(courseDTO.getId())
                .name(courseDTO.getName())
                .category(convertCategoryValue(courseDTO.getCategory()))
                .build();

        List<Lesson> lessons = courseDTO.getLessons().stream().map(
                lessonDTO -> {
                    return Lesson.builder()
                            .id(lessonDTO.getId())
                            .name(lessonDTO.getName())
                            .youtubeUrl(lessonDTO.getYoutubeUrl())
                            .course(course)
                            .build();
                }).collect((Collectors.toList()));
        course.setLessons(lessons);

        return course;
    }

    public Category convertCategoryValue(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return switch (value) {
            case "Back-end" -> Category.BACK_END;
            case "Front-end" -> Category.FRONT_END;
            default -> throw new IllegalArgumentException("Invalid value " + value);
        };
    }
}
