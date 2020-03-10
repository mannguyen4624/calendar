/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import org.apache.commons.configuration.ConfigurationException;
import utils.Utility;

/**
 *
 * @author Binh
 */
public class MainFrame extends javax.swing.JFrame {
    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;
    private CalendarPanel calendarPanel;
    private EventPanel eventPanel;
    private ClassesPanel classesPanel;
    private EmailPanel emailPanel;
    private int loggedUser;  // 0 - not logged in, -1 - student, 1 - professor
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        this.loggedUser = 0;
        
        this.menuPane.setVisible(false);
        this.titleJPanel.setVisible(false);
        
        // Adding a Login panel
        this.loginPanel = new LoginPanel();
        this.loginPanel.setSize(this.getSize());
        this.loginPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.mainJPanel.add(this.loginPanel);
        this.loginPanel.addLoginBtnActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if it is correct --> display menuPane and titleJpanle
                String errorMsg = "Your account was not found. Here are the possible issues:\n"
                        + "- Your account is not verified. Please check your personal email;\n"
                        + "- Wrong email and/or password. Please try again;";
                if (loginPanel.getEmail().length() != 0 && loginPanel.getPwd().length() != 0) {
                    if (loginPanel.getEmail().equals(Utility.loadFromFile("studentEmail"))
                            && loginPanel.getPwd().equals(Utility.loadFromFile("studentPwd"))) {
                        loginPanel.setVisible(false);
                        loginPanel.clearFields();
                        menuPane.setVisible(true);
                        titleJPanel.setVisible(true);
                        // Logged as a student
                        loggedUser = -1;
                    } else if (loginPanel.getEmail().equals(Utility.loadFromFile("professorEmail"))
                            && loginPanel.getPwd().equals(Utility.loadFromFile("professorPwd"))) {
                        loginPanel.setVisible(false);
                        loginPanel.clearFields();
                        menuPane.setVisible(true);
                        titleJPanel.setVisible(true);
                        // Logged as a professor
                        loggedUser = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, errorMsg, "", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else { 
                    JOptionPane.showMessageDialog(null, errorMsg, "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.loginPanel.addCreateAccMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginPanel.setVisible(false);
                registrationPanel.setVisible(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        // Adding a Registration panel
        this.registrationPanel = new RegistrationPanel();
        this.registrationPanel.setSize(this.getSize());
        this.mainJPanel.add(this.registrationPanel);
        this.registrationPanel.addCreateAccBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (registrationPanel.isStudent()) {
                    // Creating a student account 
                    Utility.saveToFile("studentEmail", registrationPanel.getEmail());
                    Utility.saveToFile("studentPwd", registrationPanel.getPwd());
                } else {
                    // Creating a professor account 
                    Utility.saveToFile("professorEmail", registrationPanel.getEmail());
                    Utility.saveToFile("professorPwd", registrationPanel.getPwd());
                }
                registrationPanel.setVisible(false);
                emailPanel.setEmail(registrationPanel.getEmail());
                emailPanel.setVisible(true);
                
            }
            
        });
        
        // Adding a Calendar panel
        this.calendarPanel = new CalendarPanel();
        this.calendarPanel.setSize(this.panelForCalendar.getSize());
        this.panelForCalendar.add(this.calendarPanel);
        this.calendarPanel.addCreateEventBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd = new JDialog();
                jd.setTitle("Creating a New Event");
                jd.setSize(calendarPanel.getWidth() / 3, calendarPanel.getHeight());
                jd.setLocationRelativeTo(null);
                jd.setVisible(true);
                
                jd.add(eventPanel);
                
                jd.pack();
                
                eventPanel.addCreateBtnActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Utility.saveToFile("eventName", eventPanel.getEventName());
                        jd.dispose();
                        eventPanel.reset();
                        calendarPanel.addEvent(1, 1, Utility.loadFromFile("eventName"));
                    }
                });
            } 
        });
        
        // Adding an Event panel
        this.eventPanel = new EventPanel();
        
        // Adding a Classes panel
        this.classesPanel = new ClassesPanel();
        this.classesPanel.setSize(this.panelForClasses.getSize());
        this.panelForClasses.add(this.classesPanel);
        this.classesPanel.addAddClassBtnActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                classesPanel.setVisible(false);
                // TODO switch to AddClassPanel (as student)
                
            }
        });
        
        // Adding an Email Panel
        this.emailPanel = new EmailPanel();
        this.emailPanel.setSize(this.getSize());
        this.emailPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.mainJPanel.add(this.emailPanel);
        this.emailPanel.setVisible(false);
        this.emailPanel.addBackBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPanel.clearFields();
                loginPanel.setVisible(true);
                emailPanel.setVisible(false);
            }
            
        });
        
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainJPanel = new javax.swing.JPanel();
        titleJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        menuPane = new javax.swing.JTabbedPane();
        panelForCalendar = new javax.swing.JPanel();
        panelForClasses = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deadline");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 600));

        mainJPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        titleJPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel1.setText("DeadLine");

        logoutBtn.setBackground(new java.awt.Color(102, 102, 102));
        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addContainerGap())
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuPane.setToolTipText("");
        menuPane.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        panelForCalendar.setLayout(new java.awt.BorderLayout());
        menuPane.addTab("Calendar", panelForCalendar);

        panelForClasses.setLayout(new java.awt.BorderLayout());
        menuPane.addTab("Classes", panelForClasses);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 886, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        menuPane.addTab("Profile", jPanel1);

        javax.swing.GroupLayout mainJPanelLayout = new javax.swing.GroupLayout(mainJPanel);
        mainJPanel.setLayout(mainJPanelLayout);
        mainJPanelLayout.setHorizontalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPane)
                .addContainerGap())
        );
        mainJPanelLayout.setVerticalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuPane)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        this.loginPanel.setVisible(true);
        this.menuPane.setVisible(false);
        this.titleJPanel.setVisible(false);
    }//GEN-LAST:event_logoutBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JTabbedPane menuPane;
    private javax.swing.JPanel panelForCalendar;
    private javax.swing.JPanel panelForClasses;
    private javax.swing.JPanel titleJPanel;
    // End of variables declaration//GEN-END:variables
}