package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.utils.IntConst;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"APERSONNE_LOGIN"}),
  @UniqueConstraint(columnNames = {"APERSONNE_ALIAS"})
})
@Getter
@Setter
public class Login extends AbstractEntity {

  /**
   * Nom de login.
   */
  @Column(unique = true, nullable = false, length = IntConst.I100)
  private String nom;

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

}
