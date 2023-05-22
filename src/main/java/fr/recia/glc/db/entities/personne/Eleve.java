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
import fr.recia.glc.db.entities.common.CleJointure;
import fr.recia.glc.db.entities.education.MEF;
import fr.recia.glc.db.entities.education.MappingEleveEnseignement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * APersonne étendu en Eleve.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD> uid, login, password, cn, sn, displayName, givenName, CategoriePersonne.Eleve,
 * cleJointure, anneeScolaire, etat, sexe, majeur, statut, mef, enseignements,
 * classes(groupes dans APersonne), dateNaissance, structureRattachement, sconetId.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 11 juin 08
 */
@Entity
@Table(indexes = {
  @Index(name = "sconetId", columnList = "sconetId"),
  @Index(name = "INE", columnList = "INE")
})
public class Eleve extends APersonne {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = -1232117960539403689L;

	//Attributs
	/** Identifiant sconet.*/
	@Column(length = IntConst.I15)
	private String sconetId;
	/** Identifiant National. */
	@Column(length = IntConst.I11)
	private String INE;
	/** Vrai si l'élève est majeur, Faux sinon. */
	@Column(nullable = false, columnDefinition = "BIT not null DEFAULT false")
	private boolean majeur;
	/** Vrai si l'élève est majeur anticipé, Faux sinon, null si non renseigné. */
	@Column(columnDefinition = "BIT")
	private Boolean majeurAnticipe;
	/** Régime de l'élève. */
	@Column(length = IntConst.I128)
	private String regime;
	/** statut de l'élève. */
	@Column(length = IntConst.I128)
	private String statut;
	/** Vrai si l'élève utilise les transport scolaire, Faux sinon, null si non renseigné. */
	@Column(columnDefinition = "BIT")
	private Boolean transport;
	/** Vrai si l'élève est boursier, Faux sinon, null si non renseigné. */
	@Column(columnDefinition = "BIT")
	private Boolean boursier;

	//Relations
	/** Relation unidirectionnelle.
	 * MEF associé à l'élève. */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "mef_fk")
	private MEF mef;

	/** Relation unidirectionnelle.
	 * Liste des enseignements suivi par l'élève.*/
	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "eleves_enseignements", joinColumns = @JoinColumn(name = "ELEVE_ID", referencedColumnName = "ID"))
	private Set<MappingEleveEnseignement> enseignements = new HashSet<>();

	//Constructeurs
	/**
	 * Constructeur de l'objet Eleve.java.
	 */
	public Eleve() {
		super();
		this.setCategorie(CategoriePersonne.Eleve);
	}

	/**
	 * Constructeur de l'objet Eleve.java.
	 * @param anneeScolaire Année scolaire de validité de l'individu. Année à la rentrée de septembre.
	 * @param cleJointure Clé de jointure, identifiant unique fourni par les différentes sources,
	 * mais unique uniquement pour le périmètre de la source.
	 * @param cn Nom canonique de la personne : NOM + Prénom usuels.
	 * @param givenName Prénom usuel.
	 * @param sn Nom d'usage.
	 */
	public Eleve(final Date anneeScolaire, final CleJointure cleJointure,
               final String cn, final String givenName, final String sn) {
		super(anneeScolaire, CategoriePersonne.Eleve, cleJointure, cn, givenName, sn);
	}

	//Accesseurs
	/**
	 * Getter du membre sconetId.
	 * @return <code>String</code> le membre sconetId.
	 */
	public String getSconetId() {
		return sconetId;
	}

	/**
	 * Setter du membre sconetId.
	 * @param sconetId la nouvelle valeur du membre sconetId.
	 */
	public void setSconetId(final String sconetId) {
		this.sconetId = sconetId;
	}

	/**
	 * Getter du membre INE.
	 * @return the iNE
	 */
	public String getINE() {
		return INE;
	}

	/**
	 * Setter du membre INE.
	 * @param iNE la nouvelle valeur du membre INE
	 */
	public void setINE(final String iNE) {
		INE = iNE;
	}

	/**
	 * Getter du membre majeur.
	 * @return <code>boolean</code> le membre majeur.
	 */
	public boolean getMajeur() {
		return this.majeur;
	}

	/**
	 * Setter du membre majeur.
	 * @param majeur la nouvelle valeur du membre majeur.
	 */
	public void setMajeur(final boolean majeur) {
		this.majeur = majeur;
	}

	/**
	 * Getter du membre majeurAnticipe.
	 * @return <code>Boolean</code> le membre majeurAnticipe.
	 */
	public Boolean getMajeurAnticipe() {
		return this.majeurAnticipe;
	}

	/**
	 * Setter du membre majeurAnticipe.
	 * @param majeurAnticipe la nouvelle valeur du membre majeurAnticipe.
	 */
	public void setMajeurAnticipe(final Boolean majeurAnticipe) {
		this.majeurAnticipe = majeurAnticipe;
	}

	/**
	 * Getter du membre regime.
	 * @return <code>String</code> le membre regime.
	 */
	public String getRegime() {
		return this.regime;
	}

	/**
	 * Setter du membre regime.
	 * @param regime la nouvelle valeur du membre regime.
	 */
	public void setRegime(final String regime) {
		this.regime = regime;
	}

	/**
	 * Getter du membre statut.
	 * @return <code>String</code> le membre statut.
	 */
	public String getStatut() {
		return this.statut;
	}

	/**
	 * Setter du membre statut.
	 * @param statut la nouvelle valeur du membre statut.
	 */
	public void setStatut(final String statut) {
		this.statut = statut;
	}

	/**
	 * Getter du membre transport.
	 * @return <code>Boolean</code> le membre transport.
	 */
	public Boolean getTransport() {
		return this.transport;
	}

	/**
	 * Setter du membre transport.
	 * @param transport la nouvelle valeur du membre transport.
	 */
	public void setTransport(final Boolean transport) {
		this.transport = transport;
	}

	/**
	 * Getter du membre boursier.
	 * @return <code>Boolean</code> le membre boursier.
	 */
	public Boolean getBoursier() {
		return this.boursier;
	}

	/**
	 * Setter du membre boursier.
	 * @param boursier la nouvelle valeur du membre boursier.
	 */
	public void setBoursier(final Boolean boursier) {
		this.boursier = boursier;
	}

	//Relations
	/**
	 * Getter du membre mef.
	 * @return <code>MEF</code> le membre mef.
	 */
	public MEF getMef() {
		return this.mef;
	}

	/**
	 * Setter du membre mef.
	 * @param mef la nouvelle valeur du membre mef.
	 */
	public void setMef(final MEF mef) {
		this.mef = mef;
	}

	/**
	 * Getter du membre enseignements.
	 * @return <code>Set< MappingEleveEnseignement ></code> le membre enseignements.
	 */
	public Set<MappingEleveEnseignement> getEnseignements() {
		return this.enseignements;
	}

	/**
	 * Setter du membre enseignements.
	 * @param enseignements la nouvelle valeur du membre enseignements.
	 */
	public void setEnseignements(final Set<MappingEleveEnseignement> enseignements) {
		this.enseignements = enseignements;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.personne.APersonne#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Eleve [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.sconetId);
		sb.append(", ");
		sb.append(this.INE);
		sb.append(", ");
		sb.append(this.boursier);
		sb.append(", ");
		sb.append(this.majeur);
		sb.append(", ");
		sb.append(this.majeurAnticipe);
		sb.append(", ");
		sb.append(this.regime);
		sb.append(", ");
		sb.append(this.statut);
		sb.append(", ");
		sb.append(this.transport);
		sb.append(", ");
		sb.append(this.mef);
		sb.append(", ");
		sb.append(this.enseignements);
		sb.append("]");
		return sb.toString();
	}

}
