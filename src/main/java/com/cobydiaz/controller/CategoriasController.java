package com.cobydiaz.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobydiaz.model.Categoria;
import com.cobydiaz.service.IntCategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	public IntCategoriasService serviceCategoria;
	
	//@GetMapping(value = "/indexPaginate")
	@RequestMapping(value="/indexPaginado",method=RequestMethod.GET)
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Categoria> lista = serviceCategoria.buscarTodas(page);
	model.addAttribute("categorias", lista);
	return "categorias/listaCategorias";
	}

	@RequestMapping(value="/eliminar",method=RequestMethod.GET)
	public String eliminar(@RequestParam("id")int idCategoria,
			RedirectAttributes attributes) {
		serviceCategoria.eliminar(idCategoria);
		attributes.addFlashAttribute("msg","Categoria eliminada");
		return "redirect:/categorias/index";
	}
	
	
	@RequestMapping(value="/editar",method=RequestMethod.GET)
    public String editar(@RequestParam("id") int idCategoria,
            Model model) {
        Categoria  categoria =
                serviceCategoria.buscarPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategorias";
    }
	
	
//Anotacion anterior para solicitar peticiones
	//@GetMapping("/index")
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista=serviceCategoria.obtenerTodas();
		for(Categoria c: lista) {
			System.out.println(c);
		}
		model.addAttribute("categorias", lista);
		return "categorias/listaCategorias";
	}
	
	@RequestMapping(value="/crear",method=RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategorias";
	}
	
	@RequestMapping(value="/guardar",method=RequestMethod.POST)
	public String guardar(Categoria categoria, RedirectAttributes attributes) {
	//categoria.setId(serviceCategoria.obtenerTodas().size()+1);
	serviceCategoria.guardar(categoria);
	attributes.addFlashAttribute("msg","Se ha guardado exitosamente");
	return "redirect:/categorias/indexPaginado";
		
	}
	
		
}
