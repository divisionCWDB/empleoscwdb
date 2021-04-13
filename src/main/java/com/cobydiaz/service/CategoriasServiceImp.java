package com.cobydiaz.service;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cobydiaz.model.Categoria;

@Service
public class CategoriasServiceImp implements IntCategoriasService {

private List<Categoria> lista=null;
	
	public CategoriasServiceImp() {
		lista=new LinkedList<Categoria>();
			Categoria c1=new Categoria();
			c1.setId(1);
			c1.setNombre("Contaduria");
			c1.setDescripcion("Relacionado a cuentas");
			lista.add(c1);
			//
			Categoria c2=new Categoria();
			c2.setId(2);
			c2.setNombre("Arquitectura");
			c2.setDescripcion("Conocimiento en construccion");
			lista.add(c2);
			//
			Categoria c3=new Categoria();
			c3.setId(3);
			c3.setNombre("Programacion");
			c3.setDescripcion("Conocimientos en java avanzado");
			lista.add(c3);
			//
			Categoria c4=new Categoria();
			c4.setId(4);
			c4.setNombre("Mantenimiento");
			c4.setDescripcion("Conocimientos sobre mantenimiento a equipo de computo");
			lista.add(c4);
		}
	
	@Override
	public List<Categoria> obtenerTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		for(Categoria categoria : obtenerTodas()) {
			if(categoria.getId()==idCategoria) {
				return categoria;
			}
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		lista.add(categoria);
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
