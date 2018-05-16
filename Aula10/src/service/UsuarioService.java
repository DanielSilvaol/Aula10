package service;

import pacote.Usuario;
import Dao.UsuarioDao;

public class UsuarioService {
	
	public boolean validar(Usuario usuario){
		UsuarioDao dao = new UsuarioDao();
		return dao.validar(usuario);
	}
}
