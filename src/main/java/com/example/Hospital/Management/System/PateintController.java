package com.example.Hospital.Management.System;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class PateintController {

    HashMap<Integer,Patient> PatientDb = new HashMap();
    @PostMapping("/addPatientViaparameters")
    public  String addPatient(@RequestParam ("patientId") Integer patientId , @RequestParam("name") String name,
                              @RequestParam("disease") String disease, @RequestParam ("age") Integer age){
        Patient patient = new Patient(patientId,name,disease,age);
        PatientDb.put(patientId,patient);
        return "Patient Added Succesfully By @RequestParam.";

    }
    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient ){
        int key = patient.getPatientId();
        PatientDb.put(key , patient);
        return "patient Added Succesfully By @RequestBody";
    }
    @GetMapping("/getPatietInformation")
    public Patient getPatientInfo(@RequestParam ("patientId") Integer patientId){
        Patient patient = PatientDb.get(patientId);
        return patient;
    }
    @GetMapping("/getAllPatients")
  public List<Patient> getAllPatients (){
        List <Patient> Patients = new ArrayList<>();
        for (Patient p :PatientDb.values()){
            Patients.add(p);
        }
        return Patients;
    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name") String name){
        for (Patient p: PatientDb.values()){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    @GetMapping("/getPatientsGreaterThanAge")
    public List<Patient> getPatient(@RequestParam("age") Integer age){
        List <Patient> Patients = new ArrayList<>();
        for (Patient p:PatientDb.values()){
            if (p.getAge()>age){
                Patients.add(p);
            }
        }
        return Patients;
    }

}
