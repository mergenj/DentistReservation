package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dao.DentistVisitRepository;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DentistAppController {

    @Autowired
    private DentistVisitRepository dentistVisitRepository;



//    @GetMapping("/signup")
////    public String showSignUpForm(DentistVisitEntity dentistVisitEntity) {
////        return "form";
////    }

    @GetMapping("/addvisit")
    public String showAddVisit(DentistVisitEntity dentistVisitEntity, Model model) {
        model.addAttribute("visits", dentistVisitRepository.findAll());
        return "index";
    }

//    @PostMapping("/addvisit")
//    public String addVisit(@Valid DentistVisitEntity dentistVisitEntity, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "form";
//        }
//
//        dentistVisitRepository.save(dentistVisitEntity);
//        model.addAttribute("visits", dentistVisitRepository.findAll());
//        return "index";
//    }

    @PostMapping("/addvisit")
    public String addVisit(@Valid DentistVisitEntity dentistVisitEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("visits", dentistVisitRepository.findAll());
            return "index";
        }

        dentistVisitRepository.save(dentistVisitEntity);
        model.addAttribute("visits", dentistVisitRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        DentistVisitEntity visit = dentistVisitRepository.findOne(id);

        model.addAttribute("visit", visit);
        return "update-visit";
    }

    @PostMapping("/update/{id}")
    public String updateVisit(@PathVariable("id") long id, @Valid DentistVisitEntity dentistVisitEntity,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            dentistVisitEntity.setId(id);
            return "update-visit";
        }

        dentistVisitRepository.save(dentistVisitEntity);
        model.addAttribute("visits", dentistVisitRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteVisit(@PathVariable("id") long id, Model model, DentistVisitEntity dentistVisitEntity) {
        DentistVisitEntity visit = dentistVisitRepository.findOne(id);

        dentistVisitRepository.delete(visit);
        model.addAttribute("visits", dentistVisitRepository.findAll());
        return "index";
    }
}
