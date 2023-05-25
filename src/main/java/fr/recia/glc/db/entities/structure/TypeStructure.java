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
package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Définition du type de structure : Lycée d'enseignement général, lycée professionnel, collège...
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>libelle, sigle (même si l'un des deux champs est nul, l'initialiser à String vide : "").</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 11 juin 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"libelle", "sigle"})
})
public class TypeStructure extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 1191498724458361075L;

  //Attributs
  /**
   * Nom complet du type.
   */
  @Column(length = IntConst.I80)
  private String libelle;
  /**
   * Nom court du type.
   */
  @Column(length = IntConst.I25)
  private String sigle;

  //Relations
  /**
   * Relation bidirectionnelle.
   * Liste des structures de ce type.
   */
  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
  private Set<AStructure> structures = new HashSet<>();

  //Constructeurs

  /**
   * Constructeur de l'objet TypeStructure.java.
   */
  public TypeStructure() {
    super();
  }

  /**
   * Constructeur de l'objet TypeStructure.java.
   *
   * @param sigle   Nom court du type.
   * @param libelle Nom complet du type.
   */
  public TypeStructure(final String sigle, final String libelle) {
    super();
    this.sigle = sigle;
    this.libelle = libelle;
  }

  //Accesseurs

  /**
   * Getter du membre libelle.
   *
   * @return <code>String</code> le membre libelle.
   */
  public String getLibelle() {
    return this.libelle;
  }

  /**
   * Setter du membre libelle.
   *
   * @param libelle la nouvelle valeur du membre libelle.
   */
  public void setLibelle(final String libelle) {
    this.libelle = libelle;
  }

  /**
   * Getter du membre sigle.
   *
   * @return <code>String</code> le membre sigle.
   */
  public String getSigle() {
    return this.sigle;
  }

  /**
   * Setter du membre sigle.
   *
   * @param sigle la nouvelle valeur du membre sigle.
   */
  public void setSigle(final String sigle) {
    this.sigle = sigle;
  }

  //Relations

  /**
   * Getter du membre structures.
   *
   * @return <code>Set< AStructure ></code> le membre structures.
   */
  public Set<AStructure> getStructures() {
    return this.structures;
  }

  /**
   * Setter du membre structures.
   *
   * @param structures la nouvelle valeur du membre structures.
   */
  public void setStructures(final Set<AStructure> structures) {
    this.structures = structures;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "TypeStructure [" +
      super.toString() + ", " +
      this.libelle + ", " +
      this.sigle +
      "]";
  }

  /**
   * Donne la valeur de hachage de l'instance.
   *
   * @return <code>int</code> La valeur du hash.
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    if (this.libelle == null) {
      result = prime * result;
    } else {
      result = prime * result + this.libelle.hashCode();
    }
    return result;
  }

  /**
   * Teste si un objet est égal à cette instance.
   *
   * @param obj l'instance le l'object à comparer.
   * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof TypeStructure)) {
      return false;
    }
    final TypeStructure other = (TypeStructure) obj;
    if (this.libelle == null) {
      if (other.libelle != null) {
        return false;
      }
    } else if (!this.libelle.equals(other.libelle)) {
      return false;
    }
    return true;
  }

}
