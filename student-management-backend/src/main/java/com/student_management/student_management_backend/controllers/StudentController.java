package com.student_management.student_management_backend.controllers;

import com.student_management.student_management_backend.DTO.StudentDto;
import com.student_management.student_management_backend.models.Student;
import com.student_management.student_management_backend.service.StudentService;
import com.student_management.student_management_backend.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();

        if(!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @PostMapping()
//    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto student) {
//        if(student != null) {
//            return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @PostMapping()
    public ResponseEntity<StudentDto> addStudent(@RequestPart("student") StudentDto studentDto, @RequestParam("image")MultipartFile image) throws IOException {
        if(studentDto != null) {
            return new ResponseEntity<>(studentService.addStudent(studentDto, image), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        return new ResponseEntity<StudentDto>(studentService.updateById(id, studentDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<StudentDto>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getStudentImage(@PathVariable Long id) {

        Student st = studentService.getStudentByIdImage(id);
        if (st.getImageFile() != null) {
            String imageType = st.getImageType();
            MediaType mediaType = MediaType.parseMediaType(imageType);
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(st.getImageFile());

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
