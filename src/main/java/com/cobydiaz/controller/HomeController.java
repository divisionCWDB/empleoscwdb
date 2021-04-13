package com.cobydiaz.controller;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cobydiaz.model.Vacante;
import com.cobydiaz.service.IntVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private IntVacantesService serviceVacantes;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		//Devuelve una vista(archivo html)
		List<Vacante> vacantes=serviceVacantes.obtenerTodas();
		model.addAttribute("vacantes", vacantes);
		return "home";
	}
	
	@GetMapping("/mensaje")
	public String mensaje(Model model) {
		//Formatear una fecha
		//LocalDate fecha=LocalDate.now();
		//Personalizar fecha
		LocalDate fecha=null;
		try{
			fecha=LocalDate.parse("20-07-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			System.out.println("Fecha: "+fecha);
		}catch(DateTimeException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
		model.addAttribute("msg","Bienvenidos a mi app: Empleos app");
		model.addAttribute("fecha",java.sql.Date.valueOf(fecha));
		return "mensaje";
	}
	
	//Metodo para manipular datos
	@GetMapping("/vacante")
	public String vacante(Model model) {
		Integer id=1000;
		String nombre="Programador de paginas web";
		LocalDate fecha= LocalDate.now();
		Double salario=12000.00;
		Boolean vigente=true;
		model.addAttribute("id",id);
		model.addAttribute("nombre",nombre);
		model.addAttribute("fecha",fecha);
		model.addAttribute("salario",salario);
		model.addAttribute("vigente",vigente);
		return "vacante";
	}
	
	//Coleccion de datos
	@GetMapping("/lista")
	public String lista(Model model) {
		List<String> vacantes=new LinkedList<String>();
		vacantes.add("Contador público");
		vacantes.add("Programador web");
		vacantes.add("Diseñador web");
		vacantes.add("Tecnico de mantenimiento");
		vacantes.add("Arquitecto");
		//Imprimir arreglo
		for(String elemento:vacantes) {
			System.out.println(elemento);
		}
		model.addAttribute("vacantes",vacantes);
		return "vacante";
	}
	
	//Metodo que devuelve objeto de tipo vacante
	@GetMapping("/detalles")
	public String detalles(Model model) {
		Vacante v=new Vacante();
		System.out.println(v);
		v.setId(100);
		v.setNombre("Programador de app web");
		v.setDescripcion("Desarrollara aplicaciones para I4.0");
		v.setFecha(LocalDate.now());
		v.setSalario(1200.00);
		System.out.println(v);
		model.addAttribute("vacante", v);
		return "detalles";
	}
	
	@GetMapping("/tabla")
	public String mostrarVacantes(Model model) {
		List<Vacante> vacantes=serviceVacantes.obtenerTodas();
		for(Vacante elemento:vacantes) {
			System.out.println(elemento.getId());
			System.out.println(elemento.getNombre());
			System.out.println(elemento.getDescripcion());
			System.out.println(elemento.getFecha());
			System.out.println(elemento.getSalario());
			System.out.println(elemento.getDestacado());
		}
		model.addAttribute("vacantes", vacantes);
		return "tabla";
	}
		
}
