package org.woehlke.jakartaee.petclinic.oodm.view.flow;


import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.impl.CrudViewFlow;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SuppressWarnings("deprecation")
@ManagedBean(name="vetViewFlow")
@SessionScoped
public class VetViewFlow extends CrudViewFlow implements HasCrudFlowState, Serializable {
    private static final long serialVersionUID = -4032116498859585466L;
}