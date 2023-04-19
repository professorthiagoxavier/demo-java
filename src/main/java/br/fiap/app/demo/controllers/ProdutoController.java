package br.fiap.app.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.fiap.app.demo.models.Categoria;
import br.fiap.app.demo.models.Fonecedor;
import br.fiap.app.demo.models.Produto;
import br.fiap.app.demo.repositories.CategoriaRepository;
import br.fiap.app.demo.repositories.FornecedorRepository;
import br.fiap.app.demo.repositories.ProdutoRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired // injeção de dependência
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@GetMapping("/listar")
	public ModelAndView Get() {
		ModelAndView model = new ModelAndView("produto/index");
		/*
		 * List<Produto> listarProdutos = new ArrayList<Produto>(); Produto produto =
		 * new Produto(); listarProdutos.add(produto.create(1, "Macarrão"));
		 * listarProdutos.add(produto.create(2, "Molho"));
		 */
		List<Produto> produtos = produtoRepository.findAll();
		model.addObject("produtos", produtos);
		return model;
	}

	@GetMapping("/create")
	public ModelAndView Create(Model model) {
		ModelAndView modelAndView = new ModelAndView("produto/create");
		List<Categoria> listCategoria = categoriaRepository.findAll();
		List<Fonecedor> listFornecedor = fornecedorRepository.findAll();
		modelAndView.addObject("fornecedores", listFornecedor);
		modelAndView.addObject("categorias", listCategoria);
		return modelAndView;
	}

	@PostMapping("/create")
	public ModelAndView Create(@Valid @ModelAttribute("produto") Produto produto) {
		// Saving produto
		produtoRepository.save(produto);
		return new ModelAndView("redirect:/produto/listar");
		// return "redirect:/produto";
	}
	
	@PostMapping("/create-from")
	public ResponseEntity<Produto>  CreateFrom(@Valid @RequestBody Produto produto) {
		produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}

	@GetMapping("/categoria/{id}")
	@ResponseBody // Convert to Json
	public List<Produto> GetCategoria(@PathVariable("id") Integer id) {
		List<Produto> produto = produtoRepository.findByIdCategoria(id);
		return produto; // Retun data forma Json
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		Optional<Produto> optional = this.produtoRepository.findById(id);
		Produto produto = optional.get();
		ModelAndView modelView = new ModelAndView("produto/edit");
		List<Categoria> listCategoria = categoriaRepository.findAll();
		List<Fonecedor> listFornecedor = fornecedorRepository.findAll();
		modelView.addObject("fornecedores", listFornecedor);
		modelView.addObject("categorias", listCategoria);
		modelView.addObject("produto", produto);
		return modelView;
	}

	@PostMapping("/update/{id}")
	public ModelAndView update(@PathVariable Integer id, @ModelAttribute("produto") Produto produto) {
		Optional<Produto> optional = this.produtoRepository.findById(id);

		if (optional.isPresent()) {
			produto.setId(id);
			produtoRepository.save(produto);
		}

		return new ModelAndView("redirect:/produto/listar");
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		produtoRepository.deleteById(id);
		return mv;
	}
	

}
