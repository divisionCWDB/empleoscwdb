package com.cobydiaz.controller;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobydiaz.model.Categoria;
import com.cobydiaz.model.Vacante;
import com.cobydiaz.service.IntCategoriasService;
import com.cobydiaz.service.IntVacantesService;
import com.cobydiaz.util.Utileria;

@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {

	@Autowired
	public IntCategoriasService serviceCategoria;
	
	@Autowired
	private IntVacantesService serviceVacante;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista=serviceVacante.obtenerTodas();
		for(Vacante v:lista)System.out.println(v);
		model.addAttribute("vacantes",lista);
		return "vacantes/listaVacantes";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante> lista = serviceVacante.buscarTodas(page);
	model.addAttribute("vacantes", lista);
	return "vacantes/listaVacantes";
	}


	
	//Vinculación de datos entre formulario y clase modelo Data Binding
	@PostMapping("/guardar")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart,Model model) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
				}
			model.addAttribute("categorias",serviceCategoria.obtenerTodas());
			return "vacantes/formVacante";
			}
		if (!multiPart.isEmpty()) {
			String ruta = "C:\\empleos\\images-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			vacante.setImagen(nombreImagen);
			}
			}

		Categoria c= serviceCategoria.buscarPorId(vacante.getCategoria().getId());
		vacante.setCategoria(c);
		//vacante.setId(serviceVacante.obtenerTodas().size()+1);
		serviceVacante.guardar(vacante);
		System.out.println("Vacante: "+vacante);
		System.out.println("Numero de vacantes: "+serviceVacante.obtenerTodas().size());
		//model.addAttribute("msg","Registro guardado con exito" );
		attributes.addFlashAttribute("msg", "Registro guardado con exito");
		return "redirect:/vacantes/indexPaginado";
	}
	
	/*@PostMapping("/guardar")
	public String guardar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,@RequestParam("estatus") String estatus ,
			@RequestParam("fecha") String fecha,@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,@RequestParam("detalles") String detalles){
		Vacante v=new Vacante();
		v.setNombre(nombre);
		v.setDescripcion(descripcion);
		//v.setFecha(fecha);
		v.setDestacado(destacado);
		v.setSalario(salario);
		//Guardar el objeto tabla o lista doblemente enlazada
		System.out.println(v);
		return "vacantes/listaVacantes";
	}*/
	
	@GetMapping("/crear")
	public String crear(Vacante vacante,Model model) {
		model.addAttribute("categorias",serviceCategoria.obtenerTodas());
		for(Categoria c: serviceCategoria.obtenerTodas()) {
			System.out.println(c);
		}
		return "vacantes/formVacante";
	}
	
	@GetMapping("/editar")
	public String editar(@RequestParam("id") int idVacante, Model model) {
		Vacante vacante= serviceVacante.buscarPorId(idVacante);
		model.addAttribute("categorias",serviceCategoria.obtenerTodas());
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
	}
	
	@GetMapping("/detalle/{id}")
	public String verDetalle(@PathVariable("id") int idVacante,Model model) {
		System.out.println("Id vacante: "+ serviceVacante.buscarPorId(idVacante));
		model.addAttribute("vacante",serviceVacante.buscarPorId(idVacante));
		return "vacantes/detalle";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idVacante,
			RedirectAttributes attributes) {
		//System.out.println("Id eliminada: "+ idVacante);
		//model.addAttribute("idVacante", idVacante);
		serviceVacante.eliminar(idVacante);
		attributes.addFlashAttribute("msg","Vacante eliminada con exito");
		return "redirect:/vacantes/indexPaginado";
	}
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }
      });
    }
}
