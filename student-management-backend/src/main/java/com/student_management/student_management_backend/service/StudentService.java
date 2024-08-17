package com.student_management.student_management_backend.service;

import com.student_management.student_management_backend.DTO.DepartmentDto;
import com.student_management.student_management_backend.DTO.StudentDto;
import com.student_management.student_management_backend.models.Department;
import com.student_management.student_management_backend.models.Student;
import com.student_management.student_management_backend.repository.DepartmentRepository;
import com.student_management.student_management_backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentServiceImpl{

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
//        for(Student st:students) {
//            Department department = st.getDepartment();
//            System.out.println(department);
//            if(department != null) {
//                st.setDepartmentDto(department.getDepartmentName(), department.getStrength());
//                System.out.println(st);
//            }
//        }
        return students.stream().map(this::convertToDtoUpdate).collect(Collectors.toList());
    }

    @Transactional // This mitigates the unable to stream lob error .
    // The annotation is basically used to mark the method or class so that it gets executed within the transaction otherwise if an error occurs
    // then the transaction is rolled back.
    @Override
    public StudentDto addStudent(StudentDto student , MultipartFile image) throws IOException {
        Student st = convertToEntity(student, image);
        Student savedDepartment = studentRepository.save(st);
        return convertToDto(savedDepartment, image);
//        Long departmentId = student.getDepartmentId();
//        Department department = departmentRepository.findById(departmentId).
//                orElseThrow(() -> new RuntimeException("Department Not found"));
//        Student st = new Student();
//        st.setEmail(student.getEmail());
//        st.setFirstName(student.getFirstName());
//        st.setLastName(student.getLastName());
//        st.setDepartment(department);
//        return studentRepository.save(st);
    }

    @Override
    public String deleteStudent(Long id) {
        Optional<Student> st = studentRepository.findById(id);
        if(st.isEmpty()) {
            return String.format("No such ID:%d found", id);
        }
        else {
            studentRepository.deleteById(id);
            return "Deleted Successfully";
        }


    }

    @Override
    public StudentDto updateById(Long id, StudentDto studentDto) {
        Student st = convertToEntityUpdate(studentDto);
        Student s = studentRepository.getReferenceById(id);
        s.setFirstName(studentDto.getFirstName());
        s.setLastName(studentDto.getLastName());
        s.setEmail(studentDto.getEmail());
        s.setDepartmentName(studentDto.getDepartmentName());
        Department dt = departmentRepository.findByDepartmentName(studentDto.getDepartmentName());
        if(dt!=null) {
            s.setDepartment(dt);
        }
        studentRepository.save(s);

        return studentDto;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student st = studentRepository.findById(id).orElse(null);
        return convertToDtoUpdate(st);
    }

    @Override
    public Student getStudentByIdImage(Long id) {
        Student st = studentRepository.findById(id).orElse(null);
        return st;
    }

    @Override
    public byte[] getImageByStudent(Long id) {
        return new byte[0];
    }


    public StudentDto convertToDto(Student student, MultipartFile image) throws IOException {
        if(student == null){
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDepartmentName(student.getDepartmentName());
//        studentDto.setDepartment(student.getDepartment());

        Department dept = student.getDepartment();
        if(dept != null) {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setDepartmentName(dept.getDepartmentName());
            departmentDto.setStrength(dept.getStrength());
            studentDto.setDepartmentName(departmentDto.getDepartmentName());
        }
        if(image !=null && !image.isEmpty()) {
            studentDto.setImageName(image.getOriginalFilename());
            studentDto.setImageType(image.getContentType());
            studentDto.setImageFile(image.getBytes());
        }


        return studentDto;
    }

    public Student convertToEntity(StudentDto studentDto, MultipartFile image) throws IOException {
        if(studentDto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());


        student.setDepartmentName(studentDto.getDepartmentName());
//        String dept = studentDto.getDepartmentName();
//        if(dept != null) {
//            Long i = departmentRepository.getByDepartmentId(dept);
//            student.setDepart_id(i);
//        }
        Department dept = departmentRepository.findByDepartmentName(studentDto.getDepartmentName());
        List<Student> st = dept.getDepartmentStudents();
        student.setDepartment(dept);
        if(st == null) {
            st = new ArrayList<>();
        }
        dept.setVacantSeats(dept.getStrength() - st.size() - 1);

        departmentRepository.save(dept);


        if(image !=null && !image.isEmpty()) {
            student.setImageName(image.getOriginalFilename());
            student.setImageType(image.getContentType());
            student.setImageFile(image.getBytes());
        }

//

        return student;
    }

    public Student convertToEntityUpdate(StudentDto studentDto)  {
        if(studentDto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());


        student.setDepartmentName(studentDto.getDepartmentName());
//        String dept = studentDto.getDepartmentName();
//        if(dept != null) {
//            Long i = departmentRepository.getByDepartmentId(dept);
//            student.setDepart_id(i);
//        }
        Department dept = departmentRepository.findByDepartmentName(studentDto.getDepartmentName());
        if(dept != null) {
            student.setDepartment(dept);
        }

//

        return student;
    }
    public StudentDto convertToDtoUpdate(Student student) {
        if(student == null){
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDepartmentName(student.getDepartmentName());
//        studentDto.setDepartment(student.getDepartment());

        Department dept = student.getDepartment();
        if(dept != null) {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setDepartmentName(dept.getDepartmentName());
            departmentDto.setStrength(dept.getStrength());
            studentDto.setDepartmentName(departmentDto.getDepartmentName());
        }

        return studentDto;
    }
}
