package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.enums.CategorieStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Entreprise extends AStructure {

  /**
   * Relation unidirectionnelle.
   * Liste des établissements en partenariat avec l'entreprise.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinTable(
    name = "partenariat",
    joinColumns = @JoinColumn(name = "ENTREPRISE_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ETABLISSEMENT_ID", referencedColumnName = "ID")
  )
  private Set<Etablissement> partenariat = new HashSet<>();

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

  @Override
  public String toString() {
    return "Entreprise [" +
      super.toString() + ", " +
      this.partenariat +
      "]";
  }

}
