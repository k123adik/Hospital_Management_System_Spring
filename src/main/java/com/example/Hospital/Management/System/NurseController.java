package com.example.Hospital.Management.System;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    NurseService nurseService = new NurseService();

    @PostMapping("/add")
    public String addNurse(@RequestBody Nurse nurse){

        String ans = nurseService.addNurse(nurse);

        return ans;
    }

    @GetMapping("/getListByAge")
    public List<Nurse> getNursesGreaterThanAge(@RequestParam("age")Integer age){
       List<Nurse> nurseList = nurseService.getList(age);
       return nurseList;
    }

    @GetMapping("/getListByQualification")
    public List<Nurse> getNursesByQualification(@RequestParam("qualification")String qualification){

        List<Nurse> nurseList = nurseService.getNursesWithQualification(qualification);
        return nurseList;
    }
}
