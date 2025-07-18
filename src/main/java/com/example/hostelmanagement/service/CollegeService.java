package com.example.hostelmanagement.service;

import com.example.hostelmanagement.entity.College;
import com.example.hostelmanagement.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id).orElse(null);
    }

    public College createCollege(College college) {
        return collegeRepository.save(college);
    }

    public College updateCollege(Long id, College collegeDetails) {
        College college = collegeRepository.findById(id).orElse(null);
        if (college != null) {
            college.setColgname(collegeDetails.getColgname());
            return collegeRepository.save(college);
        }
        return null;
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

    // Your needed service method: find college name by colgid
    public String getCollegeNameByColgid(Long colgid) {
        College college = collegeRepository.findById(colgid).orElse(null);
        return college != null ? college.getColgname() : null;
    }
}
