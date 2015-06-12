/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
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
        panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Intitulé :"));
        intitule = new JTextField(30);
        panel.add(intitule);
        
        //Champ calendrier
        panel.add(new JLabel("Date :"));
        JPanel panelDate = new JPanel();
        panelDate.setLayout(new GridLayout(1,3));
        //panelDate.add(new JLabel("JOUR :"));
     
        for (int i = 0;i<31;i++) {
            jours[i]=i+1;
        }
      
        liste2 = new JComboBox(jours);  
        panelDate.add(liste2);
         
        //panelDate.add(new JLabel("MOIS :"));


        liste1 = new JComboBox(Mois.values()); 
        panelDate.add(liste1);
         
        //panelDate.add(new JLabel("ANNEE :"));
        //
        int j = 2000;
        for (int i = 0;i<50;i++) {
        
        items[i]=j;
        j=j+1;
        }

        liste = new JComboBox(items);
        panelDate.add(liste);
        
        panel.add(panelDate);
         
          

        //Liste de participants
       
            
        String[] colonnes = {"Nom","Prenom"};
            model.setColumnIdentifiers(colonnes);
            tableContact=new JTable(model);
        JPanel panelPar = new JPanel();
        panelPar.setLayout(new GridLayout(3,1));
        panelPar.add(new JLabel("Liste de participants"));
        panelPar.add(tableContact.getTableHeader());
        panelPar.add(tableContact);
        panel.add(panelPar);
        gestParti = new JButton("Gestion des participants");
        panel.add(gestParti);
         
         
         //Button Annuler/Valider + Gerer participants
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new BorderLayout());
        
            valider = new JButton("Valider");
            panelBouton.add(valider,BorderLayout.WEST);
            annuler = new JButton("Annuler");
            panelBouton.add(annuler,BorderLayout.EAST);
            
        JPanel mainPanel = new JPanel();   
        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(panel,BorderLayout.CENTER);
        mainPanel.add(panelBouton,BorderLayout.SOUTH);
        this.add(mainPanel);
  
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
