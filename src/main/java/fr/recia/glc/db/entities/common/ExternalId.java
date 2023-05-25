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

import fr.recia.glc.db.commons.IntConst;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Set;

/**
 * Bean - Entity. Décrit un identifiant fourni à un destinataire.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>id, destinataire.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 18 août 2020.
 */
@Embeddable
public class ExternalId {

  // Attributs
  /**
   * Identifiant externe.
   */
  @Column(nullable = false)
  private String id;
  /**
   * Destinataire de l'identifiant.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I20, nullable = false)
  private ExternalIdSource destinataire;

  // Constructeurs

  /**
   * Constructeur de l'objet ExternalId.java.
   */
  public ExternalId() {
    super();
  }

  /**
   * Constructeur de l'objet Mail.java.
   *
   * @param id           Identifiant.
   * @param destinataire Le destinataire utilisant l'identifiant.
   */
  public ExternalId(final String id, final ExternalIdSource destinataire) {
    super();
    this.id = id;
    this.destinataire = destinataire;
  }

  // Accesseurs

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the destinataire
   */
  public ExternalIdSource getDestinataire() {
    return destinataire;
  }

  /**
   * @param destinataire the destinataire to set
   */
  public void setDestinataire(ExternalIdSource destinataire) {
    this.destinataire = destinataire;
  }

  @Override
  public String toString() {
    return "ExternalId [id=" +
      this.id + ", destinataire=" +
      this.destinataire +
      "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((destinataire == null) ? 0 : destinataire.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    ExternalId other = (ExternalId) obj;
    if (destinataire != other.destinataire)
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public static boolean isExternalIdForDestIsDefined(final Set<ExternalId> collection, final ExternalIdSource dest) {
    for (ExternalId item : collection) {
      if (item.destinataire.equals(dest))
        return true;
    }
    return false;
  }

}
