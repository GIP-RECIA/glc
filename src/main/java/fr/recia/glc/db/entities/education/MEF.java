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
package fr.recia.glc.db.entities.education;

import fr.recia.glc.db.commons.IntConst;
import fr.recia.glc.db.entities.common.AbstractSimpleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean - Entity.
 * Description d'un MEF par rapport aux données fournies.
 * <DL><DT><b>Champs obligatoires :</b></DT>
 * <DD>code, libelle (pour l'enseignant)
 * niveauFormation, specialité (en plus pour l'élève), anneeScolaire, type.</DD></DL>
 * @author GIP RECIA - Gribonvald Julien
 * 9 juin 08
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "source" }) })
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MEF extends AbstractSimpleEntity {

	/** Identifiant de sérialisation. */
	private static final long serialVersionUID = 6020021484188421533L;

	//Attributs
	/** Année scolaire en cours du MEF.
	 * Année à la rentrée de septembre.*/
	@Temporal(TemporalType.DATE)
	private Date anneeScolaire;
	/** code MEF, N_MEF => ENTEleveMEF. */
	@Column(nullable = false, length = IntConst.I128)
	private String code;
	/** code MEF_STAT_11. */
	@Column(length = IntConst.I40)
	private String codeStat11;
	/** Source d'alimentation du MEF. */
	@Column(nullable = false, length = IntConst.ISOURCE)
	private String source;
	/** libelle MEF, N_MEF (10 caractères) si MEF national,
	 * texte libre si MEF académique => ENTEleveLibelleMEF. */
	private String libelle;
	/** Niveau de formation, N_MEF_STAT_4. */
	private String niveauFormation;
	/** Filière, N_MEF_STAT_5. */
	private String filiere;
	/** Niveau de formation de diplôme, N_NIVEAU_FORMATION_DIPLOME. **/
	private String niveauFormationDiplome;
	/** Spécialité de formation, N_FORMATION_DIPLOME. */
	private String specialite;
	/** Type du MEF, N_TYPE_MEF académique ou national. */
	@Enumerated(EnumType.STRING)
	@Column(length = IntConst.I15)
	private MEFType type;

	//Relations
	/** Relation bidirectionnelle.
	 * Code MEF de rattachement (normalement code MEF national)
	 * , si cas d'un MEF académique. */
	@ManyToOne
	@JoinColumn(name = "mef_principal_fk")
	private MEF rattachement;

	/** Relation bidirectionnelle. */
	@OneToMany(mappedBy = "rattachement")
	private Set<MEF> sousMEFs = new HashSet<>();

	//Constructeurs
	/**
	 * Constructeur de l'objet MEF.java.
	 */
	public MEF() {
		super();
	}

	/**
	 * Constructeur de l'objet MEF.java avec les champs utiles pour un enseignant.
	 * @param code Code MEF N_MEF.
	 * @param libelle Libelle MEF.
	 * @param source Source de création
	 */
	public MEF(final String code, final String libelle, final String source) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.source = source;
	}

	/**
	 * Constructeurs de MEF avec les champs obligatoire pour définir le MEF d'un Eleve.
	 * @param code Code MEF N_MEF.
	 * @param libelle Libellé MEF.
	 * @param source Source de création.
	 * @param niveauFormation Niveau de formation N_MEF_STAT_4.
	 * @param filiere Filère N_MEF_STAT5.
	 */
	public MEF(final String code, final String libelle, final String source, final String niveauFormation,
			final String filiere) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.niveauFormation = niveauFormation;
		this.filiere = filiere;
	}

	//Accesseurs
	/**
	 * Getter du membre code.
	 * @return <code>String</code> le membre code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Setter du membre code.
	 * @param code la nouvelle valeur du membre code.
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Getter of attribute source.
	 * @return <code>String</code> the attribute source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter of attribute source.
	 * @param source <code>String</code> the attribute source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * Getter du membre libelle.
	 * @return <code>String</code> le membre libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Setter du membre libelle.
	 * @param libelle la nouvelle valeur du membre libelle
	 */
	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Getter of member codeStat11.
	 * @return <code>String</code> the attribute codeStat11
	 */
	public String getCodeStat11() {
		return codeStat11;
	}

	/**
	 * Setter of attribute codeStat11.
	 * @param codeStat11 the attribute codeStat11 to set
	 */
	public void setCodeStat11(final String codeStat11) {
		this.codeStat11 = codeStat11;
	}

	/**
	 * Getter du membre niveauFormation.
	 * @return <code>String</code> le membre niveauFormation.
	 */
	public String getNiveauFormation() {
		return this.niveauFormation;
	}

	/**
	 * Setter du membre niveauFormation.
	 * @param niveauFormation la nouvelle valeur du membre niveauFormation.
	 */
	public void setNiveauFormation(final String niveauFormation) {
		this.niveauFormation = niveauFormation;
	}

	/**
	 * Getter du membre filiere.
	 * @return <code>String</code> le membre filiere.
	 */
	public String getFiliere() {
		return this.filiere;
	}

	/**
	 * Setter du membre filiere.
	 * @param filiere la nouvelle valeur du membre filiere.
	 */
	public void setFiliere(final String filiere) {
		this.filiere = filiere;
	}

	/**
	 * Getter du membre niveauFormationDiplome.
	 * @return <code>String</code> le membre niveauFormationDiplome
	 */
	public String getNiveauFormationDiplome() {
		return this.niveauFormationDiplome;
	}

	/**
	 * Setter du membre niveauFormationDiplome.
	 * @param niveauFormationDiplome la nouvelle valeur du membre niveauFormationDiplome
	 */
	public void setNiveauFormationDiplome(final String niveauFormationDiplome) {
		this.niveauFormationDiplome = niveauFormationDiplome;
	}

	/**
	 * Getter du membre specialite.
	 * @return <code>String</code> le membre specialite.
	 */
	public String getSpecialite() {
		return this.specialite;
	}

	/**
	 * Setter du membre specialite.
	 * @param specialite la nouvelle valeur du membre specialite.
	 */
	public void setSpecialite(final String specialite) {
		this.specialite = specialite;
	}

	/**
	 * Getter du membre type.
	 * @return <code>MEFType</code> le membre type
	 */
	public MEFType getType() {
		return this.type;
	}

	/**
	 * Setter du membre type.
	 * @param type la nouvelle valeur du membre type
	 */
	public void setType(final MEFType type) {
		this.type = type;
	}

	/**
	 * Getter du membre anneeScolaire.
	 * @return <code>Date</code> le membre anneeScolaire.
	 */
	public Date getAnneeScolaire() {
		return this.anneeScolaire;
	}

	/**
	 * Setter du membre anneeScolaire.
	 * @param anneeScolaire la nouvelle valeur du membre anneeScolaire.
	 */
	public void setAnneeScolaire(final Date anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	//Relations
	/**
	 * Getter du membre rattachement.
	 * @return <code>MEF</code> le membre rattachement
	 */
	public MEF getRattachement() {
		return this.rattachement;
	}

	/**
	 * Setter du membre rattachement.
	 * @param rattachement la nouvelle valeur du membre rattachement
	 */
	public void setRattachement(final MEF rattachement) {
		this.rattachement = rattachement;
	}

	/**
	 * Getter du membre sousMEFs.
	 * @return <code>Set< MEF ></code> le membre sousMEFs
	 */
	public Set<MEF> getSousMEFs() {
		return this.sousMEFs;
	}

	/**
	 * Setter du membre sousMEFs.
	 * @param sousMEFs la nouvelle valeur du membre sousMEFs
	 */
	public void setSousMEFs(final Set<MEF> sousMEFs) {
		this.sousMEFs = sousMEFs;
	}

	/**
	 * Transforme cette instance en chaine de caractères.
	 * @return <code>String</code> La chaine.
	 * @see fr.recia.glc.db.entities.common.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MEF [");
		sb.append(super.toString());
		sb.append(", ");
		sb.append(this.code);
		sb.append(", ");
		sb.append(this.libelle);
		sb.append(", ");
		sb.append(this.filiere);
		sb.append(", ");
		sb.append(this.niveauFormation);
		sb.append(", ");
		sb.append(this.niveauFormationDiplome);
		sb.append(", ");
		sb.append(this.specialite);
		if (this.rattachement != null) {
			sb.append(", MEF principal [");
			sb.append(this.rattachement.code);
			sb.append("]");
		}
		sb.append(", ");
		sb.append(this.anneeScolaire);
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
		if (code == null) {
			result = prime * result;
		} else {
			result = prime * result + code.hashCode();
		}
		if (libelle == null) {
			result = prime * result;
		} else {
			result = prime * result + libelle.hashCode();
		}
		return result;
	}

	/**
	 * Teste si un objet est égal uniquement sur le code à cette instance.
	 * @param obj l'instance le l'object à comparer.
	 * @return <code>boolean</code> : vrai si l'instance est identique, faux sinon
	 */
	public boolean equalsOnCode(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MEF)) {
			return false;
		}
		final MEF other = (MEF) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		return true;
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
		if (!(obj instanceof MEF)) {
			return false;
		}
		final MEF other = (MEF) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (libelle == null) {
			if (other.libelle != null) {
				return false;
			}
		} else if (!libelle.equals(other.libelle)) {
			return false;
		}
		return true;
	}

}
