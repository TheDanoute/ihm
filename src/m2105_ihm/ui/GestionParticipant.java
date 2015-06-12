/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import m2105_ihm.Controleur;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author ricciva
 */
public class GestionParticipant extends JFrame {
    
    private PlanningUI planning;
    private FicheEvtUI ficheEvt;
    private Controleur controleur;
    private FenetreUI  fenetre;
    private JList      list1;
    private JList      list2;
    private JButton    valider;
    private JButton    annuler;
    private JButton    add;
    private JButton    del;
    private Evenement evenement;
    
    private ArrayList<Contact> listContact;
    private ArrayList<Contact> participants;
    
    
    public GestionParticipant(Controleur c,Evenement e){
        super("Gestion de participant");
        this.controleur =c;
        evenement = e;
        initUIComponents();
        
    }
    public GestionParticipant(Controleur c){
        super("Gestion de participant");
        this.controleur =c;
        initUIComponents();
        
    }
    
    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setVisible(true);                        
    }
    private void initUIComponents() {
        list2 = new JList();
        this.add(list1);
        this.add(list2);
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        add = new JButton("->");
        del = new JButton("<-");
        this.add(valider);
        this.add(annuler);
        this.add(add);
        this.add(del);
        
        
    }
    private void initListener(){
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.ajouterParticipantEvenement();
            }
        });
        
        del.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.retirerParticipantEvenement();
            }
        });
    }
    
    public void setValues() {
        for (Contact c : evenement.getParticipants()) {
            participants.add(c);
        }
        listContact = controleur.
        for (Contact c : participants) {
            try {
                listContact.remove(c);
            } catch (java.lang.NullPointerException e) {}
        }
    }
   
    
}
