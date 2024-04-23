package bibliotheque.mvc.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Mail;


public interface DAOSpecialExemplaire {

    public void modifierEtat(Exemplaire ex, String etat);
    public Lecteur lecteurActuel(Exemplaire ex);

    public void envoiMailLecteurActuel(Exemplaire ex,Mail m);

    public boolean enLocation(Exemplaire ex);
}