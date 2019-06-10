package com.company;

import javax.swing.*;
import java.awt.*;

public class Minner extends JFrame {
    JPanel panel;
    private final int IMAGE_SIZE=50;
    private final int COLS=15;
    private final int ROWS=1;

    public static void main(String[] args) {
        new Minner();
    }
    public Minner(){
        initPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Закривається коли нажимаєш на хрестік
        setTitle("Miner");//Заговолок гри
        setVisible(true);// Создає окошко
        setResizable(false);
        pack();
        setLocationRelativeTo(null);//in senter
    }

    private void initPanel() {
        panel = new JPanel(){
            @Override
            protected void printComponent(Graphics g) {
                super.printComponent(g);
                g.drawImage(getimage("bomb"),0,0,this);
                g.drawImage(getimage("num1"),IMAGE_SIZE,0,this);
            }
        };
        panel.setPreferredSize(new Dimension(IMAGE_SIZE*COLS,IMAGE_SIZE*ROWS));//становлюється розмір панелі
        add(panel);
    }
    private Image getimage(String name){
       ImageIcon icon = new ImageIcon("resurs/img/"+name+".png");
       return icon.getImage();
    }
}
