package biblio;


import biblio.metier.*;

import java.time.LocalDate;

import static biblio.metier.TypeOuvrage.*;
import static biblio.metier.TypeLivre.*;
import java.util.Scanner;

public class Gestion {
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Auteur a = new Auteur("Verne", "Jules", "France");
        Livre l = new Livre("Vingt mille lieues sous les mers", 10, LocalDate.of(1880, 1, 1), LIVRE, 1.50, "français", "aventure", "a125", 350, ROMAN, "histoire de sous-marin");
        a.getOuvrages().add(l);
        l.getLauteurs().add(a);
        Rayon r = new Rayon("r12", "aventure");
        Exemplaire e = new Exemplaire("m12", "état neuf", l);
        e.setRayon(r);
        r.getLex().add(e);
        Lecteur lec = new Lecteur("Dupont", "Jean", LocalDate.of(2000, 1, 4), "Mons", "jean.dupont@mail.com", "0458774411");
        Location loc = new Location(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1), lec, e);
        lec.getLloc().add(loc);
        e.getLloc().add(loc);
        System.out.println(a);
        System.out.println(l);
        System.out.println(r);
        System.out.println(e);
        System.out.println(lec);
        System.out.println(loc);
    }

    /*--------------------------------------------------------------------------------------------------------------------*/

    public Gestion(){
        menu_principal();

    }
    public void menu_principal() {
        String choix;
        int option_choix;



        do {
            System.out.println("Menu principal ");
            System.out.println("1. AjoutAuteur \n2. Ajout Ouvrage" + "\n3. Ajout Lecteur \n4. Ajout Rayon \n5. Ajout Exemplaire \n6. Louer \n7. Rendre \n8. Fin");
            choix = controle_saisie("Entrez votre choix : ", "[1-4]");
            option_choix = Integer.parseInt(choix);

            switch (option_choix) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    System.out.println("Fin de programme");
                    break;
            }
        } while (option_choix != 8);
    }

    public String controle_saisie(String ch, String regex) {
        String choix;

        do {
            System.out.println(ch);
            choix = sc.nextLine();

        } while (choix.matches(regex) == false);

        return choix;
    }
}

