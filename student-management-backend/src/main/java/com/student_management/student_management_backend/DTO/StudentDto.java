package com.student_management.student_management_backend.DTO;

import com.student_management.student_management_backend.models.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String  departmentName;

    private byte[] imageFile;
    private String imageType;
    private String imageName;

    public StudentDto(String firstName, String lastName, String email, String departmentName, byte[] imageFile, String imageType, String imageName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.imageFile = imageFile;
        this.imageType = imageType;
        this.imageName = imageName;
    }


    public StudentDto(String firstName, String lastName, String email, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
    }

    public StudentDto(){}
}
