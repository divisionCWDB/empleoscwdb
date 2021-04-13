package com.cobydiaz.service;
import java.util.List;
import com.cobydiaz.model.Usuario;

public interface IntUsuariosService {

	public List<Usuario> obtenerTodas();
	public Usuario buscarPorId(Integer idUsuario);
	public void guardar(Usuario usuario);
	public void eliminar(Integer idUsuario);
}
