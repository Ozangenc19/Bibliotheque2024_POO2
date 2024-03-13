package biblio;

import biblio.metier.*;
import static biblio.metier.TypeOuvrage.*;
import static biblio.metier.TypeLivre.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    Scanner sc = new Scanner(System.in);

    private static List<biblio.metier.Auteur> laut = new ArrayList<>();
    private static List<biblio.metier.Lecteur> llect = new ArrayList<>();
    private static List<biblio.metier.Ouvrage> louv= new ArrayList<>();
    private static List<biblio.metier.Exemplaire> lex = new ArrayList<>();
    private static List<biblio.metier.Rayon> lrayon= new ArrayList<>();
    private static List<biblio.metier.Location> lloc = new ArrayList<>();


    public void populate(){
        biblio.metier.Auteur a = new biblio.metier.Auteur("Verne","Jules","France");
        laut.add(a);

        biblio.metier.Livre l = new biblio.metier.Livre("Vingt mille lieues sous les mers",10, LocalDate.of(1880,1,1),1.50,"français","aventure","a125",350, biblio.metier.TypeLivre.ROMAN,"histoire de sous-marin");
        louv.add(l);

        a.addOuvrage(l);

        a = new biblio.metier.Auteur("Spielberg","Steven","USA");
        laut.add(a);

        biblio.metier.DVD d = new biblio.metier.DVD("AI",12,LocalDate.of(2000,10,1),2.50,"anglais","SF",4578l,"120 min",(byte)2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        louv.add(d);

        a.addOuvrage(d);

        a = new biblio.metier.Auteur("Kubrick","Stanley","GB");
        laut.add(a);

        a.addOuvrage(d);


        biblio.metier.CD c = new biblio.metier.CD("The Compil 2023",0,LocalDate.of(2023,1,1),2,"English","POP",1245,(byte)20,"100 min");
        louv.add(c);

        biblio.metier.Rayon r = new biblio.metier.Rayon("r12","aventure");
        lrayon.add(r);

        biblio.metier.Exemplaire e = new biblio.metier.Exemplaire("m12","état neuf",l);
        lex.add(e);
        e.setRayon(r);


        r = new biblio.metier.Rayon("r45","science fiction");
        lrayon.add(r);

        e = new biblio.metier.Exemplaire("d12","griffé",d);
        lex.add(e);

        e.setRayon(r);


        biblio.metier.Lecteur lec = new biblio.metier.Lecteur("Dupont","Jean",LocalDate.of(2000,1,4),"Mons","jean.dupont@mail.com","0458774411");
        llect.add(lec);

        biblio.metier.Location loc = new biblio.metier.Location(LocalDate.of(2023,2,1),LocalDate.of(2023,3,1),lec,e);
        lloc.add(loc);
        loc.setDateRestitution(LocalDate.of(2023,2,4));

        lec = new biblio.metier.Lecteur("Durant","Aline",LocalDate.of(1980,10,10),"Binche","aline.durant@mail.com","045874444");
        llect.add(lec);

        loc = new biblio.metier.Location(LocalDate.of(2023,2,5),LocalDate.of(2023,3,5),lec,e);
        lloc.add(loc);
    }

    private void menu() {
        List options = new ArrayList<>(Arrays.asList("auteurs","ouvrages","exemplaires","rayons","lecteurs","locations","fin"));

        do{
            for(int i=0;i<options.size();i++){
                System.out.println((i+1)+"."+options.get(i));
            }

            int choix;
            do {
                System.out.println("choix :");
                choix = sc.nextInt();
                sc.skip("\n");
            } while(choix <1 || choix > options.size());
            switch (choix){
                case 1 :gestAuteurs(); break;
                case 2 : gestOuvrages();break;
                case 3 : gestExemplaires();break;
                case 4 : gestRayons();break;
                case 5 : gestLecteurs();break;
                case 6 : gestLocations();break;
                default:System.exit(0);
            }
        }  while (true);
    }

    private void gestLocations() {
//TODO lister exemplaires,lister lecteurs,créer la location avec le constructeur à deux paramètres(loueur,exemplaire)

    }

    private void gestLecteurs() {

        sc.skip("\n");
        System.out.println("nom ");
        String nom=sc.nextLine();
        System.out.println("prénom ");
        String prenom=sc.nextLine();
        System.out.println("date de naissance");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn= LocalDate.of(a,m,j);
        System.out.println("adresse");
        String adr=sc.nextLine();
        System.out.println("mail");
        String mail=sc.nextLine();
        System.out.println("tel ");
        String tel=sc.nextLine();
        biblio.metier.Lecteur lect = new biblio.metier.Lecteur(nom,prenom,dn,adr,mail,tel);
        llect.add(lect);
        System.out.println("lecteur créé");
    }

    private void gestRayons() {
        //TODO gérer rayons
    }

    private void gestExemplaires() {
        //TODO afficher les ouvrages et choisir par sa position dans la liste
        //TODO demander autres infos de l'exemplaire et le créer

    }

    private void gestOuvrages() {
        //TODO créer ouvrages

    }

    private void gestAuteurs() {
        //TODO créer auteur

    }

    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.populate();
        g.menu();
    }

}

