package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame implements ActionListener {

    JFrame f;
    JLabel l1,l2,l3,l4;
    JButton b1,b2,b3,b4;

    Index(){
        f=new JFrame("Index Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);


        l1=new JLabel();
        l1.setBounds(0,0,800,570);
        l1.setLayout(null);

        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("hospital/icons/main_image.jpg"));

        Image i1=img.getImage().getScaledInstance(800,570,Image.SCALE_SMOOTH);

        ImageIcon img1=new ImageIcon(i1);
        l1.setIcon(img1);

        l2=new JLabel("HealthCare Management system ");
        l2.setBounds(50,200,700,50);
        l2.setFont(new Font("Aerial",Font.BOLD,30));
        l2.setForeground(Color.WHITE);
//        l2.setLayout(null);


        l1.add(l2);
        f.add(l1);

        l4=new JLabel("Administrator access page");
        l4.setBounds(20,50,700,50);
        l4.setFont(new Font("Aerial",Font.BOLD,25));
        l4.setForeground(Color.WHITE);
        l1.add(l4);

        l3=new JLabel("Project using JAVA");
        l3.setBounds(70,250,700,50);
        l3.setFont(new Font("Aerial",Font.BOLD,25));
        l3.setForeground(Color.WHITE);
        l1.add(l3);

        b1 = new JButton("Doctor");
        b1.setBounds(50,350,150,40);
        b1.setBackground(Color.darkGray);
        b1.setForeground(Color.orange);
        b1.addActionListener(this);
        l1.add(b1);

        b2 = new JButton("Patient");
        b2.setBounds(250,350,150,40);
        b2.setBackground(Color.darkGray);
        b2.setForeground(Color.orange);
        b2.addActionListener(this);

        l1.add(b2);

        b3 = new JButton("Admin");
        b3.setBounds(b1.getX(),b1.getY()+50,150,40);
        b3.setBackground(Color.darkGray);
        b3.setForeground(Color.orange);
        b3.addActionListener(this);

        l1.add(b3);

        b4 = new JButton("Register");
        b4.setBounds(b2.getX(), b3.getY(), 150,40);
        b4.setBackground(Color.darkGray);
        b4.setForeground(Color.orange);
        b4.addActionListener(this);
        l1.add(b4);
        f.setSize(800,570);
        f.setLocation(300,100);
        f.setVisible(true);
        f.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            f.dispose();
            new DoctorLogin();

        }
        if(e.getSource()==b2){

            f.dispose();
            new PatientLogin();

        }
        if(e.getSource()==b3){
            f.dispose();
            new AdminLogin();
        }
        if(e.getSource()==b4){
            f.dispose();
            new RegistrationPage();
        }
    }

    public static void main(String[] args) {

        new Index();
    }
}
