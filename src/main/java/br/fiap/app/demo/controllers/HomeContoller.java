package br.fiap.app.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeContoller {
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "Enviada pelo servidor");
		model.addAttribute("nome", "Thiago Souza xavier");
		return "hello";
	}
}
