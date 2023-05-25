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
package fr.recia.glc.db.entities.application;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Bean - Entity.
 * Object étendu d'une application.
 * Défini le plugin d'une application sous forme d'une nouvelle application.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>identifiant, password, nom, categorie, nomClass, application.</DD></DL>
 *
 * @author GIP RECIA - Gribonvald Julien
 * 5 juin 08
 */
@Entity
public class PluginDescription extends Application {

  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -3690505746457042623L;

  // Attributs
  /**
   * Nom de la classe à executer.
   */
  private String nomClass;
  /**
   * Nom du fichier de paramètres.
   */
  private String nomIni;
  /**
   * Nom du plugin.
   */
  private String nomPlugin;

  //Relations
  /**
   * Relation unidirectionnelle.
   */
  @ManyToOne
  @JoinColumn(name = "application_fk")
  private Application application;

  //Constructeurs

  /**
   * Constructeur de l'objet PluginDescription.java.
   */
  public PluginDescription() {
    super();
  }

  /**
   * Constructeur de l'objet PluginDescription.java.
   *
   * @param identifiant Identifiant unique de l'application.
   * @param password    Password de l'application.
   * @param nom         Nom unique de l'application, pouvant servir de login.
   * @param categorie   Catégorie de l'application.
   */
  public PluginDescription(final String identifiant, final String password, final String nom,
                           final CategorieApplication categorie) {
    super(identifiant, password, nom, categorie);
  }

  /**
   * Constructeur de l'objet PluginDescription.java.
   *
   * @param identifiant Identifiant unique de l'application.
   * @param password    Password de l'application.
   * @param nom         Nom unique de l'application, pouvant servir de login.
   * @param categorie   Catégorie de l'application.
   * @param nomClass    Nom de la classe à executer.
   * @param application Application référente du plugin.
   */
  public PluginDescription(final String identifiant, final String password, final String nom,
                           final CategorieApplication categorie, final String nomClass, final Application application) {
    super(identifiant, password, nom, categorie);
    this.nomClass = nomClass;
    this.application = application;
  }

  //Accesseurs

  /**
   * Getter du membre nomClass.
   *
   * @return <code>String</code> le membre nomClass
   */
  public String getNomClass() {
    return this.nomClass;
  }

  /**
   * Setter du membre nomClass.
   *
   * @param nomClass la nouvelle valeur du membre nomClass
   */
  public void setNomClass(final String nomClass) {
    this.nomClass = nomClass;
  }

  /**
   * Getter du membre nomIni.
   *
   * @return <code>String</code> le membre nomIni
   */
  public String getNomIni() {
    return this.nomIni;
  }

  /**
   * Setter du membre nomIni.
   *
   * @param nomIni la nouvelle valeur du membre nomIni
   */
  public void setNomIni(final String nomIni) {
    this.nomIni = nomIni;
  }

  /**
   * Getter du membre nomPlugin.
   *
   * @return <code>String</code> le membre nomPlugin
   */
  public String getNomPlugin() {
    return this.nomPlugin;
  }

  /**
   * Setter du membre nomPlugin.
   *
   * @param nomPlugin la nouvelle valeur du membre nomPlugin
   */
  public void setNomPlugin(final String nomPlugin) {
    this.nomPlugin = nomPlugin;
  }

  //Relations

  /**
   * Getter du membre application.
   *
   * @return <code>Application</code> le membre application
   */
  public Application getApplication() {
    return this.application;
  }

  /**
   * Setter du membre application.
   *
   * @param application la nouvelle valeur du membre application
   */
  public void setApplication(final Application application) {
    this.application = application;
  }

  /**
   * Transforme cette instance en chaine de caractères.
   *
   * @return <code>String</code> La chaine.
   * @see fr.recia.glc.db.entities.application.Application#toString()
   */
  @Override
  public String toString() {
    return "Plugin [" +
      super.toString() + ", " +
      this.nomClass + ", " +
      this.application.toString() + ", " +
      this.nomIni + ", " +
      this.nomPlugin +
      "]";
  }

  /**
   * Donne la valeur de hachage de l'instance.
   *
   * @return <code>int</code> La valeur du hash.
   * @see fr.recia.glc.db.entities.application.Application#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    if (this.application == null) {
      result = prime * result;
    } else {
      result = prime * result + this.application.hashCode();
    }
    if (this.nomClass == null) {
      result = prime * result;
    } else {
      result = prime * result + this.nomClass.hashCode();
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
    if (!(obj instanceof PluginDescription)) {
      return false;
    }
    final PluginDescription other = (PluginDescription) obj;
    if (this.application == null) {
      if (other.application != null) {
        return false;
      }
    } else if (!this.application.equals(other.application)) {
      return false;
    }
    if (this.nomClass == null) {
      if (other.nomClass != null) {
        return false;
      }
    } else if (!this.nomClass.equals(other.nomClass)) {
      return false;
    }
    return true;
  }

}
