package bibliotheque.utilitaires;

import bibliotheque.metier.DVD;
import bibliotheque.metier.Ouvrage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DVDFactory extends OuvrageFactory{
    public Ouvrage addDetail(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre) {
        System.out.println("code : ");
        long code = sc.nextLong();
        LocalTime dureeTotale = Utilitaire.lecTime();
        byte nbreBonus = sc.nextByte();
        DVD dvd = new DVD(titre, ageMin, dateParution, prixLocation, langue, genre, code, dureeTotale, nbreBonus);
        System.out.println("autres langues");
        List<String> langues = new ArrayList<>(Arrays.asList("anglais", "français", "italien", "allemand", "fin"));
        int choix;
        //TODO vérifier unicité ou utiliser set et pas de doublon avec langue d'origine
        do {
            choix = Utilitaire.choixListe(langues);
            if (choix == langues.size()) break;
            String langueChoisie = langues.get(choix - 1);
            if (!dvd.getAutresLangues().contains(langueChoisie)) {
                dvd.getAutresLangues().add(langueChoisie);
            } else {
                System.out.println("Cette langue est déjà sélectionnée.");
            }
        } while (true);
        System.out.println("sous-titres");
        //TODO vérifier unicité ou utiliser set
        do {
            choix = Utilitaire.choixListe(langues);
            if (choix == langues.size()) break;
            String langueChoisie = langues.get(choix - 1);
            if (!dvd.getSousTitres().contains(langueChoisie)) {
                dvd.getSousTitres().add(langueChoisie);
            } else {
                System.out.println("Cette langue est déjà sélectionnée.");
            }
        } while (true);
        return dvd;
    }
}
