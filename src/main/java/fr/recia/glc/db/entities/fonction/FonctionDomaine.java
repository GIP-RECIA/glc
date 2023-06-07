package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.enums.CategorieFonction;
import fr.recia.glc.db.entities.personne.APersonne;
import fr.recia.glc.db.entities.structure.AStructure;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class FonctionDomaine extends AFonction {

  /**
   * Relation unidirectionnelle.
   * Domaines d'exercice.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(
    name = "fonctions_domaines",
    joinColumns = @JoinColumn(name = "FONCTIONDOMAINE_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "TYPEDOMAINE_ID", referencedColumnName = "ID")
  )
  private Set<TypeDomaine> domaines = new HashSet<>();
  /**
   * Relation unidirectionnelle.
   * Structure d'exercice des domaines.
   */
  @ManyToOne
  @JoinColumn(name = "astructure_fk", insertable = false, updatable = false)
  private AStructure structure;

  /**
   * Constructeur de l'objet FonctionDomaine.java.
   */
  public FonctionDomaine() {
    super();
    this.setCategorie(CategorieFonction.Domaine);
  }

  /**
   * Constructeur de l'objet FonctionDomaine.java.
   *
   * @param domaines  Domaines d'exercice.
   * @param structure Structure d'exercice des domaines.
   * @param personne  Personne exerçant ce domaine.
   * @param source    Source d'alimentation gérant cette fonction.
   */
  public FonctionDomaine(final Set<TypeDomaine> domaines, final AStructure structure,
                         final APersonne personne, final String source) {
    super(CategorieFonction.Domaine, personne, source);
    this.domaines = domaines;
    this.structure = structure;
  }

  @Override
  public String toString() {
    return "FonctionDomaine [" +
      super.toString() + ", " +
      this.structure + ", " +
      this.domaines +
      "]";
  }

}
