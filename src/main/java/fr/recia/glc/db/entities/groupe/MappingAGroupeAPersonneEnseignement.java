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
package fr.recia.glc.db.entities.groupe;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.education.Enseignement;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.personne.Enseignant;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 oct. 2013
 */
@Entity
@Table(name = "apersonnes_agroupes_enseignements")
@AssociationOverrides({
  @AssociationOverride(name = "pk.enseignant",
    joinColumns = @JoinColumn(name = "APERSONNE_ID")),
  @AssociationOverride(name = "pk.groupe",
    joinColumns = @JoinColumn(name = "AGROUPEOFFONCCLASSEGROUPE_ID")),
  @AssociationOverride(name = "pk.enseignement",
    joinColumns = @JoinColumn(name = "ENSEIGNEMENT_ID"))})
public class MappingAGroupeAPersonneEnseignement implements Serializable {

  /**
   * Identifiant de serialisation
   */
  private static final long serialVersionUID = 9171972868617131333L;

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
  private MappingAGroupeAPersonneEnseignementId pk = new MappingAGroupeAPersonneEnseignementId();

  /**
   * Contructor of the object MappingAGroupeAPersonneEnseignement.java.
   */
  public MappingAGroupeAPersonneEnseignement() {
    super();
  }

  /**
   * Contructor of the object MappingAGroupeAPersonneEnseignement.java.
   *
   * @param source
   * @param groupe
   * @param enseignant
   * @param enseignement
   */
  public MappingAGroupeAPersonneEnseignement(final String source, final Enseignant enseignant,
                                             final AGroupeOfFoncClasseGroupe groupe, final Enseignement enseignement) {
    super();
    this.source = source;
    this.pk = new MappingAGroupeAPersonneEnseignementId(enseignant, groupe, enseignement);
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
   * @return <code>MappingAGroupeAPersonneEnseignementId</code> the attribute pk
   */
  public MappingAGroupeAPersonneEnseignementId getPk() {
    return pk;
  }

  /**
   * Setter of attribute pk.
   *
   * @param pk the attribute pk to set
   */
  public void setPk(final MappingAGroupeAPersonneEnseignementId pk) {
    this.pk = pk;
  }

  /**
   * Getter of member enseignant.
   *
   * @return <code>Enseignant</code> the attribute enseignant
   */
  public APersonne getEnseignant() {
    return this.getPk().getEnseignant();
  }

  /**
   * Setter of attribute enseignant.
   *
   * @param enseignant the attribute enseignant to set
   */
  public void setEnseignant(final Enseignant enseignant) {
    this.getPk().setEnseignant(enseignant);
  }

  /**
   * Getter of member groupe.
   *
   * @return <code>AGroupeOfAPersonne</code> the attribute groupe
   */
  public AGroupeOfFoncClasseGroupe getGroupe() {
    return this.getPk().getGroupe();
  }

  /**
   * Setter of attribute groupe.
   *
   * @param groupe the attribute groupe to set
   */
  public void setGroupe(final AGroupeOfFoncClasseGroupe groupe) {
    this.getPk().setGroupe(groupe);
  }

  /**
   * Getter of member enseignement.
   *
   * @return <code>Enseignement</code> the attribute enseignement
   */
  public Enseignement getEnseignement() {
    return this.getPk().getEnseignement();
  }

  /**
   * Setter of attribute enseignement.
   *
   * @param enseignement the attribute enseignement to set
   */
  public void setEnseignement(final Enseignement enseignement) {
    this.getPk().setEnseignement(enseignement);
  }

  /**
   * @see java.lang.Object#hashCode()
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
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MappingAGroupeAPersonneEnseignement other = (MappingAGroupeAPersonneEnseignement) obj;
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "MappingAGroupeAPersonneEnseignement [source=" +
      source + ", division=" +
      this.pk.getGroupe().getId() + ", enseignant=" +
      this.pk.getEnseignant().getId() + ", enseignement=" +
      this.pk.getEnseignement().getId() +
      "]";
  }

}
