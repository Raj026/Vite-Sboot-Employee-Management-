package com.student_management.student_management_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDto {
    private Integer strength;
    private Integer vacantSeats;
    private String departmentName;
    private byte[] departmentImage;
    private String imageType;
    private String imageName;

    public DepartmentDto(String departmentName, Integer strength, Integer vacantSeats) {
        this.departmentName = departmentName;
        this.strength = strength;
        this.vacantSeats = vacantSeats;
    }

    public DepartmentDto() {}
}
