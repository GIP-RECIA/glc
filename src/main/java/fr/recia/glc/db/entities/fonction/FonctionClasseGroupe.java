package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.enums.CategorieFonction;
import fr.recia.glc.db.entities.common.enums.TypeClasse;
import fr.recia.glc.db.entities.groupe.AGroupeOfFoncClasseGroupe;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class FonctionClasseGroupe extends AFonction {

  /**
   * Type énuméré du type de fonction.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = IntConst.I30)
  private TypeClasse type;

  /**
   * Relation bidirectionnelle.
   * Classe ou groupe concerné.
   */
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinColumn(name = "classe_groupe_fk")
  private AGroupeOfFoncClasseGroupe classeGroupe;

  /**
   * Constructeur de l'objet FonctionClasseGroupe.java.
   */
  public FonctionClasseGroupe() {
    super();
    this.setCategorie(CategorieFonction.Classe);
  }

  /**
   * Constructeur de l'objet FonctionClasseGroupe.java.
   *
   * @param type         Type énuméré du type de fonction.
   * @param personne     Personne ayant cette fonction.
   * @param classeGroupe Classe ou groupe concerné.
   * @param source       Source d'alimentation gérant cette fonction.
   */
  public FonctionClasseGroupe(final TypeClasse type, final APersonne personne,
                              final AGroupeOfFoncClasseGroupe classeGroupe, final String source) {
    super(CategorieFonction.Classe, personne, source);
    this.type = type;
    this.classeGroupe = classeGroupe;
  }

  @Override
  public String toString() {
    return "FonctionClasseGroupe [" +
      super.toString() + ", " +
      this.type + ", " +
      this.classeGroupe +
      "]";
  }

}
