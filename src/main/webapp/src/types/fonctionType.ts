import type { Discipline } from "@/types/disciplineType";
import type { Filiere } from "@/types/filiereType";

export type Fonction = {
  "COLL-CD18": FonctionBySource;
  "EF2S-CENTRE": FonctionBySource;
  "CFA-CENTRE": FonctionBySource;
  "AC-ORLEANS-TOURS": FonctionBySource;
  "COLL-CVDL": FonctionBySource;
  "COLL-CD41": FonctionBySource;
  "LA-CENTRE": FonctionBySource;
  "AC-DEMO": FonctionBySource;
};

type FonctionBySource = {
  filiereWithoutDisciplines: Array<Filiere>;
  filiereWithDiscipline: Array<Filiere>;
  disciplinesWithoutFiliere: Array<Discipline>;
};
