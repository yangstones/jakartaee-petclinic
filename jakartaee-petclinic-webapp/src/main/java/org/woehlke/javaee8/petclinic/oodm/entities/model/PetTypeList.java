package org.woehlke.javaee8.petclinic.oodm.entities.model;

import org.woehlke.javaee8.petclinic.oodm.entities.PetType;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name="petTypeList")
@XmlType(
        name="petTypeList",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/PetTypeList",
        propOrder = {
                "petTypeList"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetTypeList implements Serializable {

    @XmlElement(required=true)
    private List<PetType> petTypeList;

    public PetTypeList() {
        this.petTypeList = new ArrayList<>();
    }

    public PetTypeList(@NotNull List<PetType> petTypeList) {
        this.petTypeList = petTypeList;
    }

    public List<PetType> getPetTypeList() {
        return petTypeList;
    }

    public void setPetTypeList(List<PetType> petTypeList) {
        this.petTypeList = petTypeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetTypeList)) return false;
        PetTypeList that = (PetTypeList) o;
        return getPetTypeList().equals(that.getPetTypeList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetTypeList());
    }
}
