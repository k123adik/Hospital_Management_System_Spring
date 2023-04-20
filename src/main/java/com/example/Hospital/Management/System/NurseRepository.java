package com.example.Hospital.Management.System;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class NurseRepository {

    HashMap<Integer, Nurse> nurseDb = new HashMap<>();

    public String addNurse(Nurse nurse){

        int key = nurse.getNurseId();
        nurseDb.put(key, nurse);
        return "Nurse Added Successfully";

    }
    public List<Nurse> getAllNurses(){
        return nurseDb.values().stream().toList();
    }

}
