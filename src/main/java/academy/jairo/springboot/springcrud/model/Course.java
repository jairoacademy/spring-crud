package academy.jairo.springboot.springcrud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import academy.jairo.springboot.springcrud.enums.Category;
import academy.jairo.springboot.springcrud.enums.Status;
import academy.jairo.springboot.springcrud.enums.converters.CategoryConverter;
import academy.jairo.springboot.springcrud.enums.converters.StatusConverter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Ativo'")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;
    
    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Builder.Default
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //@JoinColumn(name = "course_id") // evitar usar com questoes de performance
    private List<Lesson> lessons = new ArrayList<>();

} 