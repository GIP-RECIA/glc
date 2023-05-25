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
package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.personne.APersonne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Bean - Entity.
 * Objet abstrait de fonction.
 * <b><u>Champs obligatoires :</u>
 * categorie, personne, source.</b>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 5 juin 08
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AFonction extends AbstractEntity {

  //Attributs
  /**
   * Categorie de la fonction.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20)
  private CategorieFonction categorie;
  /**
   * Source d'alimentation de la fonction.
   */
  @Column(length = IntConst.ISOURCE)
  private String source;

  //Relations
  /**
   * Relation bidirectionnelle.
   *
   * @see fr.recia.glc.db.entities.personne.APersonne
   */
  @ManyToOne
  @JoinColumn(name = "personne_fk")
  private APersonne personne;

  //Constructeurs

  /**
   * Constructeur de l'objet AFonction.java.
   */
  public AFonction() {
    super();
  }

  /**
   * Constructeur de l'objet AFonction.java.
   *
   * @param categorie Categorie de la fonction.
   * @param personne  Personne ayant cette fonction.
   * @param source    Source d'alimentation gérant cette fonction.
   */
  public AFonction(final CategorieFonction categorie, final APersonne personne, final String source) {
    super();
    this.personne = personne;
    this.categorie = categorie;
    this.source = source;
  }

  //Accesseurs

  /**
   * Getter du membre categorie.
   *
   * @return <code>CategorieFonction</code> le membre categorie
   */
  public CategorieFonction getCategorie() {
    return this.categorie;
  }

  /**
   * Setter du membre categorie.
   *
   * @param categorie la nouvelle valeur du membre categorie
   */
  public void setCategorie(final CategorieFonction categorie) {
    this.categorie = categorie;
  }

  /**
   * Getter du membre source.
   *
   * @return <code>String</code> le membre source.
   */
  public String getSource() {
    return source;
  }

  /**
   * Setter du membre source.
   *
   * @param source la nouvelle valeur du membre source.
   */
  public void setSource(final String source) {
    this.source = source;
  }

  // Relations

  /**
   * Getter du membre personne.
   *
   * @return <code>APersonne</code> le membre personne
   */
  public APersonne getPersonne() {
    return this.personne;
  }

  /**
   * Setter du membre personne.
   *
   * @param personne la nouvelle valeur du membre personne
   */
  public void setPersonne(final APersonne personne) {
    this.personne = personne;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "AFonction [" +
      super.toString() + ", " +
      this.categorie + ", " +
      this.source +
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
    if (this.categorie == null) {
      result = prime * result;
    } else {
      result = prime * result + this.categorie.hashCode();
    }
    if (this.source == null) {
      result = prime * result;
    } else {
      result = prime * result + this.source.hashCode();
    }
    if (this.personne == null) {
      result = prime * result;
    } else {
      result = prime * result + this.personne.hashCode();
    }
    return result;
  }

  /**
   * Donne la valeur de hachage de l'instance sans la source.
   *
   * @return <code>int</code> La valeur du hash.
   */
  public int hashCodeWithoutSource() {
    final int prime = 31;
    int result = 1;
    if (this.categorie == null) {
      result = prime * result;
    } else {
      result = prime * result + this.categorie.hashCode();
    }
    if (this.personne == null) {
      result = prime * result;
    } else {
      result = prime * result + this.personne.hashCode();
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
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof AFonction)) {
      return false;
    }
    final AFonction other = (AFonction) obj;
    if (categorie == null) {
      if (other.categorie != null) {
        return false;
      }
    } else if (!categorie.equals(other.categorie)) {
      return false;
    }
    if (source == null) {
      if (other.source != null) {
        return false;
      }
    } else if (!source.equals(other.source)) {
      return false;
    }
    if (personne == null) {
      if (other.personne != null) {
        return false;
      }
    } else if (!personne.equals(other.personne)) {
      return false;
    }
    return true;
  }

  /**
   * Teste si un objet est égal à cette instance sans vérifier l'attribut source.
   *
   * @param obj l'instance le l'object à comparer.
   * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
   */
  public boolean equalsIgnoreSource(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof AFonction)) {
      return false;
    }
    final AFonction other = (AFonction) obj;
    if (categorie == null) {
      if (other.categorie != null) {
        return false;
      }
    } else if (!categorie.equals(other.categorie)) {
      return false;
    }
    if (personne == null) {
      if (other.personne != null) {
        return false;
      }
    } else if (!personne.equals(other.personne)) {
      return false;
    }
    return true;
  }

}
