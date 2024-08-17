package com.student_management.student_management_backend.controllers;

import com.student_management.student_management_backend.DTO.DepartmentAdminDto;
import com.student_management.student_management_backend.DTO.DepartmentDto;
import com.student_management.student_management_backend.models.Department;
import com.student_management.student_management_backend.service.DepartmentServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;
    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        if(!departments.isEmpty()) {
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping()
    public ResponseEntity<DepartmentDto> addDepartment(@RequestPart("department") DepartmentDto departmentDto, @RequestPart("image")MultipartFile image) throws IOException {
        if(departmentDto != null) {
            DepartmentDto dept = departmentService.addDepartment(departmentDto, image);
            return new ResponseEntity<>(dept, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentAdminDto> viewDepartment(@PathVariable("id") Long id){
        return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImageByDepartment(@PathVariable("id") Long id) {
        Department dt = departmentService.getImageByDepartment(id);
        if(dt != null) {
            String imageType = dt.getImageType();
            MediaType mediaType = MediaType.parseMediaType(imageType);
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(dt.getDepartmentImage());
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }



}
