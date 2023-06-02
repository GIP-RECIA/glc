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
package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.utils.IntConst;
import fr.recia.glc.db.entities.common.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Bean - Entity
 * <DL><DT><b>Champ obligatoire :</b></DT>
 * <DD>nom.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 28 juil. 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"APERSONNE_LOGIN"}),
  @UniqueConstraint(columnNames = {"APERSONNE_ALIAS"})
})
public class Login extends AbstractEntity {

  /**
   * Identifiant de serialisation.
   */
  private static final long serialVersionUID = -6217138755157392704L;

  //Attributs
  /**
   * Nom de login.
   */
  @Column(unique = true, nullable = false, length = IntConst.I100)
  private String nom;

  //Relations
  /**
   * Relation bidirectionnelle.
   * Personne ayant ce login.
   */
  @OneToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "apersonne_login")
  private APersonne apersonneLogin;

  /**
   * Relation bidirectionnelle.
   * Personne ayant cet alias.
   */
  @OneToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "apersonne_alias")
  private APersonne apersonneAlias;

  /**
   * Relation bidirectionnelle.
   * Personne ayant eu ce login/alias.
   */
  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "apersonne_old_alias")
  private APersonne apersonneOldAlias;

  /**
   * Constructeur de l'objet Login.java.
   */
  public Login() {
    super();
  }

  /**
   * Constructeur de l'objet Login.java.
   *
   * @param nom Nom de login.
   */
  public Login(final String nom) {
    super();
    this.nom = nom;
  }

  // Accesseurs

  /**
   * Getter du membre nom.
   *
   * @return <code>String</code> le membre nom.
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Setter du membre nom.
   *
   * @param nom la nouvelle valeur du membre nom.
   */
  public void setNom(final String nom) {
    this.nom = nom;
  }

  /**
   * Getter du membre apersonneLogin.
   *
   * @return <code>APersonne</code> le membre apersonneLogin.
   */
  public APersonne getApersonneLogin() {
    return apersonneLogin;
  }

  /**
   * Setter du membre apersonneLogin.
   *
   * @param apersonneLogin la nouvelle valeur du membre apersonneLogin.
   */
  public void setApersonneLogin(final APersonne apersonneLogin) {
    this.apersonneLogin = apersonneLogin;
  }

  /**
   * Getter du membre apersonneAlias.
   *
   * @return <code>APersonne</code> le membre apersonneAlias.
   */
  public APersonne getApersonneAlias() {
    return apersonneAlias;
  }

  /**
   * Setter du membre apersonneAlias.
   *
   * @param apersonneAlias la nouvelle valeur du membre apersonneAlias.
   */
  public void setApersonneAlias(final APersonne apersonneAlias) {
    this.apersonneAlias = apersonneAlias;
  }

  /**
   * Getter du membre apersonneOldAlias.
   *
   * @return <code>APersonne</code> le membre apersonneOldAlias.
   */
  public APersonne getApersonneOldAlias() {
    return apersonneOldAlias;
  }

  /**
   * Setter du membre apersonneOldAlias.
   *
   * @param apersonneOldAlias la nouvelle valeur du membre apersonneOldAlias.
   */
  public void setApersonneOldAlias(final APersonne apersonneOldAlias) {
    this.apersonneOldAlias = apersonneOldAlias;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "Login [" +
      super.toString() + ", " +
      this.nom +
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
    if (this.nom == null) {
      result = prime * result;
    } else {
      result = prime * result + this.nom.hashCode();
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
    if (!(obj instanceof Login)) {
      return false;
    }
    final Login other = (Login) obj;
    if (this.nom == null) {
      if (other.nom != null) {
        return false;
      }
    } else if (!this.nom.equals(other.nom)) {
      return false;
    }
    return true;
  }

}
