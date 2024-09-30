package com.codegym.minitestweek2.controller;

import com.codegym.minitestweek2.model.Classes;
import com.codegym.minitestweek2.model.Student;
import com.codegym.minitestweek2.service.IClassService;
import com.codegym.minitestweek2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public ModelAndView listClass(){
        ModelAndView modelAndView = new ModelAndView("/class/list");
        Iterable<Classes> classes = classService.findAll();
        modelAndView.addObject("classes", classes);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("class", new Classes());

        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("class") Classes classes,
                         RedirectAttributes redirectAttributes){
        classService.save(classes);
        redirectAttributes.addFlashAttribute("message", "Create class successfully");
        return "redirect:/classes";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id){
        Optional<Classes> classes = classService.findById(id);
        if(classes.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/class/update");
            modelAndView.addObject("class", classes.get());
            return modelAndView;
        }else{
            return new ModelAndView("/error_404");
        }


    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                       @ModelAttribute("class") Classes classes,
                       RedirectAttributes redirectAttributes){
        classService.save(classes);
        redirectAttributes.addFlashAttribute("message", "Update class successfully");
        return "redirect:/classes";
    }
    @GetMapping("/view-class/{id}")
    public ModelAndView viewClass(@PathVariable("id") Long id){
        Optional<Classes> classOptional = classService.findById(id);
        if(!classOptional.isPresent()){
            return new ModelAndView("/error_404");
        }
        Iterable< Student> students = studentService.findAllByClass(classOptional.get());
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }


}
