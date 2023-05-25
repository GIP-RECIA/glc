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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 oct. 2013
 */
@Embeddable
public class MappingAPersonneAPersonneId implements Serializable {

  /**
   * Identifiant de serialisation
   */
  private static final long serialVersionUID = -3355562175987895747L;

  /**
   * The personne to associate to the personne2.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "APERSONNE1_ID", nullable = false)
  private APersonne personne1;

  /**
   * The personne to associate to the personne2.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "APERSONNE2_ID", nullable = false)
  private APersonne personne2;

  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private CategorieRelation categorie;

  /**
   * Contructor of the object MappingAPersonneAPersonneId.java.
   */
  public MappingAPersonneAPersonneId() {
    super();
  }

  /**
   * Contructor of the object MappingAPersonneAPersonne.java.
   *
   * @param personne1
   * @param personne2
   */
  public MappingAPersonneAPersonneId(final APersonne personne1, final APersonne personne2,
                                     final CategorieRelation categoryRelation) {
    super();
    this.personne1 = personne1;
    this.personne2 = personne2;
    this.categorie = categoryRelation;
  }

  /**
   * Getter of member personne.
   *
   * @return <code>APersonne</code> the attribute personne
   */
  protected APersonne getPersonne1() {
    return personne1;
  }

  /**
   * Setter of attribute personne.
   *
   * @param personne the attribute personne to set
   */
  protected void setPersonne1(final APersonne personne) {
    this.personne1 = personne;
  }

  /**
   * Getter of member personne2.
   *
   * @return <code>APersonneOfAPersonne</code> the attribute personne2
   */
  protected APersonne getPersonne2() {
    return personne2;
  }

  /**
   * Setter of attribute personne2.
   *
   * @param personne2 the attribute personne2 to set
   */
  protected void setPersonne2(final APersonne personne2) {
    this.personne2 = personne2;
  }

  /**
   * Getter of member categorie.
   *
   * @return <code>CategoryRelation</code> the attribute categorie
   */
  public CategorieRelation getCategorie() {
    return categorie;
  }

  /**
   * Setter of attribute categorie.
   *
   * @param categoryRelation the attribute categorie to set
   */
  public void setCategorie(CategorieRelation categoryRelation) {
    this.categorie = categoryRelation;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
    result = prime * result + ((personne1 == null) ? 0 : personne1.hashCode());
    result = prime * result + ((personne2 == null) ? 0 : personne2.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MappingAPersonneAPersonneId other = (MappingAPersonneAPersonneId) obj;
    if (categorie != other.categorie)
      return false;
    if (personne1 == null) {
      if (other.personne1 != null)
        return false;
    } else if (!personne1.equals(other.personne1))
      return false;
    if (personne2 == null) {
      if (other.personne2 != null)
        return false;
    } else if (!personne2.equals(other.personne2))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MappingAPersonneAPersonneId [personne1=" +
      this.personne1 + ", personne2=" +
      this.personne2 + ", categoryRelation=" +
      this.categorie +
      "]";
  }

}
