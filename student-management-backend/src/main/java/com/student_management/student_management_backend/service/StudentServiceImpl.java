package com.student_management.student_management_backend.service;

import com.student_management.student_management_backend.DTO.StudentDto;
import com.student_management.student_management_backend.models.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentServiceImpl {
    List<StudentDto> getAllStudents();

    StudentDto addStudent(StudentDto studentDto, MultipartFile image) throws IOException;

    String deleteStudent(Long id);

    StudentDto updateById(Long id, StudentDto studentDto);

    StudentDto getStudentById(Long id);

    Student getStudentByIdImage(Long id);

    byte[] getImageByStudent(Long id);
}
