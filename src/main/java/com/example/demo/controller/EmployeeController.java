package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	//list all employees
	@GetMapping("/")
	public String viewHome(Model theModel) {
		theModel.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/AddEmployee")
	public String AddEmployee(Model theModel) {
		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);
		return "AddEmployee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/ShowFormforUpdate/{id}")
	public String ShowFormforUpdate(@PathVariable (value = "id") Long id, Model theModel) {
		Employee employee = employeeService.getEmployeeById(id);
		theModel.addAttribute("employee", employee);
		return "updateForm";
	}
	
	@GetMapping("/DeleteEmployee/{id}")
	public String DeleteEmployee(@PathVariable (value = "id") Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
}
