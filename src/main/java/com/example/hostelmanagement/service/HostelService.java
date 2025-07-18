package com.example.hostelmanagement.service;

import com.example.hostelmanagement.entity.Hostel;
import com.example.hostelmanagement.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }

    public Hostel getHostelById(Long id) {
        return hostelRepository.findById(id).orElse(null);
    }

    public Hostel createHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public Hostel updateHostel(Long id, Hostel hostelDetails) {
        Hostel hostel = hostelRepository.findById(id).orElse(null);
        if (hostel != null) {
            hostel.setRoom(hostelDetails.getRoom());
            hostel.setBlock(hostelDetails.getBlock());
            hostel.setAvail(hostelDetails.getAvail());
            hostel.setMoney(hostelDetails.getMoney());
            hostel.setCollege(hostelDetails.getCollege());
            return hostelRepository.save(hostel);
        }
        return null;
    }

    public void deleteHostel(Long id) {
        hostelRepository.deleteById(id);
    }
}
