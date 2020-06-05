package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.service.AdminService;
import co.edu.icesi.service.AdminServiceImp;

//@Controller
public class TsscAdminController{
	AdminServiceImp adminService;
	@Autowired
	public TsscAdminController(AdminServiceImp adminService) {
		this.adminService =adminService;
	}
	/*
	@GetMapping("/")
	public String loginAdmin(Model model) {
		model.addAttribute("admin",adminService.findAll());
		return "";
	}*/
}
