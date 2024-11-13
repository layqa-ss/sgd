package com.fhce.sgd.audit;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import jakarta.persistence.Entity;

@Entity
@RevisionEntity(EntityAuditListener.class)
public class EntityAudit extends DefaultRevisionEntity {
	
	private String modifierUser;

    public String getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(String modifierUser) {
        this.modifierUser = modifierUser;
    }

}
