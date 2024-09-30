package com.codegym.minitestweek2.controller;

import com.codegym.minitestweek2.model.Classes;
import com.codegym.minitestweek2.model.Student;
import com.codegym.minitestweek2.service.IClassService;
import com.codegym.minitestweek2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassService classService;

    @ModelAttribute("classes")
    public Iterable<Classes> listClasses(){
        return classService.findAll();
    }
    @GetMapping
    public ModelAndView listStudent(Pageable pageable){
        Page<Student> students=studentService.findAll(pageable);
        ModelAndView modelAndView=new ModelAndView("/student/list");
        modelAndView.addObject("students",students);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView listStudentsSearch(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Student> students;
        if(search.isPresent()){
            students=studentService.findAllByFirstNameContaining(pageable,search.get());
        }else{
            students=studentService.findAll(pageable);
        }
        ModelAndView modelAndView=new ModelAndView("/student/list");
        modelAndView.addObject("students",students);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView=new ModelAndView("/student/create");
        modelAndView.addObject("student",new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("student") Student student,
                         RedirectAttributes redirectAttributes) {
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "New student created successfully");
        return "redirect:/students";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ModelAndView("/error_404");
        }
        ModelAndView modelAndView = new ModelAndView("/student/update");
        modelAndView.addObject("student", student.get());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("student") Student student,
                         RedirectAttributes redirectAttributes) {
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully");
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        studentService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        return "redirect:/students";
    }
}

