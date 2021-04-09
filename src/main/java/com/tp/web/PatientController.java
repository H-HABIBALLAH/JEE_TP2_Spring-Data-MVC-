package com.tp.web;

import com.tp.dao.PatientRepository;
import com.tp.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping(path="/patients")
    private String patientsList(Model model,
                                @RequestParam(name="page", defaultValue="0") int page,
                                @RequestParam(name="size", defaultValue="10") int size,
                                @RequestParam(name="keyword", defaultValue="") String kw){
        Page<Patient> patientsPage = patientRepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("patients",patientsPage.getContent());
        model.addAttribute("pages",new int[patientsPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patientsList";
    }

    @GetMapping(path="/deletePatient")
    private String delete(long id){
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
