package com.employee.management.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Document("employees")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Please enter name")
    private String name;

    @NotBlank(message = "Please enter email")
    private String email;

    private double salary;

    @Size(min = 9, max = 10)
    private String telephone;

    private Date dateOfBirth;

    private String department;


    public Employee(Long id) {
        this.id = id;
    }
}
