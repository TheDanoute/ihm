/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import m2105_ihm.nf.*;
import m2105_ihm.ui.CarnetUI;
//import javax.swing.JTable;
//import javax.swing.Defa

/**
 *
 * @author IUT2
 */
public class FicheContactUI extends JPanel {

    private CarnetUI         carnet;
    
    private JTextField       champNom;
    private JTextField       champPrenom;
    private JRadioButton     soir,nuit,weekend;
    private ButtonGroup      bGroup;
    private Integer []       items = new Integer[115];
    private JComboBox        liste;     
    private JComboBox        liste1;
    private Integer []       jours = new Integer[31];
    private JComboBox        liste2;
    private HashMap <Hobby,JCheckBox> hobbies = new HashMap<Hobby,JCheckBox>();
    private String           emel;
    private JTextField       champmel;
    private JTextField       champtel;
    private JComboBox        regc;
    private int              jour;
    private int              annee;
    private Mois             moois;
    private JButton          bAnnuler = new JButton("Annuler");
    private JButton          bValider = new JButton("Valider");
    
    
    /**
     * Formulaire pour saisir les informations relatives à un contact
     */
    public FicheContactUI(CarnetUI carnet) {
        super();
        
        this.carnet      = carnet;
              
        initUIComponents();
        initListeners();
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {      
        /** Pour l'exemple **/
        
        /*
         * Ajoute dans l'IHM un champ pour la saisie/Affichage du nom
         */
        // NOM
        this.add(new JLabel("Nom :"));
         
        champNom = new JTextField(30);
        this.add(champNom);
        
        /*
         * Ajoute un label associé au champ pour la saisie du nom du contact
         */
        
        // PRENOM
        
        this.add(new JLabel("Prenom :"));
        champPrenom = new JTextField(30);
        this.add(champPrenom);
        
        /** TP 1 : à compléter **/
        
        
        // DISPO
        
        
        this.add(new JLabel("Dispo :"));
        
        
        soir = new JRadioButton("soir");
        nuit = new JRadioButton("nuit");
        weekend = new JRadioButton("Week-End");
        bGroup = new ButtonGroup();
        
        bGroup.add(soir);
        bGroup.add(nuit);
        bGroup.add(weekend);
        add(soir);
        add(nuit);
        add(weekend);
        
        // DATE DE NAISSANCE
        
       // String [] colonnes = {"Annee","Mois","Jour"};
      //  DefaultTableModel model = new DefaultTableModel();
        
         this.add(new JLabel("ANNEE :"));
        
       
        //{ "1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002" };
       int j = 1900;
        for (int i = 0;i<115;i++) {
        
        items[i]=j;
        j=j+1;
        }

         liste = new JComboBox(items);
        // JList multiple;
        // multiple = new JList(items);
         add(liste);
         
          this.add(new JLabel("MOIS :"));


         liste1 = new JComboBox(Mois.values()); 
         add(liste1);
         
         this.add(new JLabel("JOUR :"));
     
        for (int i = 0;i<31;i++) {
            jours[i]=i+1;
        }
      
         liste2 = new JComboBox(jours);  
         add(liste2);
         
         // HOBBY
         
          this.add(new JLabel("HOBBYS:"));
         
  
          
          for(Hobby toto : Hobby.values()) {
              
              
              JCheckBox c =  new JCheckBox(toto.toString()); 
              hobbies.put(toto, c);
              add(c);
          }
          
         //JCheckBox c1,c2,c3,c4;
         
         //c1 = new JCheckBox("CINEMA");
         //c2 = new JCheckBox("LECTURE");
         //c3 = new JCheckBox("MUSIQUE");
         //c4 = new JCheckBox("SPORT");
         
        //add(c1); add(c2); add(c3); add(c4);
         
        // EMAIL
        
         this.add(new JLabel("EMAIL :"));

         
         champmel = new JTextField(50);
         add(champmel);
         
        // TELEPHONE
         
        this.add(new JLabel("TEL :"));
        champtel = new JTextField(10);
        add(champtel);
        
        // REGION
        this.add(new JLabel("REGION :"));
        Region [] region = Region.values();
        regc = new JComboBox(region);
        add(regc);
        
        this.add(bAnnuler);
        this.add(bValider);
    }
    
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
        if (contact == null) { return false; }
        
        /** TP 1 : à compléter **/
        
        /*
         * Nom du contact
         */
        champNom.setText(contact.getNom());        
        champPrenom.setText(contact.getPrenom());
        champmel.setText(contact.getEmail());
        champtel.setText(contact.getNumeroTelephone());
        //DISPO 
        
        if (contact.getDisponibilite() == DispoSortie.NUIT) {
            nuit.setSelected(true);
        }
        
      if (contact.getDisponibilite() == DispoSortie.SOIR) {
            soir.setSelected(true);
        }
                
      if (contact.getDisponibilite() == DispoSortie.WEEK_END) {
            weekend.setSelected(true);
        }       
      for(Hobby h : Hobby.values()) {
          JCheckBox c = hobbies.get(h);    
          c.setSelected(false);
      } 
      
      for(Hobby h : contact.getHobbies()) {
          JCheckBox c = hobbies.get(h);
          c.setSelected(true);
      }
            
      
     // CHECK BOX 4 if comme lotre
   /*   Hobby [] hobbys = contact.getHobbies();
      for (int i=0; i<=hobbys.length;i++) {
          Hobby h = hobbys[i];
          if (h == Hobby.CINEMA) { for (int j = 0; j<hobbies.size();j++) { if (hobbies.get(j)==Hobby.CINEMA) { 
      }*/
      
      
             
      
      //DATE NAISSANCE
      jour = contact.getDateNaissanceJour();
      moois = contact.getDateNaissanceMois();
      annee = contact.getDateNaissanceAnnee();
      

      liste.setSelectedItem(jour);
      liste1.setSelectedItem(moois);
      liste2.setSelectedItem(annee);
      
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche contact
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public boolean getValues(Contact contact) {
        if (contact == null) { return false; }
        
        /** TP 1 : à compléter **/
          
        /*
         * Affecte une valeur à l'attribut Nom avec le nom saisi dans le champ
         * correspondant de l'IHM
         */
        contact.setNom(champNom.getText());        
        contact.setPrenom(champPrenom.getText());
        
        if (soir.isSelected()) { contact.setDisponibilite(DispoSortie.SOIR); }
        if (nuit.isSelected()) { contact.setDisponibilite(DispoSortie.NUIT); }
        if (weekend.isSelected()) { contact.setDisponibilite(DispoSortie.WEEK_END); }
        
        contact.setDateNaissance((Integer)liste2.getSelectedItem(), (Mois)liste1.getSelectedItem(), (Integer)liste.getSelectedItem());
        
        contact.setEmail(champmel.getText());
        
        contact.setNumeroTelephone(champtel.getText());
        
        contact.setRegion((Region)regc.getSelectedItem());
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        /** TP 5 : à compléter **/ 
        bAnnuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(false);
            }
        });
        bValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(true);
            }
        });
    }
        
        
}