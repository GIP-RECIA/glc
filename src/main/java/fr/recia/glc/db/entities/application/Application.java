package fr.recia.glc.db.entities.application;

import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.common.enums.CategorieApplication;
import fr.recia.glc.db.entities.groupe.Profil;
import fr.recia.glc.db.entities.groupe.RoleApplicatif;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Application extends AbstractEntity {

  /**
   * Catégorie de l'application.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I40)
  private CategorieApplication categorie;
  /**
   * Description de l'application.
   */
  private String description;
  /**
   * Identifiant unique de l'application.
   */
  @Column(unique = true, length = IntConst.I40)
  private String identifiant;
  /**
   * Nom unique de l'application, pouvant servir de login.
   */
  @Column(unique = true, length = IntConst.I100)
  private String nom;
  /**
   * Password de l'application.
   */
  private String password;

  /**
   * Relation bidirectionnelle.
   */
  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinTable(
    name = "applications_profils",
    joinColumns = @JoinColumn(name = "APPLICATION_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "PROFIL_ID", referencedColumnName = "ID")
  )
  private Set<Profil> profils = new HashSet<>();
  /**
   * Relation unidirectionnelle.
   */
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinColumn(name = "apersonne_fk")
  private APersonne proprietaire;
  /**
   * Relation bidirectionnelle.
   */
  @OneToMany(mappedBy = "proprietaire", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  private Set<RoleApplicatif> rolesApplicatifs = new HashSet<>();

  /**
   * Constructeur de l'objet Application.java.
   */
  public Application() {
    super();
  }

  /**
   * Constructeur de l'objet Application.java.
   *
   * @param identifiant Identifiant unique de l'application.
   * @param password    Password de l'application.
   * @param nom         Nom unique de l'application, pouvant servir de login.
   * @param categorie   Catégorie de l'application.
   */
  public Application(final String identifiant, final String password,
                     final String nom, final CategorieApplication categorie) {
    super();
    this.identifiant = identifiant;
    this.password = password;
    this.nom = nom;
    this.categorie = categorie;
  }

  @Override
  public String toString() {
    return "Application [" +
      super.toString() + ", " +
      this.categorie + ", " +
      this.identifiant + ", " +
      this.nom + ", " +
      this.description + ", " +
      this.password +
      "]";
  }

}
