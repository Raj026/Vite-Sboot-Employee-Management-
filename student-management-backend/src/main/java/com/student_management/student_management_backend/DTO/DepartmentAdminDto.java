package com.student_management.student_management_backend.DTO;

import com.student_management.student_management_backend.models.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentAdminDto {
    private String departmentName;
    private Integer strength;
    private List<String> students;

}
