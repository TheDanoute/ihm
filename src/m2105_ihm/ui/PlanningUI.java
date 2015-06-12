/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel {
    /**
     * Creates new form CarnetUI
     */
    private Controleur       controleur;
    private FicheEvtUI       ficheEvt;
    private JList            listEvts;
    private DefaultListModel model;
    private GestionParticipant gp;
    private JPanel           panel;

    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl) {
        super();
        
        this.controleur = ctrl;
        
        initComponents();        
    }

    /**
     * Crée et place les composants graphiques de l'interface
     */
    private void initComponents() {
        /*
         * Fiche événement
         */        
        model = new DefaultListModel();
        listEvts = new JList(model);
        listEvts.setBorder(BorderFactory.createTitledBorder("Evenements :"));
        
        
        ficheEvt = new FicheEvtUI(this);
        ficheEvt.add(listEvts);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(ficheEvt,BorderLayout.CENTER);
        panel.add(listEvts,BorderLayout.WEST);
        
        this.add(panel);
        
        listEvts.addListSelectionListener(new ListSelectionListener(){
            
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()){
                    controleur.setEvtSelected(true);
                    ficheEvt.setValues(getSelectedEvt());
                }
        }
        });
        
    }
    public void afficherFenetreParti(){
        gp = new GestionParticipant(controleur,this.getSelectedEvt());
        gp.afficher();
        //gp.setValues();
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            model.add(0, evt);
            ficheEvt.setVisible(true);
            listEvts.setSelectedIndex(0);
        return true;
            

    }
    
    /**
     * Retire une entrée dans l'arbre pour les contacts
     * @param Contact contact à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            model.removeElement(evt);
            ficheEvt.setVisible(false);
        
        return false;
    }
    
    public void setEventModified(boolean modified) {
        Evenement event = getSelectedEvt();

        if (modified) {
            ficheEvt.getValues(event);
            listEvts.updateUI();
        } else {
            ficheEvt.setValues(event);
        } 
    }
    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {    
        
        /** Projet à completer **/
        return (Evenement) listEvts.getSelectedValue();
       
    }
}
