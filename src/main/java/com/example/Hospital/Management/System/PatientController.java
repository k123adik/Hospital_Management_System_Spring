package com.example.Hospital.Management.System;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    HashMap<Integer, Patient> patientDb = new HashMap<>();

    @PostMapping("/addPatientViaParameters")
    public String addPatient(@RequestParam("patientId")Integer patientId, @RequestParam("name")String name,
                             @RequestParam("age")Integer age, @RequestParam("disease")String disease){

        Patient patient = new Patient(patientId, name, disease, age);

        patientDb.put(patientId, patient);

        return "Patient Added Successfully";
    }
    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){
        int key = patient.getPatientId();

        patientDb.put(key, patient);

        return "Patient Added Successfully";

    }

    @GetMapping("/getinfoViaPathVariable/{patientId}")
    public Patient getPatientInfo(@PathVariable("patientId")Integer patientId){

        Patient patient = patientDb.get(patientId);

        return patient;
    }

    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId")Integer patientId){

        Patient patient = patientDb.get(patientId);

        return patient;
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){

        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDb.values()){
            patients.add(p);
        }
        return patients;
    }

//    @GetMapping("/getPatientByDisease&Age")
//    public List<Patient> getPatientByDiseaseAge(@RequestParam("disease")String disease, @RequestParam("age")Integer age){
//
//        List<Patient> patients = new ArrayList<>();
//
//        for(Patient p : patientDb.values()){
//
//            if(p.getDisease().equals(disease) && p.getAge() > age){
//                patients.add(p);
//            }
//        }
//        return patients;
//    }

    @GetMapping("/getPatients/{age}/{disease}")
    public List<Patient> getPatients(@PathVariable("age")Integer age, @PathVariable("disease")String disease){

        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDb.values()){
            if(p.getAge() > age && p.getDisease().equals(disease)){
                patients.add(p);
            }
        }
        return patients;
    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name")String name){

        for (Patient p : patientDb.values()){

            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    @GetMapping("/getPatientsListGreaterThanAge")
    public List<Patient> getPatientsListGreaterThanAge(@RequestParam("age")Integer age){

        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDb.values()){
            if(p.getAge() > age){
                patients.add(p);
            }
        }
        return patients;
    }

    @PutMapping("/updatepatientDetails")
    public String updatePatientDetails(@RequestBody Patient patient){

        int key = patient.getPatientId();

        if(patientDb.containsKey(key)){
            patientDb.put(key, patient);
            return "Details Updated Successfully";
        }
        return "Patient not Found";
    }

    @PutMapping("/updateDisease")
    public String updateDisease(@RequestParam("patientId")Integer patientId, @RequestParam("disease")String disease){

        if(patientDb.containsKey(patientId)){
            Patient patient = patientDb.get(patientId);

            patient.setDisease(disease);

            patientDb.put(patientId, patient);

            return "Disease Updated Successfully";
        }
        return "Patient not Found";
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId")Integer patientId){

        patientDb.remove(patientId);

        return "Patient Delete Successfully";
    }
}
