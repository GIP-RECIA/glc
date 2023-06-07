package fr.recia.glc.db.entities.fonction;

import fr.recia.glc.db.entities.common.enums.CategorieFonction;
import fr.recia.glc.db.entities.groupe.GroupementEtablissements;
import fr.recia.glc.db.entities.personne.APersonne;
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
public class FonctionDomaineGroupementEtablissement extends AFonction {

  /**
   * Relation unidirectionnelle.
   * Domaines d'exercice.
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(
    name = "domaines_gp_etab",
    joinColumns = @JoinColumn(name = "FONCTIONDOMAINEGROUPETAB_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "TYPEDOMAINE_ID", referencedColumnName = "ID")
  )
  private Set<TypeDomaine> domaines = new HashSet<>();
  /**
   * Relation unidirectionnelle.
   * Groupement d'établissment dans lequel l'exercice des domaines est effectué.
   */
  @ManyToOne
  @JoinColumn(name = "groupe_etablissement_fk", insertable = false, updatable = false)
  private GroupementEtablissements groupeEtablissements;

  /**
   * Constructeur de l'objet FonctionDomaineGroupementEtablissement.java.
   */
  public FonctionDomaineGroupementEtablissement() {
    super();
    this.setCategorie(CategorieFonction.Domaine_Groupement);
  }

  /**
   * Constructeur de l'objet FonctionDomaineGroupementEtablissement.java.
   *
   * @param domaines             Domaines d'exercice.
   * @param groupeEtablissements Groupement d'établissment dans lequel l'exercice des domaines est effectué.
   * @param personne             Personne exerçant ces domaines.
   * @param source               Source d'alimentation gérant cette fonction.
   */
  public FonctionDomaineGroupementEtablissement(final Set<TypeDomaine> domaines,
                                                final GroupementEtablissements groupeEtablissements,
                                                final APersonne personne, final String source) {
    super(CategorieFonction.Domaine_Groupement, personne, source);
    this.domaines = domaines;
    this.groupeEtablissements = groupeEtablissements;
  }

  @Override
  public String toString() {
    return "FonctionDomaineGroupement [" +
      super.toString() + ", " +
      this.groupeEtablissements + ", " +
      this.domaines +
      "]";
  }

}
