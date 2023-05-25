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

import fr.recia.glc.db.entities.common.CleJointure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Structure étendue en Entreprise.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>nom, siren, cleJointure.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 11 juin 08
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entreprise extends AStructure {


  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 6582934001451242942L;

  //Attributs

  //Relations
  /**
   * Relation unidirectionnelle.
   * Liste des établissement en partenariat avec l'entreprise.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinTable(name = "partenariat",
    joinColumns =
    @JoinColumn(name = "ENTREPRISE_ID", referencedColumnName = "ID"),
    inverseJoinColumns =
    @JoinColumn(name = "ETABLISSEMENT_ID", referencedColumnName = "ID"))
  private Set<Etablissement> partenariat = new HashSet<>();

  //Constructeurs

  /**
   * Constructeur de l'objet Entreprise.java.
   */
  public Entreprise() {
    super();
    this.setCategorie(CategorieStructure.Entreprise);
  }

  /**
   * Constructeur de l'objet Entreprise.java.
   *
   * @param nom         Nom unique de la stucture.
   * @param siren       Numéro de SIRET/SIREN unique de la structure.
   * @param cleJointure Clé de jointure unique de la structure.
   */
  public Entreprise(final String nom, final String siren, final CleJointure cleJointure) {
    super(CategorieStructure.Entreprise, nom, siren, cleJointure);
  }

  //Accesseurs

  /**
   * Getter du membre partenariat.
   *
   * @return <code>Set< Etablissement ></code> le membre partenariat.
   */
  public Set<Etablissement> getPartenariat() {
    return this.partenariat;
  }

  /**
   * Setter du membre partenariat.
   *
   * @param partenariat la nouvelle valeur du membre partenariat.
   */
  public void setPartenariat(final Set<Etablissement> partenariat) {
    this.partenariat = partenariat;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.structure.AStructure#toString()
   */
  @Override
  public String toString() {
    return "Entreprise [" +
      super.toString() + ", " +
      this.partenariat +
      "]";
  }

}
