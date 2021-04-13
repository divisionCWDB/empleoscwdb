package com.cobydiaz.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cobydiaz.model.Usuario;

@Service
public class UsuariosServiceImp implements IntUsuariosService {

	private List<Usuario> lista=null;
	public UsuariosServiceImp() {
		lista= new LinkedList<Usuario>();
		try {
			Usuario u1=new Usuario();
			u1.setId(1);
			u1.setNombre("Coby");
			u1.setUsername("Cobijas");
			u1.setPassword("123456");
			u1.setEmail("coby@gmail.com");
			u1.setEstatus(1);
			u1.setFechaRegistro(LocalDate.parse("07-01-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lista.add(u1);
			//
			Usuario u2=new Usuario();
			u2.setId(2);
			u2.setNombre("Jorge");
			u2.setUsername("GeorgeDB");
			u2.setPassword("123456");
			u2.setEmail("jorge@gmail.com");
			u2.setEstatus(0);
			u2.setFechaRegistro(LocalDate.parse("07-01-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lista.add(u2);
			//
			Usuario u3=new Usuario();
			u3.setId(3);
			u3.setNombre("Williams");
			u3.setUsername("WilliWonka");
			u3.setPassword("123456");
			u3.setEmail("wwwww@gmail.com");
			u3.setEstatus(1);
			u3.setFechaRegistro(LocalDate.parse("07-01-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lista.add(u3);
		}catch(DateTimeParseException e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	@Override
	public List<Usuario> obtenerTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		// Auto-generated method stub
		for(Usuario usuario: lista) {
			if(usuario.getId()==idUsuario) {
				return usuario;
			}
		}
		return null;
	}

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		lista.add(usuario);

	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		lista.remove(idUsuario);

	}

}
