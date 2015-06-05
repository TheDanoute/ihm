/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import m2105_ihm.nf.GroupeContacts;
import javax.swing.*;
import javax.swing.table.*;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Symbole;
import m2105_ihm.ui.CarnetUI;
//import m2105_ihm.ui.ZoneDessinUI;
/**
 *
 * @author IUT2
 */
public class FicheGroupeUI extends javax.swing.JPanel {
    /*
     * Composants graphiques constituants l'interface
     */
    private CarnetUI     carnet;
    //private ZoneDessinUI zoneDessin;
    private JTextField   champGroup;
    private JTable       tableContact;
    private JButton      effacer;
    private JList        symbole;
    private DefaultTableModel model = new DefaultTableModel();
    private JButton bValider = new JButton("Valider");
    private JButton bAnnuler = new JButton("Annuler");
    /**
     * Creates new form CarnetUI
     */
    public FicheGroupeUI(CarnetUI carnet) { 
        super();
       
        this.carnet = carnet;
        
        initUIComponents();    
        initListeners();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        
        /** TP 2 : à compléter **/
        
        this.add(new javax.swing.JButton("Hello !"));
        this.add(new javax.swing.JLabel("Fiche groupe"));
        this.add(new JLabel("Nom groupe :"));
        
        //Nom groupe
        champGroup = new JTextField(30);
        this.add(champGroup);
        
        //Table
        String [] colonnes = {"nom","prenom","numero de Telephone",};
        
        model.setColumnIdentifiers(colonnes);
        tableContact=new JTable(model);
        this.add(tableContact.getTableHeader());
        this.add(tableContact);
        
        //liste des symboles
        symbole = new JList(Symbole.values());
        this.add(symbole);
        
        this.add(bValider);
        this.add(bAnnuler);
    }
        

    /**
     * Affecte des valeurs au formulaire de fiche groupe de contacts
     * @param groupe groupe de contacts
     * @return
    */    
    public boolean setValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        /** TP 2 : à compléter **/
        //Nom groupe
        champGroup.setText(groupe.getNom());
        
        //Contact
        String[] valeursligne = new String[3];
        Contact[] elements = groupe.getContacts();
        model.setRowCount(0);
        for (Contact c : elements){
            valeursligne[0]=c.getNom();
            valeursligne[1]=c.getPrenom();
            valeursligne[2]=c.getNumeroTelephone();
            model.addRow(valeursligne);
        }
        
        //zone dessin
       // zoneDessin.setPoints(groupe.getPoints());
        
        //liste des symboles
        symbole.clearSelection();
        for (Symbole s : groupe.getSymboles()){
            symbole.addSelectionInterval(s.ordinal(), s.ordinal());
        }
        
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @return
     */    
    public boolean getValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        /** TP 2 : à compléter **/
        //nom du groupe
        groupe.setNom(champGroup.getText());
        
        //symbole
        for (Symbole s : Symbole.values()){
            if (symbole.isSelectedIndex(s.ordinal())){
                groupe.addSymbole(s);
            }else{
                groupe.removeSymbole(s);
            }
        }
        
        
        /*
         * Ne pas s'occuper des membres du groupe car traité via des
         * commandes du menu qui appelera setValues
         */
        
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    public void initListeners() {        
        /*
         * Réagit aux évènements produits par le bouton effacer
         */
        
        /** TP 2 : à compléter **/
        bValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(true);
            }
        });
        bAnnuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(false);
            }
        });
    }    
}
