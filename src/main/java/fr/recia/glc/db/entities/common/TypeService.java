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
package fr.recia.glc.db.entities.common;

import fr.recia.glc.db.utils.IntConst;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Set;

/**
 * Bean - Entity. Description d'un service.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>libelleService.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 10 juin 08
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"libelleService", "source"})
})
public class TypeService extends AbstractSimpleEntity {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = 5076461650358855266L;

  // Attributs
  /**
   * Nom du service.
   */
  @Column(nullable = false)
  private String libelleService;
  /**
   * Source de l'alimentation.
   */
  @Column(nullable = false, length = IntConst.ISOURCE)
  private String source;

  // Constructeur

  /**
   * Constructeur de l'objet TypeService.java.
   */
  public TypeService() {
    super();
  }

  /**
   * Constructeur de l'objet TypeService.java.
   *
   * @param libelleService Nom du service.
   * @param source         Source d'alimentation.
   */
  public TypeService(final String libelleService, final String source) {
    super();
    this.libelleService = libelleService;
    this.source = source;
  }

  // Accesseurs

  /**
   * Getter du membre libelleService.
   *
   * @return <code>String</code> le membre libelleService.
   */
  public String getLibelleService() {
    return this.libelleService;
  }

  /**
   * Setter du membre libelleService.
   *
   * @param libelleService la nouvelle valeur du membre libelleService.
   */
  public void setLibelleService(final String libelleService) {
    this.libelleService = libelleService;
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  public void setSource(final String source) {
    this.source = source;
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
    result = prime * result + ((libelleService == null) ? 0 : libelleService.hashCode());
    result = prime * result + ((source == null) ? 0 : source.hashCode());
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
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TypeService other = (TypeService) obj;
    if (libelleService == null) {
      if (other.libelleService != null)
        return false;
    } else if (!libelleService.equals(other.libelleService))
      return false;
    if (source == null) {
      if (other.source != null)
        return false;
    } else if (!source.equals(other.source))
      return false;
    return true;
  }

  /**
   * Teste equals mais en ignorant la source d'alimentation
   *
   * @param obj l'instance le l'object à comparer.
   * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
   */
  public boolean equalsIgnoreSource(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TypeService other = (TypeService) obj;
    if (libelleService == null) {
      if (other.libelleService != null)
        return false;
    } else if (!libelleService.equals(other.libelleService))
      return false;
    if (source == null) {
      if (other.source != null)
        return false;
    } else if (!source.equals(other.source))
      return false;
    return true;
  }

  /**
   * Test if a set of TypeService contains a typeService without checking the source.
   *
   * @param collection
   * @param object
   * @return boolean
   */
  public static boolean containsWithoutSource(final Set<TypeService> collection, final TypeService object) {
    for (TypeService item : collection) {
      if (item.equalsIgnoreSource(object))
        return true;
    }
    return false;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
   */
  @Override
  public String toString() {
    return "TypeService [" +
      super.toString() + ", " +
      this.libelleService + ", " +
      this.source +
      "]";
  }

}
