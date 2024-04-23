package bibliotheque.mvc.controller;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Mail;

public interface ControllerSpecialExemplaire {

    void modifierEtat(Exemplaire ex, String etat);

   Lecteur LecteurActuel(Exemplaire ex);

    void envoiMailLecteurActuel(Exemplaire ex, Mail m);

    boolean enLocation(Exemplaire ex);
}
