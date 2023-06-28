export enum Etat {
  Invalide = "Invalide",
  Valide = "Valide",
  Bloque = "Bloque",
  Delete = "Delete",
  Incertain = "Incertain",
  Incertain_Export_Delete = "Incertain_Export_Delete",
  Incertain_Export_Modify = "Incertain_Export_Modify",
  Incertain_Export_Add = "Incertain_Export_Add",
}

type TEtat = {
  color: string;
  i18n: string;
};

const getEtat = (etat: string): TEtat => {
  switch (etat) {
    case Etat.Invalide.toString():
      return { color: "yellow", i18n: "invalid" };
    case Etat.Valide.toString():
      return { color: "green", i18n: "valid" };
    case Etat.Bloque.toString():
      return { color: "orange", i18n: "locked" };
    case Etat.Delete.toString():
      return { color: "gray", i18n: "deleted" };
    case Etat.Incertain.toString():
      return { color: "yellow", i18n: "uncertain" };
    case Etat.Incertain_Export_Delete.toString():
      return { color: "red", i18n: "uncertainExportDelete" };
    case Etat.Incertain_Export_Modify.toString():
      return { color: "yellow", i18n: "uncertainExportModify" };
    case Etat.Incertain_Export_Add.toString():
      return { color: "yellow", i18n: "uncertainExportAdd" };
    default:
      throw new Error(`Non-existent etat in switch: ${etat}`);
  }
};

export { getEtat };
