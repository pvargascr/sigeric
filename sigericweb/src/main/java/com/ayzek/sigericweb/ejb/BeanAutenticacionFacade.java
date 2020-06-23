/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.ejb;

import com.ayzek.sigericweb.model.Usuario;
import com.ayzek.sigericweb.bean.BeanAutenticacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BeanAutenticacionFacade extends AbstractFacade<BeanAutenticacion>{
    @PersistenceContext(unitName="com.ayzek_sigericweb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BeanAutenticacionFacade() {
        super(BeanAutenticacion.class);
    }

    public ArrayList<String> validateUser(String correo, String contrasena) {
        ArrayList<String> response = new ArrayList<>();
        response.add("0");//Autenticado
        response.add("");//Usuario
        response.add("");//Nombre Usuario
        response.add("");//Correo
        response.add("");//Tipo
        //Realizamos la consulta a la base de datos
        List<Usuario> usuarioList = em.createNamedQuery("Usuario.findByCorreoAndContrasena").setParameter("correo", correo).setParameter("contrasena", contrasena).getResultList();
        if (usuarioList.size() > 0) {
            if (usuarioList.get(0).getCorreo().equals(correo) && usuarioList.get(0).getContrasena().equals(contrasena)) {
                response.set(0,"1");
                response.set(1,usuarioList.get(0).getUsuario());
                response.set(2,usuarioList.get(0).getNombre()+ " " + usuarioList.get(0).getApellido1() + " " + usuarioList.get(0).getApellido2());
                response.set(3,correo);
                if (usuarioList.get(0).getTipo() == 0){
                    response.set(4,"usuario");
                }else if (usuarioList.get(0).getTipo() == 1){
                    response.set(4,"administrador");
                }
            }
        }
        return response;
    }
}
