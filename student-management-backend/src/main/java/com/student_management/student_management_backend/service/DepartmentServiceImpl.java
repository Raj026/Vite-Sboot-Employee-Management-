package com.student_management.student_management_backend.service;

import com.student_management.student_management_backend.DTO.DepartmentAdminDto;
import com.student_management.student_management_backend.DTO.DepartmentDto;
import com.student_management.student_management_backend.models.Department;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DepartmentServiceImpl {
    List<DepartmentDto> getAllDepartments();

    DepartmentDto addDepartment(DepartmentDto department, MultipartFile image) throws IOException;



    DepartmentAdminDto getDepartment(Long id);

    Department getImageByDepartment(Long id);
}
