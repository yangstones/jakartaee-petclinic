package org.woehlke.javaee8.petclinic.frontend.web.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.javaee8.petclinic.frontend.web.LanguageView;
import org.woehlke.javaee8.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.javaee8.petclinic.frontend.web.common.ViewModelOperations;
import org.woehlke.javaee8.petclinic.oodm.entities.PetType;
import org.woehlke.javaee8.petclinic.oodm.services.PetTypeService;
import org.woehlke.javaee8.petclinic.frontend.web.PetTypeView;

import javax.faces.bean.ManagedBean;;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("deprecation")
@ManagedBean(name="petTypeView")
@SessionScoped
public class PetTypeViewImpl implements PetTypeView, ViewModelOperations {

    private static Logger log = LogManager.getLogger(PetTypeViewImpl.class);

    @EJB
    private PetTypeService entityService;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    private PetType entity;
    private PetType selected;
    private List<PetType> list;
    private String searchterm;

    @PostConstruct
    public void init(){
        log.trace("postConstruct");
    }

    @Override
    public String showEntityList() {
        loadList();
        return "petTypeList.jsf";
    }

    @Override
    public String showNewForm(){
        newEntity();
        return "petTypeNew.jsf";
    }

    @Override
    public String cancel(){
        loadList();
        return "petTypeList.jsf";
    }

    @Override
    public String showSelectedEntity(){
        return showEditForm();
    }

    @Override
    public String saveNew(){
        saveNewEntity();
        loadList();
        return "petTypeList.jsf";
    }

    @Override
    public String showEditForm(){
        if(this.selected != null) {
            reloadEntityFromSelected();
            return "petTypeEdit.jsf";
        } else {
            loadList();
            return "petTypeList.jsf";
        }
    }

    @Override
    public String saveEdited(){
        saveEditedEntity();
        loadList();
        return "petTypeList.jsf";
    }

    @Override
    public String deleteSelected(){
        deleteSelectedEntity();
        return "petTypeList.jsf";
    }

    @Override
    public String search(){
        performSearch();
        return "petTypeList.jsf";
    }

    @Override
    public void performSearch() {
        if(searchterm==null || searchterm.isEmpty()){
            this.list = entityService.getAll();
        } else {
            try {
                this.list = entityService.search(searchterm);
                frontendMessagesView.addInfoMessage("Search ", "Found "+this.list.size()+ "results for searchterm "+searchterm);
            } catch (Exception e){
                log.debug(e.getMessage());
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
                this.list = entityService.getAll();
            }
        }
    }

    @Override
    public PetType getEntity() {
        if(entity == null){
            newEntity();
        }
        return entity;
    }

    @Override
    public void setEntity(PetType entity) {
        this.entity = entity;
    }

    public FrontendMessagesView getFrontendMessagesView() {
        return frontendMessagesView;
    }

    public void setFrontendMessagesView(FrontendMessagesView frontendMessagesView) {
        this.frontendMessagesView = frontendMessagesView;
    }

    @Override
    public PetType getSelected() {
        return selected;
    }

    @Override
    public void setSelected(PetType selected) {
        this.selected = selected;
        if( this.selected != null ){
            this.entity = entityService.findById(this.selected.getId());
        }
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public LanguageView getLanguageView() {
        return languageView;
    }

    public void setLanguageView(LanguageView languageView) {
        this.languageView = languageView;
    }

    public List<PetType> getList() {
        loadList();
        return this.list;
    }

    public void setList(List<PetType> list) {
        this.list = list;
    }

    @Override
    public void reloadEntityFromSelected() {
        if( this.selected != null ){
            this.entity = entityService.findById(this.selected.getId());
            this.selected = this.entity;
        }
    }

    @Override
    public void loadEntity(){
        if(this.entity != null) {
            this.entity = entityService.findById(this.entity.getId());
        } else {
            frontendMessagesView.addWarnMessage("cannot load Entity","");
        }
    }

    @Override
    public void loadList() {
        this.list = this.entityService.getAll();
    }

    @Override
    public void saveNewEntity() {
        try {
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            this.selected = this.entityService.addNew(this.entity);
            this.entity = this.selected;
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            frontendMessagesView.addInfoMessage("Added", this.entity.getPrimaryKey());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
        }
    }

    @Override
    public void saveEditedEntity() {
        try {
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            this.entity = this.entityService.update(this.entity);
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            frontendMessagesView.addInfoMessage("Updated", this.entity.getPrimaryKey());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
        }
    }

    @Override
    public void deleteSelectedEntity(){
        try {
            if(this.selected != null) {
                String msgInfo = this.selected.getPrimaryKey();
                if(this.selected.compareTo(this.entity)==0){
                    this.entity = null;
                }
                entityService.delete(this.selected.getId());
                this.selected = null;
                frontendMessagesView.addInfoMessage("Deleted", msgInfo);
            }
            loadList();
        } catch (EJBTransactionRolledbackException e) {
            frontendMessagesView.addWarnMessage("cannot delete, object still in use", this.selected);
        } catch (EJBException e){
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }

    @Override
    public void newEntity() {
        this.entity = new PetType();
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

}
