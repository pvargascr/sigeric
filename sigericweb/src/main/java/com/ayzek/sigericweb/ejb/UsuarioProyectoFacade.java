/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.ejb;

import com.ayzek.sigericweb.model.UsuarioProyecto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Varsandrey
 */
@Stateless
public class UsuarioProyectoFacade extends AbstractFacade<UsuarioProyecto> {

    @PersistenceContext(unitName = "com.ayzek_sigericweb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioProyectoFacade() {
        super(UsuarioProyecto.class);
    }
    
}
