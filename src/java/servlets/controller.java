package servlets;

import beans.beanLogin;
import beans.beanMembre;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controller", urlPatterns = {"/controller"})

public class controller extends HttpServlet {

    private Cookie getCookie(Cookie[] c, String name) {
        if (c != null) {
            for (Cookie cc : c) {
                if (cc.getName().equals(name)) {
                    return cc;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "/WEB-INF/jspGlobale.jsp";
        HttpSession session = request.getSession();
        ServletContext app = this.getServletContext();

        //permet d'envoyer d'affecter l'adresse "/WEB-INF/jspMenu.jsp" l'objet 
        //menu declaré dans la jspGlobale en tant que ${menu}
//-------------------------Traitement sur le menu-------------------------------
        request.setAttribute("menu", "/WEB-INF/jspMenu.jsp");

        beanMembre m = (beanMembre) session.getAttribute("sessionTest");

        if (m != null) {
            request.setAttribute("menu", "/WEB-INF/jspMenu2.jsp");
        }
        //permet d'envoyer d'affecter l'adresse "/WEB-INF/jspCorps.jsp" l'objet 
        //corps declaré dans la jspGlobale en tant que ${corps}
//---------------------------Traitement sur le corps----------------------------
        request.setAttribute("corps", "/WEB-INF/jspCorps.jsp");
        //Set l'url de corps en fonction de l'element selectionné
        if ("Catalogue".equals(request.getParameter("corps"))) {
            request.setAttribute("corps", "/WEB-INF/jspCatalogue.jsp");
        }
        if ("Panier".equals(request.getParameter("corps"))) {
            request.setAttribute("corps", "/WEB-INF/jspPanier.jsp");
        }
        if ("Connexion".equals(request.getParameter("corps"))) {
            request.setAttribute("corps", "/WEB-INF/jspConnexion.jsp");
        }
        if ("Inscription".equals(request.getParameter("corps"))) {
            request.setAttribute("corps", "/WEB-INF/jspInscription.jsp");
        }

        if ("Compte".equals(request.getParameter("corps"))) {
//            request.setAttribute("selectPart", null);
//            request.setAttribute("selectPro", null);
//            
            if (m.getType()==1) {
                request.setAttribute("selectPart", "selected");
             }
            else {
                request.setAttribute("selectPro", "selected");
            }
            
            request.setAttribute("corps", "/WEB-INF/jspCompte.jsp");
            
            //On prend les attribut qui sont contenu dans l'objet m et on les 
            //affectent a membre
            request.setAttribute("membre", m);

        }

        if ("SeDeco".equals(request.getParameter("corps"))) {
            session.setAttribute("sessionTest", null);
            request.setAttribute("menu", "/WEB-INF/jspMenu.jsp");
        }

        // si le bouton ok est cliqué, verifie si le nom et le mdp sont admin et root.
        // si c'est le cas on renvoie sur le menu2
        Cookie c = getCookie(request.getCookies(), "try");
        if (c == null) {
            c = new Cookie("try", "");
        }
        if (request.getParameter("doIt") != null) {

            beanLogin Login = (beanLogin) app.getAttribute("login");
            if (Login == null) {
                Login = new beanLogin();
                app.setAttribute("login", Login);
            }
            int id = Login.check(request.getParameter("nom"), request.getParameter("mdp"));
            System.out.println("id =" + id);

            if (id > 0) {
                m = new beanMembre();
                m.createMembre(id);
                session.setAttribute("sessionTest", m);
                request.setAttribute("menu", "/WEB-INF/jspMenu2.jsp");
                c.setValue("");
                response.addCookie(c);
            } else {
                request.setAttribute("corps", "/WEB-INF/jspConnexion.jsp");
                c.setValue(c.getValue() + "*");
                response.addCookie(c);
                request.setAttribute("msg", "Erreur d'authentification."
                        + " Verifier votre nom d'utilisateur/Mot de passe. "
                        + "Il vous reste " + (3 - c.getValue().length()) + " essai(s)");
                if (c.getValue().length() >= 3) {
                    request.setAttribute("corps", "/WEB-INF/jspFatalError.jsp");
                    c.setMaxAge(60 * 2);
                    response.addCookie(c);
                    if (("Connexion").equals(request.getParameter("corps"))) {
                        request.setAttribute("corps", "/WEB-INF/jspFatalError.jsp");
                    }
                }

            }
        }
        if (c.getValue().length() >= 3) {
            request.setAttribute("corps", "/WEB-INF/jspFatalError.jsp");
            if (("Connexion").equals(request.getParameter("corps"))) {
                request.setAttribute("corps", "/WEB-INF/jspFatalError.jsp");
            }
        }

        //Si le boutton supprimer est cliqué
        if (request.getParameter("suppr") != null) {
            //On execute la methode supprMembre, on reaffiche le menu 1 et on 
            //set la session a null
            m.SupprMembre();
            request.setAttribute("menu", "/WEB-INF/jspMenu.jsp");
            session.setAttribute("sessionTest", null);
        }

        //Si le button modifier est cliqué
        if (request.getParameter("modif") != null) {
            m.setNom(request.getParameter("nomCpt"));
            m.setPrenom(request.getParameter("prenomCpt"));
            if ("Part".equals(request.getParameter("typeCpt"))) {
//                    request.setAttribute("typeCpt", 1);
                m.setType(1);
//                    request.getAttribute("typeCpt").equals("1");
            }
            if ("Pro".equals(request.getParameter("typeCpt"))) {
//                    request.setAttribute("typeCpt", 2);
                m.setType(2);
//                    request.getAttribute("typeCpt").equals("2");
            }
            System.out.println(m.getType());
            m.setDateNaissance(request.getParameter("dateNaissCpt"));
            m.setMail(request.getParameter("mail"));
            m.setTel(request.getParameter("tel"));
            m.setPort(request.getParameter("port"));
            m.setMdp(request.getParameter("mdp"));

            m.UpdateMembre();
        }

        request.getRequestDispatcher(url).include(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
