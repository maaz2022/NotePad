import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
    About(){
        setLayout(null);
        setTitle("About");
//        TopIcon
        ImageIcon topIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image topIconn = topIcon.getImage();
        setIconImage(topIconn);
//          Layout Size
        setBounds(400,100,600,500);
//        Header Icon
        ImageIcon headerImage = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));
        Image image = headerImage.getImage().getScaledInstance(300,60,Image.SCALE_DEFAULT);
        ImageIcon image2 = new ImageIcon(image);
        JLabel headImage = new JLabel(image2);
        headImage.setBounds(70,40,400,80);
        add(headImage);
//      Second ICon
        ImageIcon newIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image newIcon2 = newIcon.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon newIcon3 = new ImageIcon(newIcon2);
        JLabel icon = new JLabel(newIcon3);
        icon.setBounds(50,180,70,70);
        add(icon);
//        Text
        JLabel text = new JLabel("<html>NotePad Clone<br> Version 0.0(OS Build Java)<br>Muhammad Maaz....All rights Reserved</html>");
        text.setBounds(150,60,500,300);
        text.setFont(new Font("SAN-SERIF",Font.PLAIN,17));
        add(text);
//      Button ()
        JButton btn = new JButton("Okay");
        btn.addActionListener(this);
        btn.setBounds(450,400,80,25);
        add(btn);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        On Okay Button close the frame
        this.setVisible(false);
    }
    public static void main(String[] args) {
        new About();
    }


}
