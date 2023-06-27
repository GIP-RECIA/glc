export type Personne = {
  etat: string;
  anneeScolaire: Date;
  categorie: string;
  civilite: string;
  cn: string;
  dateNaissance: Date;
  displayName: string;
  email: string;
  givenName: string;
  numBureau: string;
  patronyme?: string;
  sn: string;
  titre: string;
  uid: string;
  uuid: string;
  emailPersonnel: string;
  listeRouge: boolean;
  forceEtat: string;
  idEduConnect: string;
  login: string;
  fonctions: Array<{
    disciplinePoste: number;
    filiere: number;
    source: string;
  }>;
};

export type SimplePersonne = {
  id: number;
  etat: string;
  categorie: string;
  source: string;
  givenName: string;
  patronyme: string;
};
