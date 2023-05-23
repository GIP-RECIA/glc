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
package fr.recia.glc.db.entities.personne;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractEntity;
import fr.recia.glc.db.entities.common.Adresse;
import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.common.Etat;
import fr.recia.glc.db.entities.common.ExternalId;
import fr.recia.glc.db.entities.common.Mail;
import fr.recia.glc.db.entities.common.Telephone;
import fr.recia.glc.db.entities.fonction.AFonction;
import fr.recia.glc.db.entities.gestion.Incertain;
import fr.recia.glc.db.entities.groupe.MappingAGroupeAPersonne;
import fr.recia.glc.db.entities.relation.AMappingRelation;
import fr.recia.glc.db.entities.structure.AStructure;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity. Objet abstrait définissant les attributs communs à toutes personnes.
 * <DL>
 * <DT><b>Champs obligatoires :</b></DT>
 * <DD>uid, login, password, cn, sn, displayName, givenName, CategoriePersonne, cleJointure, anneeScolaire, etat.</DD>
 * </DL>
 *
 * @author GIP RECIA - Gribonvald Julien 3 Juin 2008
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "apersonne", indexes = {
  @Index(name = "APersCleJointure", columnList = "cle, source"),
  @Index(name = "uid_index", columnList = "uid"),
  @Index(name = "uuid_index", columnList = "uuid")
}, uniqueConstraints = {
  @UniqueConstraint(columnNames = {"cle", "source"})
})
public abstract class APersonne extends AbstractEntity {

	// Attributs
	/** Etat de l'entité valide, bloqué ou supprimé. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I25)
	private Etat etat;
	/**
	 * Année scolaire de validité de l'individu. Année à la rentrée de septembre.
	 */
	@Temporal(TemporalType.DATE)
	private Date anneeScolaire;
	/** Catégorie de la personne : Eleve, Enseignant ... */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I40)
	private CategoriePersonne categorie;
	/** Adresse de la personne. */
	@Embedded
	private Adresse adresse;
	/** Civilité : M, Mme, Mlle. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I4)
	private Civilite civilite;
	/**
	 * Clé de jointure, identifiant unique généré par les différentes sources, mais unique uniquement pour le périmètre de la source.
	 */
	@Embedded
	@Column(unique = true, nullable = false)
	private CleJointure cleJointure;
	/** Nom canonique de la personne : NOM + Prénom usuels. */
	private String cn;
	/** Date de naissance. */
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	/** Nom et prénom accentué. */
	private String displayName;
	/** Adresse e-mail. */
	private String email;
	/** Prénom usuel. */
	private String givenName;
	/** Numéro de bureau. */
	@Column(length = IntConst.I40)
	private String numBureau;
	/** Password généré. */
	private String password;
	/** Password SambaNT. */
	private String sambaNTPassword;
	/** Nom patronymique. */
	private String patronyme;
	/** Photo de l'individu, décrit par un url. */
	private String photo;
	/** Sexe de la personne : F, M. */
	@Enumerated(EnumType.STRING)
	@Column(length = 1, columnDefinition = "CHAR")
	private Sexe sexe;
	/** Nom d'usage. */
	private String sn;
	/** Titre de la personne. */
	@Column(length = IntConst.I80)
	private String titre;
	/** Identifiant interne à l'ENT. */
	@Column(unique = true, length = IntConst.I8)
	// INDEX
	private String uid;
	/** Identifiant externe GARPersonIdentifiant. */
	@Column(unique = true, nullable = false, length = IntConst.I36)
	// INDEX
	private String uuid;
	// ajouts spécifique à la gestion des comptes
	/** Adresse e-mail personnelle. */
	private String emailPersonnel;
	/** Flag pour indiqué la préférence du mail personnel. Par défaut à false. */
	@Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
	private boolean doForward;
	/** Sauvegarde de la date de validation de la charte. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date validationCharte;
	/** Flag pour indiqué que le compte est placé en liste rouge. Par défaut à false. */
	@Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
	private boolean listeRouge;
	/**
	 * Pour forcer l'état d'un compte : ex suppression avant les 20 jours ou réactivation d'un compte supprimé (à voir).
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I25)
	private ForceEtat forceEtat;
	/** Clé EduConnect pour forcer l'association. */
	@Column(length = IntConst.I100)
	private String idEduConnect;

	// Relations
	/**
	 * Relation bidirectionnelle. Login de l'individu généré par l'ENT.
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "apersonneLogin")
	private Login login;
	/**
	 * Relation bidirectionnelle. Alias remplaçant le login, personnalisé par l'individu.
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "apersonneAlias")
	private Login alias;
	/**
	 * Relation bidirectionnelle. Liste des anciens alias de l'individu qui ne sont plus utilisés.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apersonneOldAlias")
	private Collection<Login> oldAlias;

	/**
	 * Relation bidirectionnelle. Liste des centre d'intérêts par rapport à l'établissment pour la personne.
	 */
  /* @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinTable(name = "apersonnes_centres_interets", joinColumns = @JoinColumn(name = "APERSONNE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CENTRE_INTERET_ID", referencedColumnName = "ID"))
  private Set<CentreInteret> centresInterets = new HashSet<>();*/

	/**
	 * Relation bidirectionnel Liste des fonctions d'une personnes, uniquement les fonctions dans une structure, pas les fonctions de groupes.
	 * Profils, classes, groupes, role applicatif
	 */
	@OneToMany(mappedBy = "personne", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<AFonction> fonctions = new HashSet<AFonction>();
	/** Relation bidirectionnelle. */
  /* @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinTable(name = "apersonnes_agroupes", joinColumns = @JoinColumn(name = "APERSONNE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AGROUPEOFAPERS_ID", referencedColumnName = "ID"))
  private Set<AGroupeOfAPersonne> groupes = new HashSet<>();*/

  /* @CollectionOfElements(fetch = FetchType.LAZY)
  @JoinTable(name = "apersonnes_agroupes", joinColumns = @JoinColumn(name = "APERSONNE_ID", referencedColumnName = "ID"))*/
	@OneToMany(mappedBy = "pk.personne", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<MappingAGroupeAPersonne> groupes = new HashSet<>();

	/**
	 * Relation avec type primitif. Liste des prénoms.
	 */
	@ElementCollection
	@Column(length = 70)
	private Set<String> prenoms = new HashSet<>();
	/**
	 * Relation unidirectionnel. Structure à laquelle la personne est rattachée administrativement.
	 */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "structure_rattachement_fk")
	private AStructure structRattachement;
	/** Relation unidirectionnelle. */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "apersonne_fk")
	private Set<Telephone> telephones = new HashSet<>();
	/**
	 * Relation unidirectionnelle. Liste des adresses mails autres.
	 */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "apersonne_fk")
	private Set<Mail> autresMails = new HashSet<>();

	/**
	 * Relation bidirectionnelle. Liste des attributs incertains.
	 */
	@OneToMany(mappedBy = "incertainPers", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<Incertain> incertains = new HashSet<>();

	/** Liste des structures dans lesquelles la personne intervient. */
	/** Relation bidirectionnelle. */
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "apersonnes_astructures", joinColumns = @JoinColumn(name = "APERSONNE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ASTRUCTURE_ID", referencedColumnName = "ID"))
	private Set<AStructure> listeStructures = new HashSet<>();

  /* @CollectionOfElements(fetch = FetchType.EAGER)
  private Set<AMappingRelation> relationsTo = new HashSet<>();
  @CollectionOfElements(fetch = FetchType.EAGER)
  private Set<AMappingRelation> relationsFrom = new HashSet<>();*/

	/**
	 * Relation bidirectionnelle. Lien des relations des personnes, initiateur de la relation.
	 */
	@OneToMany(mappedBy = "pk.personne1", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<AMappingRelation> relationsTo = new HashSet<>();

	/**
	 * Relation bidirectionnelle. Lien des relations des personnes, partie non initateur de la relation.
	 */
	@OneToMany(mappedBy = "pk.personne2", fetch = FetchType.LAZY)
	private Set<AMappingRelation> relationsFrom = new HashSet<>();

	/**
	 * Relation avec type primitif. Liste des UAI où le compte ENT est répliqué sur le réseau local.
	 */
	//@CollectionOfElements private Set<String> structuresCompteReplique = new HashSet<>();
	@ElementCollection
	@CollectionTable(name = "externalId", joinColumns = @JoinColumn(name = "APersonne_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id" }),
			@UniqueConstraint(name = "UK_externalid_userid_destinataire", columnNames = { "APersonne_id", "destinataire" }) })
	private Set<ExternalId> externalIds = new HashSet<>();

	// Constructeurs
	/**
	 * Constructeur de l'objet APersonne.java.
	 */
	public APersonne() {
		super();
		this.forceEtat = ForceEtat.NONE;
	}

	/**
	 * Constructeur de l'objet APersonne.java.
	 *
	 * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
	 * @param categorie     Catégorie de la personne : Eleve, Enseignant ...
	 * @param cleJointure   Clé de jointure, identifiant unique fourni par les différentes sources, mais unique uniquement pour le périmètre de la
	 *                      source.
	 * @param cn            Nom canonique de la personne : NOM + Prénom usuels.
	 * @param givenName     Prénom usuel.
	 * @param sn            Nom d'usage.
	 */
	public APersonne(final Date anneeScolaire, final CategoriePersonne categorie, final CleJointure cleJointure,
                   final String cn, final String givenName, final String sn) {
		super();
		this.anneeScolaire = anneeScolaire;
		this.categorie = categorie;
		this.cleJointure = cleJointure;
		this.cn = cn;
		this.givenName = givenName;
		this.sn = sn;
		this.etat = Etat.Valide;
		this.forceEtat = ForceEtat.NONE;
	}

	/**
	 * Setter automatique du membre dateModification lors de la modification de l'objet. On désactive la fonction automatique pour le delete des
	 * personne. Cela est à gérer dans la partie métier.
	 */
	@Override
	@PreUpdate
	public void preUpdateOps() {
		if (!Etat.Delete.equals(this.getEtat())) {
			this.getDateModification().setTime(Calendar.getInstance().getTimeInMillis());
		}
	}

	// Accesseurs
	/**
	 * Getter du membre etat.
	 *
	 * @return <code>Etat</code> le membre etat
	 */
	public Etat getEtat() {
		return this.etat;
	}

	/**
	 * Setter du membre etat.
	 *
	 * @param etat la nouvelle valeur du membre etat
	 */
	public void setEtat(final Etat etat) {
		this.etat = etat;
	}

	/**
	 * Getter du membre anneeScolaire.
	 *
	 * @return <code>Date</code> le membre anneeScolaire
	 */
	public Date getAnneeScolaire() {
		return this.anneeScolaire;
	}

	/**
	 * Setter du membre anneeScolaire.
	 *
	 * @param anneeScolaire la nouvelle valeur du membre anneeScolaire
	 */
	public void setAnneeScolaire(final Date anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	/**
	 * Getter du membre categorie.
	 *
	 * @return <code>CategoriePersonne</code> le membre categorie
	 */
	public CategoriePersonne getCategorie() {
		return this.categorie;
	}

	/**
	 * Setter du membre categorie.
	 *
	 * @param categorie la nouvelle valeur du membre categorie
	 */
	public void setCategorie(final CategoriePersonne categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter du membre adresse.
	 *
	 * @return <code>Adresse</code> le membre adresse
	 */
	public Adresse getAdresse() {
		return this.adresse;
	}

	/**
	 * Setter du membre adresse.
	 *
	 * @param adresse la nouvelle valeur du membre adresse
	 */
	public void setAdresse(final Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * Getter du membre alias.
	 *
	 * @return <code>Login</code> le membre alias
	 */
	public Login getAlias() {
		return this.alias;
	}

	/**
	 * Setter du membre alias.
	 *
	 * @param alias la nouvelle valeur du membre alias
	 */
	public void setAlias(final Login alias) {
		this.alias = alias;
	}

	/**
	 * Getter du membre oldAlias.
	 *
	 * @return <code>Collection< Login ></code> le membre oldAlias.
	 */
	public Collection<Login> getOldAlias() {
		return oldAlias;
	}

	/**
	 * Setter du membre oldAlias.
	 *
	 * @param oldAlias la nouvelle valeur du membre oldAlias.
	 */
	public void setOldAlias(final Collection<Login> oldAlias) {
		this.oldAlias = oldAlias;
	}

	/**
	 * Getter du membre civilite.
	 *
	 * @return <code>Civilite</code> le membre civilite
	 */
	public Civilite getCivilite() {
		return this.civilite;
	}

	/**
	 * Setter du membre civilite.
	 *
	 * @param civilite la nouvelle valeur du membre civilite
	 */
	public void setCivilite(final Civilite civilite) {
		this.civilite = civilite;
	}

	/**
	 * Getter du membre cleJointure.
	 *
	 * @return <code>CleJointure</code> le membre cleJointure
	 */
	public CleJointure getCleJointure() {
		return this.cleJointure;
	}

	/**
	 * Setter du membre cleJointure.
	 *
	 * @param cleJointure la nouvelle valeur du membre cleJointure
	 */
	public void setCleJointure(final CleJointure cleJointure) {
		this.cleJointure = cleJointure;
	}

	/**
	 * Getter du membre cn.
	 *
	 * @return <code>String</code> le membre cn
	 */
	public String getCn() {
		return this.cn;
	}

	/**
	 * Setter du membre cn.
	 *
	 * @param cn la nouvelle valeur du membre cn
	 */
	public void setCn(final String cn) {
		this.cn = cn;
	}

	/**
	 * Getter du membre dateNaissance.
	 *
	 * @return <code>Date</code> le membre dateNaissance
	 */
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	/**
	 * Setter du membre dateNaissance.
	 *
	 * @param dateNaissance la nouvelle valeur du membre dateNaissance
	 */
	public void setDateNaissance(final Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Getter du membre displayName.
	 *
	 * @return <code>String</code> le membre displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Setter du membre displayName.
	 *
	 * @param displayName la nouvelle valeur du membre displayName
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Getter du membre email.
	 *
	 * @return <code>String</code> le membre email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setter du membre email.
	 *
	 * @param email la nouvelle valeur du membre email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Getter du membre emailPersonnel.
	 *
	 * @return <code>String</code> le membre emailPersonnel.
	 */
	public String getEmailPersonnel() {
		return this.emailPersonnel;
	}

	/**
	 * Setter du membre emailPersonnel.
	 *
	 * @param emailPersonnel la nouvelle valeur du membre emailPersonnel.
	 */
	public void setEmailPersonnel(final String emailPersonnel) {
		this.emailPersonnel = emailPersonnel;
	}

	/**
	 * Getter du membre givenName.
	 *
	 * @return <code>String</code> le membre givenName
	 */
	public String getGivenName() {
		return this.givenName;
	}

	/**
	 * Setter du membre givenName.
	 *
	 * @param givenName la nouvelle valeur du membre givenName
	 */
	public void setGivenName(final String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Getter du membre login.
	 *
	 * @return <code>Login</code> le membre login
	 */
	public Login getLogin() {
		return this.login;
	}

	/**
	 * Setter du membre login.
	 *
	 * @param login la nouvelle valeur du membre login
	 */
	public void setLogin(final Login login) {
		this.login = login;
	}

	/**
	 * Getter du membre numBureau.
	 *
	 * @return <code>String</code> le membre numBureau
	 */
	public String getNumBureau() {
		return this.numBureau;
	}

	/**
	 * Setter du membre numBureau.
	 *
	 * @param numBureau la nouvelle valeur du membre numBureau
	 */
	public void setNumBureau(final String numBureau) {
		this.numBureau = numBureau;
	}

	/**
	 * Getter du membre password.
	 *
	 * @return <code>String</code> le membre password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Setter du membre password.
	 *
	 * @param password la nouvelle valeur du membre password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Getter of attribute sambaNTPassword.
	 *
	 * @return <code>String</code> the attribute sambaNTPassword
	 */
	public String getSambaNTPassword() {
		return sambaNTPassword;
	}

	/**
	 * Setter of attribute sambaNTPassword.
	 *
	 * @param sambaNTPassword <code>String</code> the attribute sambaNTPassword to set
	 */
	public void setSambaNTPassword(final String sambaNTPassword) {
		this.sambaNTPassword = sambaNTPassword;
	}

	/**
	 * Getter du membre patronyme.
	 *
	 * @return <code>String</code> le membre patronyme
	 */
	public String getPatronyme() {
		return this.patronyme;
	}

	/**
	 * Setter du membre patronyme.
	 *
	 * @param patronyme la nouvelle valeur du membre patronyme
	 */
	public void setPatronyme(final String patronyme) {
		this.patronyme = patronyme;
	}

	/**
	 * Getter du membre photo.
	 *
	 * @return <code>String</code> le membre photo
	 */
	public String getPhoto() {
		return this.photo;
	}

	/**
	 * Setter du membre photo.
	 *
	 * @param photo la nouvelle valeur du membre photo
	 */
	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	/**
	 * Getter du membre sexe.
	 *
	 * @return <code>Sexe</code> le membre sexe
	 */
	public Sexe getSexe() {
		return this.sexe;
	}

	/**
	 * Setter du membre sexe.
	 *
	 * @param sexe la nouvelle valeur du membre sexe
	 */
	public void setSexe(final Sexe sexe) {
		this.sexe = sexe;
	}

	/**
	 * Getter du membre sn.
	 *
	 * @return <code>String</code> le membre sn
	 */
	public String getSn() {
		return this.sn;
	}

	/**
	 * Setter du membre sn.
	 *
	 * @param sn la nouvelle valeur du membre sn
	 */
	public void setSn(final String sn) {
		this.sn = sn;
	}

	/**
	 * Getter du membre titre.
	 *
	 * @return <code>String</code> le membre titre
	 */
	public String getTitre() {
		return this.titre;
	}

	/**
	 * Setter du membre titre.
	 *
	 * @param titre la nouvelle valeur du membre titre
	 */
	public void setTitre(final String titre) {
		this.titre = titre;
	}

	/**
	 * Getter du membre uid.
	 *
	 * @return <code>String</code> le membre uid
	 */
	public String getUid() {
		return this.uid;
	}

	/**
	 * Setter du membre uid.
	 *
	 * @param uid la nouvelle valeur du membre uid
	 */
	public void setUid(final String uid) {
		this.uid = uid;
	}

	// Relations
	/**
	 * Getter du membre centresInterets.
	 *
	 * @return <code>Set< CentreInteret ></code> le membre centresInterets
	 */
	/* public Set<CentreInteret> getCentresInterets() {
    return this.centresInterets;
  }*/

	/**
	 * Setter du membre centresInterets.
	 *
	 * @param centresInterets la nouvelle valeur du membre centresInterets
	 */
  /* public void setCentresInterets(final Set<CentreInteret> centresInterets) {
    this.centresInterets = centresInterets;
  }*/

	/**
	 * Getter du membre fonctions.
	 *
	 * @return <code>Set< AFonction ></code> le membre fonctions
	 */
	public Set<AFonction> getFonctions() {
		return this.fonctions;
	}

	/**
	 * Setter du membre fonctions.
	 *
	 * @param fonctions la nouvelle valeur du membre fonctions
	 */
	public void setFonctions(final Set<AFonction> fonctions) {
		this.fonctions = fonctions;
	}

	/**
	 * Ajoute une fonction à la personne.
	 *
	 * @param fonction Le groupe d'appartenance.
	 */
	public void addFonction(final AFonction fonction) {
		this.fonctions.add(fonction);
	}

	/**
	 * Getter du membre groupes.
	 *
	 * @return <code>Set< MappingAGroupeAPersonne ></code> le membre groupes
	 */
	public Set<MappingAGroupeAPersonne> getGroupes() {
		return this.groupes;
	}

	/**
	 * Setter du membre groupes.
	 *
	 * @param groupes la nouvelle valeur du membre groupes
	 */
	public void setGroupes(final Set<MappingAGroupeAPersonne> groupes) {
		this.groupes = groupes;
	}

	/**
	 * Ajoute un groupe auquel la personne appartient.
	 *
	 * @param groupe Le groupe d'appartenance.
	 */
	public void addGroupe(final MappingAGroupeAPersonne groupe) {
		this.groupes.add(groupe);
	}

	/**
	 * @return the relations
	 */
	public Set<AMappingRelation> getRelationsTo() {
		return relationsTo;
	}

	/**
	 * @param relations the relations to set
	 */
	public void setRelationsTo(Set<AMappingRelation> relations) {
		this.relationsTo = relations;
	}

	/**
	 * Ajoute une relation à la personne
	 *
	 * @param relation La définition de la relation.
	 */
	public void addRelationTo(AMappingRelation relation) {
		this.relationsTo.add(relation);
	}

	/**
	 * @return the backRelations
	 */
	public Set<AMappingRelation> getRelationsFrom() {
		return relationsFrom;
	}

	/**
	 * @param backRelations the backRelations to set
	 */
	public void setRelationsFrom(Set<AMappingRelation> backRelations) {
		this.relationsFrom = backRelations;
	}

	/**
	 * Ajoute une relation à la personne ciblée
	 *
	 * @param relation La définition de la relation.
	 */
	public void addRelationFrom(AMappingRelation relation) {
		this.relationsFrom.add(relation);
	}

	/**
	 * Getter of member groupesEnseignements.
	 *
	 * @return <code>Set<MappingAGroupeAPersonneEnseignement></code> the attribute groupesEnseignements
	 */
	/* public Set<MappingAGroupeAPersonneEnseignement> getGroupesEnseignements() {
    return groupesEnseignements;
  }*/

	/**
	 * Setter of attribute groupesEnseignements.
	 *
	 * @param groupesEnseignements the attribute groupesEnseignements to set
	 */
	/* public void setGroupesEnseignements( final Set<MappingAGroupeAPersonneEnseignement> groupesEnseignements) {
    this.groupesEnseignements = groupesEnseignements;
  }*/

	/**
	 * Getter du membre prenoms.
	 *
	 * @return <code>Set< String ></code> le membre prenoms
	 */
	public Set<String> getPrenoms() {
		return this.prenoms;
	}

	/**
	 * Setter du membre prenoms.
	 *
	 * @param prenoms la nouvelle valeur du membre prenoms
	 */
	public void setPrenoms(final Set<String> prenoms) {
		this.prenoms = prenoms;
	}

	/**
	 * Getter du membre structRattachement.
	 *
	 * @return <code>AStructure</code> le membre structRattachement
	 */
	public AStructure getStructRattachement() {
		return this.structRattachement;
	}

	/**
	 * Setter du membre structRattachement.
	 *
	 * @param structRattachement la nouvelle valeur du membre structRattachement
	 */
	public void setStructRattachement(final AStructure structRattachement) {
		this.structRattachement = structRattachement;
	}

	/**
	 * Getter du membre telephones.
	 *
	 * @return <code>Set< Telephone ></code> le membre telephones
	 */
	public Set<Telephone> getTelephones() {
		return this.telephones;
	}

	/**
	 * Setter du membre telephones.
	 *
	 * @param telephones la nouvelle valeur du membre telephones
	 */
	public void setTelephones(final Set<Telephone> telephones) {
		this.telephones = telephones;
	}

	/**
	 * @return the autresMails
	 */
	public Set<Mail> getAutresMails() {
		return autresMails;
	}

	/**
	 * @param autresMails the autresMails to set
	 */
	public void setAutresMails(final Set<Mail> autresMails) {
		this.autresMails = autresMails;
	}

	/**
	 * Getter du membre incertains.
	 *
	 * @return <code>Set< Incertain ></code> le membre incertains.
	 */
	public Set<Incertain> getIncertains() {
		return this.incertains;
	}

	/**
	 * Setter du membre incertains.
	 *
	 * @param incertains la nouvelle valeur du membre incertains.
	 */
	public void setIncertains(final Set<Incertain> incertains) {
		this.incertains = incertains;
	}

	/**
	 * Ajout d'un incertain au membre incertains.
	 *
	 * @param incertain la nouvelle valeur du membre incertains.
	 */
	public void addIncertains(final Incertain incertain) {
		if (this.incertains == null) {
			this.incertains = new HashSet<>();
		}
		this.incertains.add(incertain);
	}

	/**
	 * Getter du membre doForward.
	 *
	 * @return <code>boolean</code> le membre doForward.
	 */
	public boolean isDoForward() {
		return doForward;
	}

	/**
	 * Setter du membre doForward.
	 *
	 * @param doForward la nouvelle valeur du membre doForward.
	 */
	public void setDoForward(final boolean doForward) {
		this.doForward = doForward;
	}

	/**
	 * Getter du membre validationCharte.
	 *
	 * @return <code>Date</code> le membre validationCharte.
	 */
	public Date getValidationCharte() {
		return validationCharte;
	}

	/**
	 * Setter du membre validationCharte.
	 *
	 * @param validationCharte la nouvelle valeur du membre validationCharte.
	 */
  /* public void setValidationCharte(final Date validationCharte) {
    this.validationCharte = validationCharte;
  }*/

	/**
	 * Getter du membre listeStructures.
	 *
	 * @return <code>Set< AStructure ></code> le membre listeStructures.
	 */
	public Set<AStructure> getListeStructures() {
		return listeStructures;
	}

	/**
	 * Setter du membre listeStructures.
	 *
	 * @param listeStructures la nouvelle valeur du membre listeStructures.
	 */
	public void setListeStructures(final Set<AStructure> listeStructures) {
		this.listeStructures = listeStructures;
	}

	/**
	 * Getter of member listeRouge.
	 *
	 * @return <code>boolean</code> the attribute listeRouge
	 */
	public boolean isListeRouge() {
		return listeRouge;
	}

	/**
	 * Setter of member listeRouge.
	 *
	 * @param listeRouge the listeRouge to set
	 */
	public void setListeRouge(final boolean listeRouge) {
		this.listeRouge = listeRouge;
	}

	/**
	 * @return the forceEtat
	 */
	public ForceEtat getForceEtat() {
		return forceEtat;
	}

	/**
	 * @param forceEtat the forceEtat to set
	 */
	public void setForceEtat(final ForceEtat forceEtat) {
		this.forceEtat = forceEtat;
	}

	/**
	 * Getter of member uuid.
	 *
	 * @return<code>String</code> the attribute uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter of member uuid.
	 *
	 * @param uuid the uuid to set
	 */
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Setter of member idEduConnect.
	 *
	 * @return the idEduConnect
	 */
	public String getIdEduConnect() {
		return idEduConnect;
	}

	/**
	 * Getter of member idEduConnect.
	 *
	 * @param idEduConnect the idEduConnect to set
	 */
	public void setIdEduConnect(final String idEduConnect) {
		this.idEduConnect = idEduConnect;
	}

	/**
	 * @return the externalIds
	 */
	public Set<ExternalId> getExternalIds() {
		return externalIds;
	}

	/**
	 * @param externalIds the externalIds to set
	 */
	public void setExternalIds(Set<ExternalId> externalIds) {
		this.externalIds = externalIds;
	}

	/**
	 * Getter of member structuresCompteReplique.
	 *
	 * @return <code>Set<String></code> the attribute structuresCompteReplique
	 */
	/* public Set<String> getStructuresCompteReplique() {
    return structuresCompteReplique;
  }*/

	/**
	 * Setter of member structuresCompteReplique.
	 *
	 * @param structuresCompteReplique the structuresCompteReplique to set
	 */
	 /* public void setStructuresCompteReplique(final Set<String> structuresCompteReplique) {
     this.structuresCompteReplique = structuresCompteReplique;
   }*/

	/**
	 * Transforme cette instance en chaine de caractères.
	 *
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
    return "APersonne [" +
      super.toString() + ", " +
      this.categorie + ", " +
      this.etat + ", " +
      this.forceEtat + ", " +
      this.cleJointure + ", " +
      this.uid + ", " +
      this.uuid + ", " +
      this.idEduConnect + ", " +
      this.login + ", " +
      this.alias + ", " +
      this.externalIds + ", " +
      this.cn + ", " +
      this.listeRouge + ", " +
      this.displayName + ", " +
      this.givenName + ", " +
      this.sn + ", " +
      this.patronyme + ", " +
      this.prenoms + ", " +
      this.password + ", " +
      this.adresse + ", " +
      this.civilite + ", " +
      this.sexe + ", " +
      this.titre + ", " +
      this.dateNaissance + ", " +
      this.email + ", " +
      this.numBureau + ", " +
      this.photo + ", " +
      // this.centresInterets + ", " +
      this.fonctions + ", " +
      this.telephones + ", " +
      this.anneeScolaire + ", " +
      this.emailPersonnel + ", " +
      this.autresMails + ", " +
      this.doForward + ", " +
      this.validationCharte + ", " +
      this.groupes + ", " +
      this.relationsTo + ", " +
      this.incertains + ", " +
      this.structRattachement +
      "]";
	}

	/**
	 * Donne la valeur de hachage de l'instance.
	 *
	 * @return <code>int</code> La valeur du hash.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (cleJointure == null) {
			result = prime * result;
		} else {
			result = prime * result + cleJointure.hashCode();
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof APersonne)) {
			return false;
		}
		final APersonne other = (APersonne) obj;
		if (cleJointure == null) {
			if (other.cleJointure != null) {
				return false;
			}
		} else if (!cleJointure.equals(other.cleJointure)) {
			return false;
		}
		return true;
	}

}
