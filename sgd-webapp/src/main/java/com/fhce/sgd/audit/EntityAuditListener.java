package com.fhce.sgd.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhce.sgd.controller.AppController;

public class EntityAuditListener implements RevisionListener {
	
	@Autowired
	private AppController appCtrl;
	
	@Override
    public void newRevision(Object revisionEntity) {
		EntityAudit myRevisionEntity = (EntityAudit) revisionEntity;
        myRevisionEntity.setModifierUser(appCtrl.getUsuarioLogueado());
    }
}
