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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Bean - Entity.
 * Structure étendue en collectivité locale.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>nom, siren, cleJointure, lieuGeographique.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CollectiviteLocale extends AStructure {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 241929731383689377L;

  //Attributs
  /**
   * Région ou département de la collectivité.
   */
  @Column(nullable = false)
  private String lieuGeographique;

  //Constructeurs

  /**
   * Constructeur de l'objet CollectiviteLocale.java.
   */
  public CollectiviteLocale() {
    super();
    this.setCategorie(CategorieStructure.Collectivite_locale);
  }

  /**
   * Constructeur de l'objet CollectiviteLocale.java.
   *
   * @param nom              Nom unique de la stucture.
   * @param siren            Numéro de SIRET/SIREN unique de la structure.
   * @param cleJointure      Clé de jointure unique de la structure.
   * @param lieuGeographique Région ou département de la collectivité.
   */
  public CollectiviteLocale(final String nom, final String siren,
                            final CleJointure cleJointure, final String lieuGeographique) {
    super(CategorieStructure.Collectivite_locale, nom, siren, cleJointure);
    this.lieuGeographique = lieuGeographique;
  }

  //Accesseurs

  /**
   * Getter du membre lieuGeographique.
   *
   * @return <code>String</code> le membre lieuGeographique.
   */
  public String getLieuGeographique() {
    return this.lieuGeographique;
  }

  /**
   * Setter du membre lieuGeographique.
   *
   * @param lieuGeographique la nouvelle valeur du membre lieuGeographique.
   */
  public void setLieuGeographique(final String lieuGeographique) {
    this.lieuGeographique = lieuGeographique;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.structure.AStructure#toString()
   */
  @Override
  public String toString() {
    return "CollectiviteLocale [" +
      super.toString() + ", " +
      this.lieuGeographique +
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
    if (lieuGeographique == null) {
      result = prime * result;
    } else {
      result = prime * result + lieuGeographique.hashCode();
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
    if (!(obj instanceof CollectiviteLocale)) {
      return false;
    }
    final CollectiviteLocale other = (CollectiviteLocale) obj;
    if (lieuGeographique == null) {
      if (other.lieuGeographique != null) {
        return false;
      }
    } else if (!lieuGeographique.equals(other.lieuGeographique)) {
      return false;
    }
    return true;
  }

}
