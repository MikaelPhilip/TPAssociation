package org.licence.pro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Action
 * Servlet controleur r�cuperation de donn�es et cr�ation de param�tre
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		/*Gestion des session*/
		//Si on vient de remplir le formulaire de connection
		if ((request.getParameter("login")!=null) && (request.getParameter("mdp") != null)) {
			if ((!request.getParameter("login").equals("")) && (!request.getParameter("mdp").equals(""))) {
			//TODO:A partir du jpa ici il faudra v�rifi� si la personne existe
			//On enregistre en session un attribut "login" cela signifie que la personne est conn�ect�
			request.getSession(true).setAttribute("login", new String(request.getParameter("login")));
			}
		}
		System.out.println(request.getParameter("logout"));
		if(request.getParameter("logout")!=null){
			System.out.println("D�connexion");
			request.getSession().invalidate();
		}
		
		/*Gestion des pages*/
		//Suivant le chemin retourn� par le template on va cr�e un attribut page que le template utilisera pour savoir quoi choisir
		//les href du menu seront comme cela /action et on les r�cuperera avec les methode get path...
		System.out.println(request.getPathInfo());
		if(request.getPathInfo() != null){
			request.setAttribute("Page", request.getPathInfo());
		}
		
		
		/*Gestion des donn�es*/
		//TODO: suivant la page vis� on affichera certaine donn�e issue de l'appelle des m�thode de la JPA 
		
		/*Gestion des actions*/
		//L'ensemble des actions particuli�res (cr�ation porfile,ajout article seront trait� ici).
		if(request.getParameter("Create")!=null){
			System.out.println("Cr�ation compte");
			//TODO : JPA ajout d'un compte dans le bdd
		}
		
		
		
		getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}
}
