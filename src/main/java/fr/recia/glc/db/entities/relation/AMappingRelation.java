/*
 * Copyright (C) 2023 GIP-RECIA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.recia.glc.db.entities.relation;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 oct. 2013
 */
@Entity
@Table(name = "relations_apersonnes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AssociationOverrides({@AssociationOverride(name = "pk.personne1", joinColumns = @JoinColumn(name = "APERSONNE1_ID")),
  @AssociationOverride(name = "pk.personne2", joinColumns = @JoinColumn(name = "APERSONNE2_ID"))})
public abstract class AMappingRelation implements Serializable {

  /**
   * Identifiant de serialisation
   */
  private static final long serialVersionUID = -317565852223813976L;

  /**
   * The Source which insert the entry.
   */
  @Basic
  @Column(name = "SOURCE", length = IntConst.ISOURCE, nullable = false)
  private String source;

  /**
   * The pk
   */
  @EmbeddedId
  private MappingAPersonneAPersonneId pk = new MappingAPersonneAPersonneId();

  /**
   * Empty Constructor, must not be used.
   */
  public AMappingRelation() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Contructor of the object MappingAGroupeAPersonne.java.
   *
   * @param source
   * @param personne1
   * @param personne2
   */
  public AMappingRelation(final String source, final APersonne personne1,
                          final APersonne personne2, final CategorieRelation categoryRelation) {
    super();
    this.source = source;
    this.pk = new MappingAPersonneAPersonneId(personne1, personne2, categoryRelation);
  }

  /**
   * Getter of member source.
   *
   * @return <code>String</code> the attribute source
   */
  public String getSource() {
    return source;
  }

  /**
   * Setter of attribute source.
   *
   * @param source the attribute source to set
   */
  public void setSource(final String source) {
    this.source = source;
  }

  /**
   * Getter of member pk.
   *
   * @return <code>MappingAGroupeAPersonneId</code> the attribute pk
   */
  public MappingAPersonneAPersonneId getPk() {
    return pk;
  }

  /**
   * Setter of attribute pk.
   *
   * @param pk the attribute pk to set
   */
  public void setPk(final MappingAPersonneAPersonneId pk) {
    this.pk = pk;
  }

  /**
   * Getter of member personne.
   *
   * @return <code>APersonne</code> the attribute personne
   */
  //@Transient
  protected APersonne getPersonne1() {
    return this.getPk().getPersonne1();
  }

  /**
   * Setter of attribute personne.
   *
   * @param personne the attribute personne to set
   */
  protected void setPersonne1(APersonne personne) {
    this.getPk().setPersonne1(personne);
  }

  /**
   * Getter of member personne.
   *
   * @return <code>APersonne</code> the attribute personne
   */
  //@Transient
  protected APersonne getPersonne2() {
    return this.getPk().getPersonne2();
  }

  /**
   * Setter of attribute personne.
   *
   * @param personne the attribute personne to set
   */
  protected void setPersonne2(APersonne personne) {
    this.getPk().setPersonne2(personne);
  }

  /**
   * Getter of member categorie.
   *
   * @return <code>CategoryRelation</code> the attribute categorie
   */
  public CategorieRelation getCategorie() {
    return this.getPk().getCategorie();
  }

  /**
   * Setter of attribute categorie.
   *
   * @param categoryRelation the attribute categorie to set
   */
  public void setCategorie(CategorieRelation categoryRelation) {
    this.getPk().setCategorie(categoryRelation);
  }

  /**
   * @see Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pk == null) ? 0 : pk.hashCode());
    result = prime * result + ((source == null) ? 0 : source.hashCode());
    return result;
  }

  /**
   * @see Object#equals(Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AMappingRelation other = (AMappingRelation) obj;
    if (pk == null) {
      if (other.pk != null)
        return false;
    } else if (!pk.equals(other.pk))
      return false;
    if (source == null) {
      if (other.source != null)
        return false;
    } else if (!source.equals(other.source))
      return false;
    return true;
  }

  /**
   * Check if the object is equals without source comparison
   *
   * @param obj
   * @return true is equals else false.
   */
  public boolean equalsIgnoreSource(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AMappingRelation other = (AMappingRelation) obj;
    if (pk == null) {
      if (other.pk != null)
        return false;
    } else if (!pk.equals(other.pk))
      return false;
    return true;
  }

  /**
   * Test if a set contains a groupe.
   *
   * @param collection The set of MappingAPersonneAPersonne where to check.
   * @param object     the groupe To find.
   * @return true if contains, else false.
   */
  public static boolean containsWithoutSource(final Set<AMappingRelation> collection, final AMappingRelation object) {
    for (AMappingRelation item : collection) {
      if (item.equalsIgnoreSource(object))
        return true;
    }
    return false;
  }

  /**
   * @see Object#toString()
   */
  @Override
  public String toString() {
    return "MappingAPersonneAPersonne [source=" +
      source + ", categoryRelation=" +
      this.pk.getCategorie() + ", personne1=" +
      this.getPersonne1().getId() + ", personne2=" +
      this.getPersonne2().getId() +
      "]";
  }

}
