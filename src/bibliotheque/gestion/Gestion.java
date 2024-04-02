package bibliotheque.gestion;

import bibliotheque.metier.*;
import bibliotheque.utilitaires.CDFactoryBeta;
import bibliotheque.utilitaires.DVDFactoryBeta;
import bibliotheque.utilitaires.LivreFactoryBeta;
import bibliotheque.utilitaires.comparators.OuvrageComparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.choixListe;

public class Gestion {
    Scanner sc = new Scanner(System.in);
//on a ôté static pour les listes qui n'est plus nécessaire
    private List<Auteur> laut = new ArrayList<>();
    private List<Lecteur> llect = new ArrayList<>();
    private List<Ouvrage> louv= new ArrayList<>();
    private List<Exemplaire> lex = new ArrayList<>();
    private List<Rayon> lrayon= new ArrayList<>();
    private List<Location> lloc = new ArrayList<>();


    public void populate(){
        Auteur a = new Auteur("Verne","Jules","France");
        laut.add(a);

        Livre l = new Livre("Vingt mille lieues sous les mers",10, LocalDate.of(1880,1,1),1.50,"français","aventure","a125",350,TypeLivre.ROMAN,"histoire de sous-marin");
        louv.add(l);

        a.addOuvrage(l);

        a = new Auteur("Spielberg","Steven","USA");
        laut.add(a);

        DVD d = new DVD("AI",12,LocalDate.of(2000,10,1),2.50,"anglais","SF",4578l,LocalTime.of(2,0,0),(byte)2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        louv.add(d);

        a.addOuvrage(d);

         a = new Auteur("Kubrick","Stanley","GB");
        laut.add(a);

        a.addOuvrage(d);


        CD c = new CD("The Compil 2023",0,LocalDate.of(2023,1,1),2,"English","POP",1245,(byte)20,LocalTime.of(1,40,0));
        louv.add(c);

        Rayon r = new Rayon("r12","aventure");
        lrayon.add(r);

        Exemplaire e = new Exemplaire("m12","état neuf",l);
        lex.add(e);
        e.setRayon(r);


        r = new Rayon("r45","science fiction");
        lrayon.add(r);

        e = new Exemplaire("d12","griffé",d);
        lex.add(e);

        e.setRayon(r);


        Lecteur lec = new Lecteur(1,"Dupont","Jean",LocalDate.of(2000,1,4),"Mons","jean.dupont@mail.com","0458774411");
        llect.add(lec);

        Location loc = new Location(LocalDate.of(2023,2,1),LocalDate.of(2023,3,1),lec,e);
        lloc.add(loc);
        loc.setDateRestitution(LocalDate.of(2023,2,4));

        lec = new Lecteur(1,"Durant","Aline",LocalDate.of(1980,10,10),"Binche","aline.durant@mail.com","045874444");
        llect.add(lec);

        loc = new Location(LocalDate.of(2023,2,5),LocalDate.of(2023,3,5),lec,e);
        lloc.add(loc);
    }

    private void menu() {
        List options = new ArrayList<>(Arrays.asList("auteurs","ouvrages","exemplaires","rayons","lecteurs","locations","restitution","fin"));
      do{
        int choix = choixListe(options);

            switch (choix){
                case 1 :gestAuteurs(); break;
                case 2 : gestOuvrages();break;
                case 3 : gestExemplaires();break;
                case 4 : gestRayons();break;
                case 5 : gestLecteurs();break;
                case 6 : gestLocations();break;
                case 7 : gestRestitution();break;
                default:System.exit(0);
            }
        }  while (true);
    }

    private void gestRestitution() {

        //TODO lister exemplaires en location , choisir l'un d'entre eux, enregistrer sa restitution et éventuellement changer état
        System.out.println("Liste des exemplaires en location : ");
        int count = 1;
        for (Location loc : lloc) {
            System.out.println(count++ + ". Matricule: " + loc.getExemplaire().getMatricule() +
                    ", Titre: " + loc.getExemplaire().getOuvrage().getTitre() +
                    ", Loueur: " + loc.getLoueur().getNom());
        }

        System.out.println("Veuillez choisir le numéro de l'exemplaire à restituer : ");
        int choix = sc.nextInt();
        sc.nextLine();

        if (choix < 1 || choix > lloc.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Location locationARestituer = lloc.get(choix - 1);
        Exemplaire exemplaireARestituer = locationARestituer.getExemplaire();
        LocalDate dateRestitution = LocalDate.now();
        locationARestituer.setDateRestitution(dateRestitution);

        System.out.println("Restitution enregistrée pour l'exemplaire : " + exemplaireARestituer.getMatricule());
    }

    private void gestLocations() {
        /*int choix;
        //TODO ne lister que les exemplaires libres et les trier par matricule
        choix =choixListe(lex);
        if(lex.get(choix).enLocation()){
            System.out.println("exemplaire en location");
            return;
        }
        Exemplaire ex = lex.get(choix-1);
        choix=choixListe(llect);
        Lecteur lec = llect.get(choix-1);
        lloc.add(new Location(lec,ex));
        */
        List<Exemplaire> exemplairesLibres = new ArrayList<>();
        for (Exemplaire ex : lex) {
            if (!ex.enLocation()) {
                exemplairesLibres.add(ex);
            }
        }

        if (exemplairesLibres.isEmpty()) {
            System.out.println("Aucun exemplaire disponible pour la location.");
            return;
        }

        exemplairesLibres.sort(Comparator.comparing(Exemplaire::getMatricule));

        System.out.println("Exemplaires disponibles pour la location : ");
        int count = 1;
        for (Exemplaire ex : exemplairesLibres) {
            System.out.println(count++ + ". Matricule: " + ex.getMatricule() +
                    ", Etat: " + ex.getDescriptionEtat());
        }

        int choix = choixListe(exemplairesLibres);
        Exemplaire exemplaireChoisi = exemplairesLibres.get(choix - 1);

        choix = choixListe(llect);
        Lecteur lecteurChoisi = llect.get(choix - 1);

        lloc.add(new Location(lecteurChoisi, exemplaireChoisi));
    }

    private void gestLecteurs() {
        System.out.println("numéro");
        int num=sc.nextInt();
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
        Lecteur lect = new Lecteur(num,nom,prenom,dn,adr,mail,tel);
        llect.add(lect);
        System.out.println("lecteur créé");

    }

    private void gestRayons() {
        /*System.out.println("code ");
        String code=sc.next();
        System.out.println("genre ");
        String genre=sc.next();
        Rayon r = new Rayon(code,genre);
        System.out.println("rayon créé");
        lrayon.add(r);
        int  choix = choixListe(lex);
        r.addExemplaire(lex.get(choix-1));

         */
        //TODO attribuer par une boucle plusieurs exemplaires, les exemplaires sont triés par ordre de titre de l'ouvrage ,
        //  ne proposer que les exemplaires qui ne sont pas dans déjà présents dans ce rayon et qui ne sont dans aucun autre rayon


        System.out.println("code ");
        String code = sc.next();
        System.out.println("genre ");
        String genre = sc.next();
        Rayon r = new Rayon(code, genre);
        System.out.println("rayon créé");
        lrayon.add(r);

        List<Exemplaire> exemplairesDisponibles = new ArrayList<>();
        for (Exemplaire ex : lex) {
            if (ex.getRayon() == null) {
                exemplairesDisponibles.add(ex);
            }
        }

        exemplairesDisponibles.sort(Comparator.comparing(ex -> ex.getOuvrage().getTitre()));

        System.out.println("Exemplaires disponibles pour attribuer à ce rayon : ");
        int count = 1;
        for (Exemplaire ex : exemplairesDisponibles) {
            System.out.println(count++ + ". Matricule: " + ex.getMatricule() +
                    ", Titre: " + ex.getOuvrage().getTitre());
        }

        System.out.println("Choisissez les exemplaires à attribuer à ce rayon (0 pour terminer) : ");
        int choix;
        do {
            choix = sc.nextInt();
            if (choix > 0 && choix <= exemplairesDisponibles.size()) {
                Exemplaire exemplaireChoisi = exemplairesDisponibles.get(choix - 1);
                exemplaireChoisi.setRayon(r);
                r.addExemplaire(exemplaireChoisi);
                System.out.println("Exemplaire attribué à ce rayon.");
            } else if (choix != 0) {
                System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void gestExemplaires() {
        System.out.println("matricule ");
        String mat=sc.next();
        System.out.println("etat  ");
        String etat=sc.next();
        System.out.println("ouvrage ");
        int choix = choixListe(louv);
        Exemplaire ex = new Exemplaire(mat,etat,louv.get(choix-1));
        lex.add(ex);
        System.out.println("exemplaire créé");
        choix = choixListe(lrayon);
        ex.setRayon(lrayon.get(choix-1));
        //TODO attribuer un rayon ==> c'est fait  , nouveauté : les rayons sont triès par ordre de code

        lrayon.sort(Comparator.comparing(Rayon::getCodeRayon));

        System.out.println("Choisissez le rayon à attribuer à cet exemplaire : ");
        int count = 1;
        for (Rayon rayon : lrayon) {
            System.out.println(count++ + ". Code: " + rayon.getCodeRayon() + ", Genre: " + rayon.getGenre());
        }

        choix = sc.nextInt();
        if (choix > 0 && choix <= lrayon.size()) {
            ex.setRayon(lrayon.get(choix - 1));
            System.out.println("Exemplaire attribué au rayon avec succès.");
        } else {
            System.out.println("Choix invalide. L'exemplaire ne sera pas attribué à un rayon.");
        }
    }

    private void gestOuvrages() {
      /*  Ouvrage o = null;
        System.out.println("titre");
        String titre= sc.nextLine();
        System.out.println("age minimum");
        int ageMin= sc.nextInt();
        sc.skip("\n");
        System.out.println("date de parution");

        LocalDate dp= Utilitaire.lecDate();
        System.out.println("prix de location");
        double ploc = sc.nextDouble();
        sc.skip("\n");
        System.out.println("langue");
        String langue=sc.nextLine();
        System.out.println("genre");
        String genre=sc.nextLine();
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = Utilitaire.choixListe(lto);
        switch (choix){
                case 1 :
                           System.out.println("isbn ");
                           String isbn = sc.next();
                           System.out.println("pages ");
                           int nbrePages = sc.nextInt();
                           sc.skip("\n");
                           TypeLivre[] ttl = TypeLivre.values();
                           List<TypeLivre> ltl = new ArrayList<>(Arrays.asList(ttl));
                            choix = Utilitaire.choixListe(ltl);
                            TypeLivre tl = ttl[choix-1];
                           System.out.println("résumé du livre :");
                           String resume = sc.nextLine();
                           o=new Livre(titre,ageMin,dp,ploc,langue,genre,isbn,nbrePages,tl,resume);
                           ;break;
                case 2 :
                            System.out.println("code : ");
                            long code= sc.nextLong();
                            System.out.println("nombre de plages :");
                            byte nbrePlages= sc.nextByte();
                            LocalTime dureeTotale = Utilitaire.lecTime();
                            o=new CD(titre,ageMin,dp,ploc,langue,genre,code,nbrePlages,dureeTotale);
                            ;break;
                case 3 :
                            System.out.println("code : ");
                            code= sc.nextLong();
                            dureeTotale=Utilitaire.lecTime();
                            byte nbreBonus= sc.nextByte();
                            o=new DVD(titre,ageMin,dp,ploc,langue,genre,code,dureeTotale,nbreBonus);
                            System.out.println("autres langues");
                            List<String> langues = new ArrayList<>(Arrays.asList("anglais","français","italien","allemand","fin"));
                            do{
                                choix=Utilitaire.choixListe(langues);
                                if(choix==langues.size())break;
                                ((DVD)o).getAutresLangues().add(langues.get(choix-1));//TODO vérifier unicité ou utiliser set et pas de doublon avec langue d'origine
                            }while(true);
                           System.out.println("sous-titres");
                            do{
                             choix=Utilitaire.choixListe(langues);
                             if(choix==langues.size())break;
                             ((DVD)o).getSousTitres().add(langues.get(choix-1));//TODO vérifier unicité ou utiliser set
                             }while(true);
                            ;break;
            }*/



        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = choixListe(lto);
        Ouvrage o = null;

     switch(choix) {
            case 1 : o = new LivreFactoryBeta().create();break;
            case 2 : o = new CDFactoryBeta().create();break;
            case 3 : o = new DVDFactoryBeta().create();break;
        }
       /* List<OuvrageFactory> lof = new ArrayList<>(Arrays.asList(new LivreFactory(),new CDFactory(),new DVDFactory()));
        o = lof.get(choix-1).create();*/
        louv.add(o);
        System.out.println("ouvrage créé");
         choix = choixListe(laut);
        o.addAuteur(laut.get(choix-1));
        //TODO attribuer auteurs par boucle, les auteur sont triés par ordre de nom et prénom,
        // ne pas proposer un auteur déjà présent dans la liste des auteurs de cet ouvrage
        laut.sort(Comparator.comparing(Auteur::getNom).thenComparing(Auteur::getPrenom));

        System.out.println("Choisissez les auteurs à attribuer à cet ouvrage (0 pour terminer) : ");
        int count = 1;
        for (Auteur auteur : laut) {
            System.out.println(count++ + ". " + auteur.getNom() + " " + auteur.getPrenom());
        }

        choix = sc.nextInt();
        while (choix != 0) {
            if (choix > 0 && choix <= laut.size()) {
                Auteur auteurChoisi = laut.get(choix - 1);
                if (!o.getLauteurs().contains(auteurChoisi)) {
                    o.addAuteur(auteurChoisi);
                    System.out.println("Auteur ajouté avec succès.");
                } else {
                    System.out.println("Cet auteur est déjà associé à l'ouvrage.");
                }
            } else {
                System.out.println("Choix invalide.");
            }
            System.out.println("Entrez un autre choix d'auteur ou 0 pour terminer : ");
            choix = sc.nextInt();
        }
    }

       private void gestAuteurs() {
        /*System.out.println("nom ");
        String nom=sc.nextLine();
        System.out.println("prénom ");
        String prenom=sc.nextLine();
        System.out.println("nationalité");
        String nat=sc.nextLine();
        Auteur a  = new Auteur(nom,prenom,nat);
        laut.add(a);
        System.out.println("écrivain créé");
        int choix = choixListe(louv);
        a.addOuvrage(louv.get(choix-1));

         */
        //TODO attribuer ouvrages par boucle
        // les ouvrages sont triés par ordre de titre
        // ne pas proposer un ouvrage déjà présent dans la liste des ouvrages de cet auteur
           System.out.println("nom ");
           String nom=sc.nextLine();
           System.out.println("prénom ");
           String prenom=sc.nextLine();
           System.out.println("nationalité");
           String nat=sc.nextLine();
           Auteur a  = new Auteur(nom,prenom,nat);
           laut.add(a);
           System.out.println("écrivain créé");

           List<Ouvrage> lo2 = new ArrayList<>(louv);
           Iterator<Ouvrage> itlo2 = lo2.iterator();
           while(itlo2.hasNext()){
               if(a.getLouvrage().contains(itlo2.next())) itlo2.remove();
           }
           lo2.sort(new OuvrageComparator());
           do {
               int choix = choixListe(lo2);
               if (choix == 0) break;
               a.addOuvrage(lo2.get(choix - 1));
               System.out.println("ouvrage ajouté");
               lo2.remove(choix - 1);
           }
           while(true);
    }

    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.populate();
        g.menu();
    }

  
}


































































































































































































































































































































































































































































































































































































