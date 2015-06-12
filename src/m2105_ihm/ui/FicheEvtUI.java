/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;

/**
 *
 * @author IUT2
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    private PlanningUI          planning;
    private JTextField          intitule;
    private int                 jour;
    private int                 annee;
    private Mois                moois;
    private Integer []          items = new Integer[115];
    private JComboBox           liste;        
    private JComboBox           liste1;
    private Integer []          jours = new Integer[31];
    private JComboBox           liste2;
    private JButton             valider;
    private JButton             annuler;
    private JButton             gestParti;
    private DefaultTableModel   model = new DefaultTableModel();
    private JTable              tableContact;
    private JPanel              panel;
    
    
    
    /**
     * Creates new form CarnetUI
     */
    public FicheEvtUI(PlanningUI planning) {
        super();
        
        this.planning = planning;
        
        initUIComponents();
        initListeners();
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        
        /** Projet : à compléter **/
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                planning.setEventModified(true);
            }
        });
        annuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                planning.setEventModified(false);
            }
        });
        gestParti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                planning.afficherFenetreParti();
            }
        });
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
        
        /** Projet : à compléter **/
        //Champ intitule
        intitule = new JTextField(30);
        this.add(intitule);
        
        //Champ calendrier
        this.add(new JLabel("ANNEE :"));
        //
        int j = 2000;
        for (int i = 0;i<50;i++) {
        
        items[i]=j;
        j=j+1;
        }

         liste = new JComboBox(items);
         this.add(liste);
         
          this.add(new JLabel("MOIS :"));


         liste1 = new JComboBox(Mois.values()); 
         this.add(liste1);
         
         this.add(new JLabel("JOUR :"));
     
        for (int i = 0;i<31;i++) {
            jours[i]=i+1;
        }
      
         liste2 = new JComboBox(jours);  
         this.add(liste2);
        
        //Liste de participants
        this.add(new JLabel("Liste de participants"));
            
        String[] colonnes = {"Nom","Prenom"};
            model.setColumnIdentifiers(colonnes);
            tableContact=new JTable(model);
        this.add(tableContact.getTableHeader());
        this.add(tableContact);
         
         
         //Button Annuler/Valider + Gerer participants
            valider = new JButton("Valider");
            this.add(valider);
            annuler = new JButton("Annuler");
            this.add(annuler);
            gestParti = new JButton("Gestion des participants");
            this.add(gestParti);
  
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        /** Projet : à compléter **/
        intitule.setText(event.getIntitule());
        
        jour = event.getDateJour();
        moois = event.getDateMois();
        annee = event.getDateAnnee();
        liste.setSelectedItem(jour);
        liste1.setSelectedItem(moois);
        liste2.setSelectedItem(annee);
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        if (event == null) { return false; }
        
        /** Projet : à compléter **/
        event.setIntitule(intitule.getText());
        event.setDate((Integer)liste2.getSelectedItem(),(Mois)liste1.getSelectedItem(), (Integer)liste.getSelectedItem());
        
        return true;
    }
}
