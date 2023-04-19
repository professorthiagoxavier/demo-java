package br.fiap.app.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.fiap.app.demo.models.Fonecedor;
import br.fiap.app.demo.models.Produto;
import br.fiap.app.demo.repositories.FornecedorRepository;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@GetMapping("/listar")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("fornecedor/index");
		List<Fonecedor> listFornecedor = fornecedorRepository.findAll();
		model.addObject("fornecedores", listFornecedor);
		return model;
	}

	@GetMapping("/create")
	public String Create(Model model) {
		model.addAttribute("fornecedor", new Produto());
		return "fornecedor/create";
	}

	@PostMapping("/create")
	public ModelAndView Create(@ModelAttribute("produto") Fonecedor produto) {
		// Saving produto
		fornecedorRepository.save(produto);

		return new ModelAndView("redirect:/fornecedor/listar");
		// return "redirect:/produto";
	}
}
