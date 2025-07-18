package com.example.hostelmanagement.controller;

import com.example.hostelmanagement.entity.Hostel;
import com.example.hostelmanagement.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hostels")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @GetMapping
    public List<Hostel> getAllHostels() {
        return hostelService.getAllHostels();
    }

    @GetMapping("/{id}")
    public Hostel getHostelById(@PathVariable Long id) {
        return hostelService.getHostelById(id);
    }

    @PostMapping
    public Hostel createHostel(@RequestBody Hostel hostel) {
        return hostelService.createHostel(hostel);
    }

    @PutMapping("/{id}")
    public Hostel updateHostel(@PathVariable Long id, @RequestBody Hostel hostelDetails) {
        return hostelService.updateHostel(id, hostelDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteHostel(@PathVariable Long id) {
        hostelService.deleteHostel(id);
    }
}
