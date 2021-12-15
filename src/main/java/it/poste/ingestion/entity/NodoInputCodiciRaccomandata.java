/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.ingestion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;


/**
 *
 * @author alex
 */
@Builder @AllArgsConstructor
@Entity
@Table(name = "NodoInputCodiciRaccomandata")
public class NodoInputCodiciRaccomandata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CodiceRaccomandata")
    private long codiceRaccomandata;

    public NodoInputCodiciRaccomandata() {
    }

    public NodoInputCodiciRaccomandata(Long id) {
        this.id = id;
    }

    /*
    public NodoInputRaccomandata(Long id, long codiceRaccomandata) {
        this.id = id;
        this.codiceRaccomandata = codiceRaccomandata;
    }
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCodiceRaccomandata() {
        return codiceRaccomandata;
    }

    public void setCodiceRaccomandata(long codiceRaccomandata) {
        this.codiceRaccomandata = codiceRaccomandata;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodoInputCodiciRaccomandata)) {
            return false;
        }
        NodoInputCodiciRaccomandata other = (NodoInputCodiciRaccomandata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.poste.ingestion.entity.NodoInputRaccomandata[ id=" + id + " ]";
    }
    
}
