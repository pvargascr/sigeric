/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.ejb;

import com.ayzek.sigericweb.model.Activo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Varsandrey
 */
@Stateless
public class ActivoFacade extends AbstractFacade<Activo> {

    @PersistenceContext(unitName = "com.ayzek_sigericweb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActivoFacade() {
        super(Activo.class);
    }

    public List<Activo> listar(){
    Query q=em.createNativeQuery("SELECT * FROM SIGERICDB.ACTIVO", Activo.class);
    return q.getResultList();
}

}
