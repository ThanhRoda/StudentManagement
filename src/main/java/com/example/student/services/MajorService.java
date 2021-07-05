package com.example.student.services;

import com.example.student.exeption.UserExitedExeption;
import com.example.student.exeption.UserNotFoundExeption;
import com.example.student.models.Major;
import com.example.student.repos.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {
    private final MajorRepository majorRepository;

    @Autowired
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    public Major getMajorById(Long id) {
        Optional<Major> stu = majorRepository.findById(id);

        if (stu.isPresent()) {
            return stu.get();
        } else
            throw new UserNotFoundExeption("No Found with id = " + id);

    }

    public Major saveMajor(Major stu) {
        Optional<Major> major = majorRepository.findMajorByName(stu.getName());
        if (!major.isPresent())
            return majorRepository.save(stu);
        else
            throw new UserExitedExeption("Major already exits");

    }


    public Major updateMajor(Major major) {
        Major newMajor = majorRepository.findById(major.getId()).orElseThrow(()
                -> new UserNotFoundExeption("Can not find major"));
        return this.majorRepository.save(major);

    }

    public void deleteMajorById(Long id) {
        Major major = majorRepository.findById(id).orElseThrow(()
                -> new UserNotFoundExeption("Not Found with id = " + id));
        majorRepository.deleteById(id);
    }
}
