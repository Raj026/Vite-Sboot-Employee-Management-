package com.student_management.student_management_backend.repository;

import com.student_management.student_management_backend.DTO.DepartmentAdminDto;
import com.student_management.student_management_backend.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentName(String DepartmentName);

    @Query("SELECT d.id FROM Department d WHERE d.departmentName =:n")
    Long getByDepartmentId(@Param("n") String departmentName);

}
