/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
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
    private JList      listTemp;
    private JButton    valider;
    private JButton    annuler;
    private JButton    add;
    private JButton    del;
    private Evenement evenement;
    private DefaultListModel model1;
    private DefaultListModel model2;
    private JPanel panelMain;
    private JPanel panel;
    private JPanel sousPanel;
    
    private ArrayList<Contact> listContact;
    private ArrayList<Contact> participants;
    
    
    public GestionParticipant(Controleur c,Evenement e){
        super("Gestion de participant");
        this.controleur =c;
        evenement = e;
        list1 = new JList();
        list2 = new JList();
        list1.setSize(200, 300);
        list2.setSize(200, 300);
        listContact = new ArrayList<Contact>();
        participants = new ArrayList<Contact>();
        model1 = new DefaultListModel();
        model2 = new DefaultListModel();
        this.setValues();
        initComponent();
        initUIComponents();
        
    }
    public void initComponent(){
        for(Contact c : listContact){
            model1.addElement(c);
        }
        list1.setModel(model1);
        for(Contact c : participants){
            model2.addElement(c);
        }
        list2.setModel(model2);
    }
    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setVisible(true);                        
    }
    private void initUIComponents() {
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        add = new JButton("->");
        del = new JButton("<-");
        
        sousPanel = new JPanel();
        sousPanel.setLayout(new BorderLayout());
        sousPanel.add(add,BorderLayout.NORTH);
        sousPanel.add(del,BorderLayout.SOUTH);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(sousPanel,BorderLayout.CENTER);
        
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.add(panel,BorderLayout.CENTER);
        panelMain.add(list1,BorderLayout.WEST);
        panelMain.add(list2,BorderLayout.EAST);
        JPanel bouton = new JPanel();
        bouton.setLayout(new BorderLayout());
        bouton.add(valider,BorderLayout.WEST);
        bouton.add(annuler,BorderLayout.EAST);
        panelMain.add(bouton,BorderLayout.SOUTH);
        this.add(panelMain);
        
        
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
        listContact = controleur.getContactsList();
        for (Contact c : participants) {
            try {
                listContact.remove(c);
            } catch (java.lang.NullPointerException e) {}
        }
    }
   
    
}
