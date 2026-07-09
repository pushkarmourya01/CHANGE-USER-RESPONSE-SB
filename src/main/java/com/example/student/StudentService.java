package com.example.student;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public StudentResponseDTO saveStudent(StudentDto studentDto) {
        String name = studentDto.getName();
        String email = studentDto.getEmail();

        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setName(name);
        responseDTO.setMessage("Student saved successfully with email: " + email);

        return responseDTO;
    }
}
