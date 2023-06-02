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

import fr.recia.glc.db.utils.IntConst;
import fr.recia.glc.db.entities.personne.Eleve;
import fr.recia.glc.db.entities.personne.PersonneRelationEleve;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Set;

/**
 * @author GIP RECIA - Julien Gribonvald
 */
@Entity
@DiscriminatorValue(value = "PERSREL")
public class PersRelRelation extends AMappingRelation {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -155326042335081324L;

  /**
   * Type énuméré du type de lien de parenté.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I30)
  private LienParente lienParente;
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean responsableFinancier;
  /**
   * Type énuméré du type de la relation de stage.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I15)
  private ResponsableLegal responsableLegal;
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean contact;
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean paiement;
  /* La relation est une adresse de domiciliation. */
  @Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
  private boolean adresse;

  /**
   * Empty Constructor, must not be used.
   */
  public PersRelRelation() {
    super();
  }

  /**
   * @param source
   * @param persRelEleve
   * @param eleve
   * @param lienParente
   * @param responsableFinancier
   * @param responsableLegal
   * @param contact
   * @param paiement
   * @param adresse
   */
  public PersRelRelation(final String source, final PersonneRelationEleve persRelEleve, final Eleve eleve,
                         final LienParente lienParente, final boolean responsableFinancier,
                         final ResponsableLegal responsableLegal, final boolean contact,
                         final boolean paiement, final boolean adresse) {
    super(source, eleve, persRelEleve, CategorieRelation.PersRel);
    this.lienParente = lienParente;
    this.responsableFinancier = responsableFinancier;
    this.responsableLegal = responsableLegal;
    this.contact = contact;
    this.paiement = paiement;
    this.adresse = adresse;
  }

  /**
   * getter of Eleve to avoid confusion between personne1 and personne2 type
   *
   * @return <code>Eleve</code>
   */
  public Eleve getEleve() {
    return (Eleve) this.getPersonne1();
  }

  /**
   * getter of PersonneRelationEleve to avoid confusion between personne1 and
   * personne2 type
   *
   * @return <code>PersonneRelationEleve</code>
   */
  public PersonneRelationEleve getPersonneRelationEleve() {
    return (PersonneRelationEleve) this.getPersonne2();
  }

  /**
   * @return the lienParente
   */
  public LienParente getLienParente() {
    return lienParente;
  }

  /**
   * @param lienParente the lienParente to set
   */
  public void setLienParente(final LienParente lienParente) {
    this.lienParente = lienParente;
  }

  /**
   * @return the responsableFinancier
   */
  public boolean isResponsableFinancier() {
    return responsableFinancier;
  }

  /**
   * @param responsableFinancier the responsableFinancier to set
   */
  public void setResponsableFinancier(final boolean responsableFinancier) {
    this.responsableFinancier = responsableFinancier;
  }

  /**
   * @return the responsableLegal
   */
  public ResponsableLegal getResponsableLegal() {
    return responsableLegal;
  }

  /**
   * @param responsableLegal the responsableLegal to set
   */
  public void setResponsableLegal(final ResponsableLegal responsableLegal) {
    this.responsableLegal = responsableLegal;
  }

  /**
   * @return the contact
   */
  public boolean isContact() {
    return this.contact;
  }

  /**
   * @param contact the contact to set
   */
  public void setContact(final boolean contact) {
    this.contact = contact;
  }

  /**
   * @return the paiement
   */
  public boolean isPaiement() {
    return this.paiement;
  }

  /**
   * @param paiement the paiement to set
   */
  public void setPaiement(final boolean paiement) {
    this.paiement = paiement;
  }

  /**
   * @return the adresse
   */
  public boolean isAdresse() {
    return adresse;
  }

  /**
   * @param adresse the adresse to set
   */
  public void setAdresse(final boolean adresse) {
    this.adresse = adresse;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (adresse ? 1231 : 1237);
    result = prime * result + (contact ? 1231 : 1237);
    result = prime * result + ((lienParente == null) ? 0 : lienParente.hashCode());
    result = prime * result + (paiement ? 1231 : 1237);
    result = prime * result + (responsableFinancier ? 1231 : 1237);
    result = prime * result + ((responsableLegal == null) ? 0 : responsableLegal.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    PersRelRelation other = (PersRelRelation) obj;
    if (contact != other.contact)
      return false;
    if (lienParente != other.lienParente)
      return false;
    if (paiement != other.paiement)
      return false;
    if (responsableFinancier != other.responsableFinancier)
      return false;
    if (responsableLegal != other.responsableLegal)
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
    if (!super.equalsIgnoreSource(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    PersRelRelation other = (PersRelRelation) obj;
    if (adresse != other.adresse)
      return false;
    if (contact != other.contact)
      return false;
    if (lienParente != other.lienParente)
      return false;
    if (paiement != other.paiement)
      return false;
    if (responsableFinancier != other.responsableFinancier)
      return false;
    if (responsableLegal != other.responsableLegal)
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
  public static boolean containsWithoutSource(final Set<PersRelRelation> collection, final PersRelRelation object) {
    for (PersRelRelation item : collection) {
      if (item.equalsIgnoreSource(object))
        return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PersRelRelation [lienParente=" +
      this.lienParente + ", responsableFinancier=" +
      this.responsableFinancier + ", responsableLegal=" +
      this.responsableLegal + ", contact=" +
      this.contact + ", paiement=" +
      this.paiement + ", adresse=" +
      this.adresse + ", toString()=" +
      super.toString() +
      "]";
  }

}
