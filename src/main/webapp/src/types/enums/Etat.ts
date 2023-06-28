import type { enumValues } from "@/types/enumValuesType";

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

const getEtat = (etat: string): enumValues => {
  switch (etat) {
    case Etat.Invalide.toString():
      return { i18n: "invalid", color: "yellow" };
    case Etat.Valide.toString():
      return { i18n: "valid", color: "green" };
    case Etat.Bloque.toString():
      return { i18n: "locked", color: "orange" };
    case Etat.Delete.toString():
      return { i18n: "deleted", color: "gray" };
    case Etat.Incertain.toString():
      return { i18n: "uncertain", color: "yellow" };
    case Etat.Incertain_Export_Delete.toString():
      return { i18n: "uncertainExportDelete", color: "red" };
    case Etat.Incertain_Export_Modify.toString():
      return { i18n: "uncertainExportModify", color: "yellow" };
    case Etat.Incertain_Export_Add.toString():
      return { i18n: "uncertainExportAdd", color: "yellow" };
    default:
      throw new Error(`Non-existent etat in switch: ${etat}`);
  }
};

export { getEtat };
