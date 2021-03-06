package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pacote.Pais;
import service.PaisService;

public class ExcluirPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String Populacao = request.getParameter("populacao");
		String Area = request.getParameter("area");

		
		long pPopulacao = -1;
		double pArea = -1;
		int id = -1;
		try {
			id = Integer.parseInt(pId);
			pPopulacao = Long.valueOf(Populacao);
			pArea = Double.valueOf(Area);
		} catch (NumberFormatException e) {

		}

		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);
		PaisService cs = new PaisService();
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		cs.excluir(pais.getId());
		@SuppressWarnings("unchecked")
		ArrayList<Pais> lista =(ArrayList<Pais>) session.getAttribute("lista");
		
		lista.remove(busca(pais, lista));
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListarPais.jsp");
		view.forward(request, response);

	}
	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == pais.getId()) {
				return i;
			}
		}
		return -1;
	}

}
