package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Program extends JFrame {
    private JButton button=new JButton("Press");
    private JTextField input=new JTextField("",5);
    private JLabel label=new JLabel("Input:");
    private JRadioButton radio1=new JRadioButton("Selet 1");
    private JRadioButton radio2=new JRadioButton("Selet 2");
    private JCheckBox check=new JCheckBox("Check",false);
    public Program(){
        super("Simple exemple");
        this.setBounds(400,400,1000,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container=this.getContentPane();
        container.setLayout(new GridLayout(12,8,8,8));
        container.add(label);
        container.add(input);
        ButtonGroup grup=new ButtonGroup();
        grup.add(radio1);
        grup.add(radio2);
        container.add(radio1);
        container.add(radio2);
        container.add(check);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }
    class ButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Sort cort=new Sort();

            String messege="";
            messege +="Button was pressed\nText is"+input.getText()+"\n";
            messege +=(radio1.isSelected()? "radio1":"radio2")+"is selected\n";
            messege +="Check is"+(check.isSelected()?"checked":"unchecked");
            JOptionPane.showMessageDialog(null,messege,"output",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Program app=new Program();
        app.setVisible(true);

    }




}
