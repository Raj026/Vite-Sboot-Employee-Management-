package com.student_management.student_management_backend.service;

import com.student_management.student_management_backend.DTO.DepartmentAdminDto;
import com.student_management.student_management_backend.DTO.DepartmentDto;
import com.student_management.student_management_backend.models.Department;
import com.student_management.student_management_backend.models.Student;
import com.student_management.student_management_backend.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements DepartmentServiceImpl {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments =  departmentRepository.findAll();
//        return departments.stream().map(department -> new DepartmentDto(
//                department.getDepartmentName(), department.getStrength()))
//                .collect(Collectors.toList());
        return departments.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @Override
    public DepartmentDto addDepartment(DepartmentDto department, MultipartFile image) throws IOException {
        Department dept = convertToEntity(department, image);
        Department savedDepartment = departmentRepository.save(dept);
        return convertToDto(savedDepartment);
    }

    @Override
    public DepartmentAdminDto getDepartment(Long id) {
        Department dept = departmentRepository.findById(id).orElse(null);
        DepartmentAdminDto admin = new DepartmentAdminDto();
        if(dept !=null) {
            admin.setDepartmentName(dept.getDepartmentName());
            admin.setStrength(dept.getStrength());
            List<String> studs = dept.getDepartmentStudents().stream()
                    .map(student -> String.format("First Name: %s, Last Name: %s, Email: %s",
                            student.getFirstName(), student.getLastName(), student.getEmail()))
                    .toList(); //.collect(Collectors.toList());
            admin.setStudents(studs);

//            DepartmentAdminDto d = departmentRepository.getByDepartmentId(dept.getDepartmentName());

        }
        return admin;
    }

    @Override
    public Department getImageByDepartment(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }


    public DepartmentDto convertToDto(Department department) {
        if(department == null) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName(department.getDepartmentName());
        departmentDto.setStrength(department.getStrength());
        List<Student> st = department.getDepartmentStudents();
        if(st == null) {
            st = new ArrayList<>();

        }
        departmentDto.setVacantSeats(department.getStrength() - st.size());


//        List<Student> st = department.getDepartmentStudents();

        return departmentDto;
    }

    public Department convertToEntity(DepartmentDto departmentDto,MultipartFile image) throws IOException {
        if(departmentDto == null) {
            return null;
        }

        Department department = new Department();
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setStrength(departmentDto.getStrength());
        List<Student> studs = department.getDepartmentStudents();
        if(studs == null) {
            studs = new ArrayList<>();
        }
        department.setDepartmentStudents(studs);
        department.setVacantSeats(department.getStrength() - department.getDepartmentStudents().size());
        if(image != null  && !image.isEmpty()) {
            department.setImageType(image.getContentType());
            department.setImageName(image.getOriginalFilename());
            department.setDepartmentImage(image.getBytes());
        }

        return department;
    }
}
