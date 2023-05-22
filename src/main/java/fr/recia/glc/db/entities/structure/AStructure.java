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
package fr.recia.glc.db.entities.structure;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.application.Application;
import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.common.Adresse;
import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.Etat;
import fr.recia.glc.db.entities.common.Mail;
import fr.recia.glc.db.entities.common.Telephone;
import fr.recia.glc.db.entities.gestion.Incertain;
import fr.recia.glc.db.entities.groupe.Profil;
import fr.recia.glc.db.entities.personne.APersonne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Objet abstrait définissant une structure.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>nom, cleJointure, siren.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 10 juin 08
 *
 * Modification 05/05/2009 :
 * - modification de la méthode toString afin d'éviter les stackoverflow, à voir si ça tient correctement.
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "astructure", uniqueConstraints = { @UniqueConstraint(columnNames = { "cle", "source" }) }, indexes = { @Index(name = "AStructCleJointure", columnList = "cle,source") })
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class AStructure extends AbstractEntity {

	//Attributs
	/** Etat de l'entité valide, bloqué ou supprimé. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I25)
	private Etat etat;
	/** Etat d'alimentation de l'entité Non_alimenté, Bascule, Alimenté. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I15)
	private EtatAlim etatAlim;
	/** Année scolaire de validité de l'individu.
	 * Année à la rentrée de septembre.*/
	@Temporal(TemporalType.DATE)
	private Date anneeScolaire;
	/** Adresse de la structure. */
	@Embedded
	private Adresse adresse;
	/** Categorie de la structure. Etablissement, Entreprise ...*/
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I20)
	private CategorieStructure categorie;
	/** Clé de jointure, identifiant unique généré par les différentes sources,
	 * mais unique uniquement pour le périmètre de la source.*/
	@Embedded
	@Column(unique = true, nullable = false)
	private CleJointure cleJointure;
	/** Adresse email de la structure. */
	// private String mail;
	/** Nom obligatoire de la stucture. */
	@Column(nullable = false)
	private String nom;
	/** Nom court. */
	private String nomCourt;
	/** Numéro de SIRET/SIREN de la structure. */
	@Column(unique = true, length = IntConst.I20)
	private String siren;
	/** Adresse du site web de la structure. */
	private String siteWeb;
	/** Nom du layout owner de l'établissement permettant de définir le skin. */
	// private String modeleLogin;
	/** Url du logo. */
	private String logo;

	//Relations
	/** Relation unidirectionnelle.
	 * Personne étant le contact de la structure. */
	@Fetch(FetchMode.JOIN)
	@OneToOne(fetch = FetchType.LAZY)
	private APersonne contact;
	/** Relation unidirectionnelle.
	 * Personne étant le responsable de la structure. */
	@Fetch(FetchMode.JOIN)
	@OneToOne(fetch = FetchType.LAZY)
	private APersonne responsable;
	/** Relation bidirectionnelle.
	 * Type de la structure (Lycée Agricole , L.P., ... connus de MENESR) */
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "type_structure_fk")
	private TypeStructure type;
	/** Relation bidirectionnelle.
	 * Liste des personnes rattachées à cette structure. */
	/* @OneToMany(mappedBy = "structRattachement", fetch = FetchType.LAZY)
	private Set<APersonne> personnesRattachement = new HashSet<>();	*/
	/** Relation bidirectionnelle.
	 * Liste des groupes de profils de cette structure. */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "proprietaire")
	private Set<Profil> profils = new HashSet<>();
	/** Relation unidirectionnelle.
	 * Liste des numéros de téléphones de la structure. */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "astructure_fk")
	private Set<Telephone> telephones = new HashSet<>();
	/** Relation unidirectionnelle.
	 * Liste des adresses mail de la structure. */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "astructure_fk")
	private Set<Mail> mails = new HashSet<>();
	/** Relation bidirectionnelle.
	 * Liste des centres d'intérêts définis par la structure.	 */
	/* @OneToMany(cascade = {CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "structure")
	private Set<CentreInteret> centresInterets = new HashSet<>();*/
	/** Relation unidirectionnelle.
	 * Utilisé  par Bordeaux pour définir les listes d'applications
	 * auxquelles les établissements sont abonnés.*/
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "astructures_applications", joinColumns = @JoinColumn(name = "STRUCTURE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID", referencedColumnName = "ID"))
	private Set<Application> abonnement = new HashSet<>();

	/** Relation bidirectionnelle.
	 * Liste des attributs incertains. */
	@OneToMany(mappedBy = "incertainStruct", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<Incertain> incertains = new HashSet<>();

	//Constructeurs
	/**
	 * Constructeur de l'objet AStructure.java.
	 */
	public AStructure() {
		super();
	}

	/**
	 * Constructeur de l'objet AStructure.java.
	 * @param categorie Categorie de la structure. Etablissement, Entreprise ...
	 * @param nom Nom unique de la stucture.
	 * @param siren Numéro de SIRET/SIREN unique de la structure.
	 * @param cleJointure Clé de jointure unique de la structure.
	 */
	public AStructure(final CategorieStructure categorie, final String nom, final String siren, final CleJointure cleJointure) {
		super();
		this.categorie = categorie;
		this.nom = nom;
		this.siren = siren;
		this.cleJointure = cleJointure;
		this.etat = Etat.Valide;
	}

	//Accesseurs
	/**
	 * Getter du membre etat.
	 * @return <code>Etat</code> le membre etat.
	 */
	public Etat getEtat() {
		return this.etat;
	}

	/**
	 * Setter du membre etat.
	 * @param etat la nouvelle valeur du membre etat.
	 */
	public void setEtat(final Etat etat) {
		this.etat = etat;
	}

	/**
	 * Getter du membre etatAlim.
	 * @return <code>EtatAlim</code> le membre etatAlim.
	 */
	public EtatAlim getEtatAlim() {
		return etatAlim;
	}

	/**
	 * Setter du membre etatAlim.
	 * @param etatAlim la nouvelle valeur du membre etatAlim.
	 */
	public void setEtatAlim(final EtatAlim etatAlim) {
		this.etatAlim = etatAlim;
	}

	/**
	 * Getter du membre anneeScolaire.
	 * @return <code>Date</code> le membre anneeScolaire.
	 */
	public Date getAnneeScolaire() {
		return anneeScolaire;
	}

	/**
	 * Setter du membre anneeScolaire.
	 * @param anneeScolaire la nouvelle valeur du membre anneeScolaire.
	 */
	public void setAnneeScolaire(final Date anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	/**
	 * Getter du membre adresse.
	 * @return <code>Adresse</code> le membre adresse.
	 */
	public Adresse getAdresse() {
		return this.adresse;
	}

	/**
	 * Setter du membre adresse.
	 * @param adresse la nouvelle valeur du membre adresse.
	 */
	public void setAdresse(final Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * Getter du membre categorie.
	 * @return <code>CategorieStructure</code> le membre categorie.
	 */
	public CategorieStructure getCategorie() {
		return this.categorie;
	}

	/**
	 * Setter du membre categorie.
	 * @param categorie la nouvelle valeur du membre categorie.
	 */
	public void setCategorie(final CategorieStructure categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter du membre cleJointure.
	 * @return <code>String</code> le membre cleJointure.
	 */
	public CleJointure getCleJointure() {
		return this.cleJointure;
	}

	/**
	 * Setter du membre cleJointure.
	 * @param cleJointure la nouvelle valeur du membre cleJointure.
	 */
	public void setCleJointure(final CleJointure cleJointure) {
		this.cleJointure = cleJointure;
	}

	/**
	 * Getter du membre mail.
	 * @return <code>String</code> le membre mail.
	 */
	/* public String getMail() {
		return this.mail;
	}*/

	/**
	 * Setter du membre mail.
	 * @param mail la nouvelle valeur du membre mail.
	 */
	/* public void setMail(final String mail) {
		this.mail = mail;
	}*/

	/**
	 * Getter du membre nom.
	 * @return <code>String</code> le membre nom.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return the mails
	 */
	public Set<Mail> getMails() {
		return mails;
	}

	/**
	 * @param mails the mails to set
	 */
	public void setMails(final Set<Mail> mails) {
		this.mails = mails;
	}

	/**
	 * Setter du membre nom.
	 * @param nom la nouvelle valeur du membre nom.
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * Getter of attribute nomCourt.
	 * @return <code>String</code> the attribute nomCourt
	 */
	public String getNomCourt() {
		return nomCourt;
	}

	/**
	 * Setter of attribute nomCourt.
	 * @param nomCourt <code>String</code> the attribute nomCourt to set
	 */
	public void setNomCourt(final String nomCourt) {
		this.nomCourt = nomCourt;
	}

	/**
	 * Getter du membre siren.
	 * @return <code>String</code> le membre siren.
	 */
	public String getSiren() {
		return this.siren;
	}

	/**
	 * Setter du membre siren.
	 * @param siren la nouvelle valeur du membre siren.
	 */
	public void setSiren(final String siren) {
		this.siren = siren;
	}

	/**
	 * Getter du membre siteWeb.
	 * @return <code>String</code> le membre siteWeb.
	 */
	public String getSiteWeb() {
		return this.siteWeb;
	}

	/**
	 * Setter du membre siteWeb.
	 * @param siteWeb la nouvelle valeur du membre siteWeb.
	 */
	public void setSiteWeb(final String siteWeb) {
		this.siteWeb = siteWeb;
	}

	/**
	 * Getter du membre modeleLogin.
	 * @return <code>String</code> le membre modeleLogin.
	 */
	/* public String getModeleLogin() {
		return modeleLogin;
	}*/

	/**
	 * Setter du membre modeleLogin.
	 * @param modeleLogin la nouvelle valeur du membre modeleLogin.
	 */
	/* public void setModeleLogin(final String modeleLogin) {
		this.modeleLogin = modeleLogin;
	}*/

	/**
	 * Getter du membre logo.
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Setter du membre logo.
	 * @param logo the logo to set
	 */
	public void setLogo(final String logo) {
		this.logo = logo;
	}

	//Relations
	/**
	 * Getter du membre contact.
	 * @return <code>APersonne</code> le membre contact.
	 */
	public APersonne getContact() {
		return this.contact;
	}

	/**
	 * Setter du membre contact.
	 * @param contact la nouvelle valeur du membre contact.
	 */
	public void setContact(final APersonne contact) {
		this.contact = contact;
	}

	/**
	 * Getter du membre responsable.
	 * @return <code>APersonne</code> le membre responsable.
	 */
	public APersonne getResponsable() {
		return this.responsable;
	}

	/**
	 * Setter du membre responsable.
	 * @param responsable la nouvelle valeur du membre responsable.
	 */
	public void setResponsable(final APersonne responsable) {
		this.responsable = responsable;
	}

	/**
	 * Getter du membre type.
	 * @return <code>TypeStructure</code> le membre type.
	 */
	public TypeStructure getType() {
		return this.type;
	}

	/**
	 * Setter du membre type.
	 * @param type la nouvelle valeur du membre type.
	 */
	public void setType(final TypeStructure type) {
		this.type = type;
	}

	/**
	 * Getter du membre personnesRattachement.
	 * @return <code>Set< APersonne ></code> le membre personnesRattachement.
	 */
	/* public Set<APersonne> getPersonnesRattachement() {
		return this.personnesRattachement;
	}*/

	/**
	 * Setter du membre personnesRattachement.
	 * @param personnesRattachement la nouvelle valeur du membre personnesRattachement.
	 */
	/* public void setPersonnesRattachement(final Set<APersonne> personnesRattachement) {
		this.personnesRattachement = personnesRattachement;
	}*/

	/**
	 * Getter du membre profils.
	 * @return <code>Set< Profil ></code> le membre profils.
	 */
	public Set<Profil> getProfils() {
		return this.profils;
	}

	/**
	 * Setter du membre profils.
	 * @param profils la nouvelle valeur du membre profils.
	 */
	public void setProfils(final Set<Profil> profils) {
		this.profils = profils;
	}

	/**
	 * Getter du membre telephones.
	 * @return <code>Set< Telephone ></code> le membre telephones.
	 */
	public Set<Telephone> getTelephones() {
		return this.telephones;
	}

	/**
	 * Setter du membre telephones.
	 * @param telephones la nouvelle valeur du membre telephones.
	 */
	public void setTelephones(final Set<Telephone> telephones) {
		this.telephones = telephones;
	}

	/**
	 * Getter du membre centresInterets.
	 * @return <code>Set< CentreInteret ></code> le membre centresInterets.
	 */
	/* public Set<CentreInteret> getCentresInterets() {
		return this.centresInterets;
	}*/

	/**
	 * Setter du membre centresInterets.
	 * @param centresInterets la nouvelle valeur du membre centresInterets.
	 */
	/* public void setCentresInterets(final Set<CentreInteret> centresInterets) {
		this.centresInterets = centresInterets;
	}*/

	/**
	 * Getter du membre abonnement.
	 * @return <code>Set< Application ></code> le membre abonnement.
	 */
	public Set<Application> getAbonnement() {
		return this.abonnement;
	}

	/**
	 * Setter du membre abonnement.
	 * @param abonnement la nouvelle valeur du membre abonnement.
	 */
	public void setAbonnement(final Set<Application> abonnement) {
		this.abonnement = abonnement;
	}

	/**
	 * Getter du membre incertains.
	 * @return <code>Set< Incertain ></code> le membre incertains.
	 */
	public Set<Incertain> getIncertains() {
		return this.incertains;
	}

	/**
	 * Setter du membre incertains.
	 * @param incertains la nouvelle valeur du membre incertains.
	 */
	public void setIncertains(final Set<Incertain> incertains) {
		this.incertains = incertains;
	}

	/**
	 * Ajout d'un incertain au membre incertains.
	 * @param incertain la nouvelle valeur du membre incertains.
	 */
	public void addIncertains(final Incertain incertain) {
		if (this.incertains == null) {
			this.incertains = new HashSet<>();
		}
		this.incertains.add(incertain);
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("AStructure [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.etat);
		sb.append(", ");
		sb.append(this.etatAlim);
		sb.append(", ");
		sb.append(this.cleJointure);
		sb.append(", ");
		sb.append(this.categorie);
		sb.append(", ");
		sb.append(this.nom);
		sb.append(", ");
		sb.append(this.siren);
		sb.append(", ");
		sb.append(this.adresse);
		if (this.contact != null) {
			sb.append(", Contact[");
			sb.append(this.contact.getId());
			sb.append(", ");
			sb.append(this.contact.getCleJointure());
			sb.append(", ");
			sb.append(this.contact.getDisplayName());
			sb.append("], ");
		}
		sb.append(this.mails);
		if (this.responsable != null) {
			sb.append(", Responsable[");
			sb.append(this.responsable.getId());
			sb.append(", ");
			sb.append(this.responsable.getCleJointure());
			sb.append(", ");
			sb.append(this.responsable.getDisplayName());
			sb.append("], ");
		}
		sb.append(this.type);
		sb.append(", ");
		sb.append(this.telephones);
		sb.append(", ");
		sb.append(this.siteWeb);
		//sb.append(", ");
		//sb.append(this.modeleLogin);
		sb.append(", ");
		sb.append(logo);
		sb.append(", ");
		sb.append(this.abonnement);
		//sb.append(", ");
		//sb.append(this.centresInterets);
		sb.append(", ");
		sb.append(this.incertains);
		//sb.append(", ");
		//sb.append(this.profils);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Donne la valeur de hachage de l'instance.
	 * @return <code>int</code> La valeur du hash.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.cleJointure == null) {
			result = prime * result;
		} else {
			result = prime * result + this.cleJointure.hashCode();
		}
		return result;
	}

	/**
	 * Teste si un objet est égal à cette instance.
	 * @param obj l'instance le l'object à comparer.
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AStructure)) {
			return false;
		}
		final AStructure other = (AStructure) obj;
		if (this.cleJointure == null) {
			if (other.cleJointure != null) {
				return false;
			}
		} else if (!this.cleJointure.equals(other.cleJointure)) {
			return false;
		}
		return true;
	}

}
