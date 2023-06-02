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

import fr.recia.glc.db.utils.IntConst;
import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.groupe.GroupementEtablissements;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Structure étendue en Etablissement.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>nom, siren, cleJointure, uai, ministereTutelle, contrat, typeStructure.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 11 juin 08
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Etablissement extends AStructure {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -4725840080156542768L;

  //Attributs
  /**
   * Numero UAI, ancien RNE.
   */
  @Column(unique = true, length = IntConst.IUAI)
  private String uai;
  /**
   * Type de contrat (public, privé, non défini).
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I30)
  private Contrat contrat;

  //Relations
  /**
   * Relation unidirectionnelle.
   * Le ministère de tutelle.
   */
  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinColumn(name = "ministere_tutelle_fk")
  private MinistereTutelle ministereTutelle;
  /**
   * Relation unidirectionnelle.
   * Le bassin de formation.
   */
  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinColumn(name = "bassin_formation_fk")
  private BassinFormation bassinFormation;
  /**
   * Relation unidirectionnelle.
   * La Liste des structures de rattachement administratif.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinTable(name = "etab_rattachement_administratif", joinColumns = @JoinColumn(name = "ETABLISSEMENT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ASTRUCTURE_ID", referencedColumnName = "ID"))
  private Set<AStructure> rattachementAdministratif = new HashSet<>();
  /**
   * Relation unidirectionnelle.
   * La Liste des structures de rattachement fonctionnel.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinTable(name = "etab_rattachement_fonctionnel", joinColumns = @JoinColumn(name = "ETABLISSEMENT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ASTRUCTURE_ID", referencedColumnName = "ID"))
  private Set<AStructure> rattachementFonctionnel = new HashSet<>();
  /**
   * Relation bidirectionnelle.
   * Liste des groupes d'établissement dont l'établissement fait parti.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "membres")
  private Set<GroupementEtablissements> groupements = new HashSet<>();

  //Constructeurs

  /**
   * Constructeur de l'objet Etablissement.java.
   */
  public Etablissement() {
    super();
    this.setCategorie(CategorieStructure.Etablissement);
  }

  /**
   * Constructeur de l'objet Etablissement.java.
   *
   * @param uai              Numero UAI, ancien RNE.
   * @param nom              Nom unique de la stucture.
   * @param siren            Numéro de SIRET/SIREN unique de la structure.
   * @param cleJointure      Clé de jointure unique de la structure.
   * @param ministereTutelle Ministère de tutelle.
   * @param contrat          Type de contrat (public, privé, non défini).
   */
  public Etablissement(final String uai, final String nom, final String siren, final CleJointure cleJointure,
                       final MinistereTutelle ministereTutelle, final Contrat contrat) {
    super(CategorieStructure.Etablissement, nom, siren, cleJointure);
    this.uai = uai;
    this.ministereTutelle = ministereTutelle;
    this.contrat = contrat;
  }

  //Accesseurs

  /**
   * Getter du membre uai.
   *
   * @return <code>String</code> le membre uai.
   */
  public String getUai() {
    return this.uai;
  }

  /**
   * Setter du membre uai.
   *
   * @param uai la nouvelle valeur du membre uai.
   */
  public void setUai(final String uai) {
    this.uai = uai;
  }

  /**
   * Getter du membre contrat.
   *
   * @return <code>Contrat</code> le membre contrat.
   */
  public Contrat getContrat() {
    return this.contrat;
  }

  /**
   * Setter du membre contrat.
   *
   * @param contrat la nouvelle valeur du membre contrat.
   */
  public void setContrat(final Contrat contrat) {
    this.contrat = contrat;
  }

  /**
   * Getter du membre ministereTutelle.
   *
   * @return <code>MinistereTutelle</code> le membre ministereTutelle.
   */
  public MinistereTutelle getMinistereTutelle() {
    return this.ministereTutelle;
  }

  /**
   * Setter du membre ministereTutelle.
   *
   * @param ministereTutelle la nouvelle valeur du membre ministereTutelle.
   */
  public void setMinistereTutelle(final MinistereTutelle ministereTutelle) {
    this.ministereTutelle = ministereTutelle;
  }

  // Relations

  /**
   * Getter du membre bassinFormation.
   *
   * @return <code>BassinFormation</code> le membre bassinFormation.
   */
  public BassinFormation getBassinFormation() {
    return this.bassinFormation;
  }

  /**
   * Setter du membre bassinFormation.
   *
   * @param bassinFormation la nouvelle valeur du membre bassinFormation.
   */
  public void setBassinFormation(final BassinFormation bassinFormation) {
    this.bassinFormation = bassinFormation;
  }

  /**
   * Getter du membre rattachementAdministratif.
   *
   * @return <code>Set< AStructure ></code> le membre rattachementAdministratif.
   */
  public Set<AStructure> getRattachementAdministratif() {
    return this.rattachementAdministratif;
  }

  /**
   * Setter du membre rattachementAdministratif.
   *
   * @param rattachementAdministratif la nouvelle valeur du membre rattachementAdministratif.
   */
  public void setRattachementAdministratif(final Set<AStructure> rattachementAdministratif) {
    this.rattachementAdministratif = rattachementAdministratif;
  }

  /**
   * Getter du membre rattachementFonctionnel.
   *
   * @return <code>Set< AStructure ></code> le membre rattachementFonctionnel.
   */
  public Set<AStructure> getRattachementFonctionnel() {
    return this.rattachementFonctionnel;
  }

  /**
   * Setter du membre rattachementFonctionnel.
   *
   * @param rattachementFonctionnel la nouvelle valeur du membre rattachementFonctionnel.
   */
  public void setRattachementFonctionnel(final Set<AStructure> rattachementFonctionnel) {
    this.rattachementFonctionnel = rattachementFonctionnel;
  }

  /**
   * Getter du membre groupements.
   *
   * @return <code>Set< GroupementEtablissements ></code> le membre groupements.
   */
  public Set<GroupementEtablissements> getGroupements() {
    return this.groupements;
  }

  /**
   * Setter du membre groupements.
   *
   * @param groupements la nouvelle valeur du membre groupements.
   */
  public void setGroupements(final Set<GroupementEtablissements> groupements) {
    this.groupements = groupements;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.structure.AStructure#toString()
   */
  @Override
  public String toString() {
    return "Etablissement [" +
      super.toString() + ", " +
      this.uai + ", " +
      this.contrat + ", " +
      this.bassinFormation + ", " +
      this.ministereTutelle + ", " +
      this.groupements +
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
    int result = super.hashCode();
    if (this.uai == null) {
      result = prime * result;
    } else {
      result = prime * result + this.uai.hashCode();
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
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof Etablissement)) {
      return false;
    }
    final Etablissement other = (Etablissement) obj;
    if (this.uai == null) {
      if (other.uai != null) {
        return false;
      }
    } else if (!this.uai.equals(other.uai)) {
      return false;
    }
    return true;
  }

}
