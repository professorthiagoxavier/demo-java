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
import br.fiap.app.demo.models.Categoria;
import br.fiap.app.demo.models.Produto;
import br.fiap.app.demo.repositories.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/listar")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("categoria/index");
		List<Categoria> listcategoria = categoriaRepository.findAll();
		model.addObject("categorias", listcategoria);
		return model;
	}

	@GetMapping("/create")
	public String Create(Model model) {
		model.addAttribute("categoria", new Produto());
		return "categoria/create";
	}

	@PostMapping("/create")
	public ModelAndView Create(@ModelAttribute("categoria") Categoria categoria) {
		// Saving produto
		categoriaRepository.save(categoria);

		return new ModelAndView("redirect:/categoria/listar");
		// return "redirect:/produto";
	}
}
