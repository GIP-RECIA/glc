package fr.recia.glc.db.entities.application;

import fr.recia.glc.db.entities.common.enums.CategorieApplication;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PluginDescription extends Application {

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

  /**
   * Relation unidirectionnelle.
   */
  @ManyToOne
  @JoinColumn(name = "application_fk")
  private Application application;

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

}
