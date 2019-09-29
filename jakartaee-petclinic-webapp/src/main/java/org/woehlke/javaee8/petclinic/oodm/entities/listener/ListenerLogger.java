package org.woehlke.javaee8.petclinic.oodm.entities.listener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.woehlke.javaee8.petclinic.oodm.entities.common.TwEntities;

class ListenerLogger {

    static void logIt(String event, StandardLevel logLevel, Logger log, TwEntities domainObject){
        String[] infos = {
                event+" TableName  "+domainObject.getTableName(),
                event+" PrimaryKey "+domainObject.getPrimaryKey(),
                event+" Id         "+domainObject.getId(),
                event+" Uuid       "+domainObject.getUuid(),
                event+" toString   "+domainObject.toString()
        };
        switch (logLevel){
            case TRACE:
                for(String info:infos){
                    log.trace(info);
                }
                break;
            case DEBUG:
                for(String info:infos){
                    log.debug(info);
                }
                break;
            case INFO:
            default:
                for(String info:infos){
                    log.info(info);
                }
                break;
        }
    }
}
