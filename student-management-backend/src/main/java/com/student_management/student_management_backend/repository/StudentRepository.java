package com.student_management.student_management_backend.repository;

import com.student_management.student_management_backend.DTO.DepartmentAdminDto;
import com.student_management.student_management_backend.models.Department;
import com.student_management.student_management_backend.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
