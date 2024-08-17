package com.student_management.student_management_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.student_management.student_management_backend.DTO.DepartmentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @Lob
    @Basic(fetch = FetchType.EAGER) //ensures that the large object is generally loaded with the entity.
    private byte[] imageFile;
    private String imageType;
    private String imageName;
//    @Lob
//    private byte[] imageFile;
//    private String imageType;
//    private String imageName;
    private String departmentName;
    @ManyToOne
    @JoinColumn (name = "department_id")
//    @JsonManagedReference
    @JsonIgnore
    private Department department;

}
