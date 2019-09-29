package org.woehlke.javaee8.petclinic.oodm.entities.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.woehlke.javaee8.petclinic.oodm.entities.Owner;

import javax.persistence.*;
import java.io.Serializable;

import static org.woehlke.javaee8.petclinic.oodm.entities.listener.ListenerLogger.logIt;

public class OwnerListener implements Serializable {

    private static Logger log = LogManager.getLogger(OwnerListener.class.getName());

    @PrePersist
    public void onPrePersist(Owner domainObject) {
        logIt("try to Persist: ",StandardLevel.DEBUG,log, domainObject);
    }

    @PreUpdate
    public void onPreUpdate(Owner domainObject) {
        logIt("try to Update: ",StandardLevel.DEBUG,log, domainObject);
    }

    @PreRemove
    public void onPreRemove(Owner domainObject) {
        logIt("try to Remove: ",StandardLevel.INFO,log, domainObject);
    }

    @PostPersist
    public void onPostPersist(Owner domainObject) {
        logIt("Persisted: ",StandardLevel.INFO,log, domainObject);
    }

    @PostUpdate
    public void onPostUpdate(Owner domainObject) {
        logIt("Updated: ",StandardLevel.INFO,log, domainObject);
    }

    @PostRemove
    public void onPostRemove(Owner domainObject) {
        logIt("Removed: ",StandardLevel.INFO,log, domainObject);
    }

    @PostLoad
    public void onPostLoad(Owner domainObject) {
        logIt("Loaded: ",StandardLevel.TRACE,log, domainObject);
    }


}
