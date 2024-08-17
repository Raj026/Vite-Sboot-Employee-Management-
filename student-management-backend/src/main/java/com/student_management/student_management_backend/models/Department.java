package com.student_management.student_management_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;



    private String departmentName;

    @Lob
    private byte[] departmentImage;
    private String imageType;
    private String imageName;

    private Integer strength;
    private Integer vacantSeats;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "department")
    @JsonBackReference
    private List<Student> departmentStudents;

    public Department(String departmentName, int strength, List<Student> departmentStudents) {
        this.departmentName = departmentName;
        this.strength = strength;
        this.departmentStudents = departmentStudents;

    }

    public Department(Long departmentId, String departmentName, byte[] departmentImage, String imageType, String imageName, Integer strength, Integer vacantSeats, List<Student> departmentStudents) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentImage = departmentImage;
        this.imageType = imageType;
        this.imageName = imageName;
        this.strength = strength;
        this.vacantSeats = vacantSeats;
        this.departmentStudents = departmentStudents;
    }

//    @PrePersist
//    @PreUpdate
//    private void getVacantSeats() {
//        if(departmentStudents!=null) {
//            this.vacantSeats = strength - departmentStudents.size();
//        }
//        else {
//            this.vacantSeats = strength;
//        }
//    }
}
