package com.example.Hospital.Management.System;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NurseService {

    NurseRepository nurseRepository = new NurseRepository();

    public String addNurse(Nurse nurse){

        if(nurse.getNurseId() < 0){
            return "Enter Valid NurseId";
        }

        if(nurse.getName().equals(null)){
            return "Name Should not be null";
        }

        String ans = nurseRepository.addNurse(nurse);

        return ans;
    }

    public List<Nurse> getList(int age){

        //calling all the nurses from database

        List<Nurse> nurses = nurseRepository.getAllNurses();

        //writing logic

        List<Nurse> finalList = new ArrayList<>();

        for(Nurse nurse : nurses){

            if(nurse.getAge() > age){
               finalList.add(nurse);
            }
        }
        return finalList;
    }

    public List<Nurse> getNursesWithQualification(String qualification){

        List<Nurse> nurseList = nurseRepository.getAllNurses();

        List<Nurse> nurses = new ArrayList<>();

        for(Nurse nurse : nurseList){

            if(nurse.getQualification().equals(qualification)){
                nurses.add(nurse);
            }
        }
        return nurses;
    }
}
