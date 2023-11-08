import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;
//        Constructor
    Notepad(){
//        Set NotePad Icon
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);
        setTitle("NotePad");
//        Creating MenuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        JMenu file = new JMenu("File");
        file.setFont(new Font("Arial",Font.PLAIN,14));
//        Creating File DropDown ---> New,open,save,print,exit
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        file.add(newdoc);
//        -------------------
        JMenuItem open = new JMenuItem("open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        file.add(open);
//        -------------------
        JMenuItem save = new JMenuItem("save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        file.add(save);
//        --------------------
        JMenuItem print = new JMenuItem("print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        file.add(print);
//        ---------------------
        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.CTRL_MASK));
        file.add(exit);
//        ----------------------
//        Creating second MenuItem Edit
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("Arial",Font.PLAIN,14));
//        Edit dropDown --> Copy,paste,cut,selectAll
        JMenuItem copy = new JMenuItem("copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        edit.add(copy);
//        -----------------------
        JMenuItem paste = new JMenuItem("paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        edit.add(paste);
//        ------------------------
        JMenuItem cut = new JMenuItem("cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        edit.add(cut);
//        -------------------------
        JMenuItem selectAll = new JMenuItem("select all");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        edit.add(selectAll);
//       ---------------------------
//        Third MenuItem About
        JMenu helpMenu = new JMenu("About");
        helpMenu.setFont(new Font("Arial",Font.PLAIN,14));
//       About DropDown -->About Notepad
        JMenuItem help = new JMenuItem("About NotePad");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        helpMenu.add(help);
//        Add MenuItem on MenuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(helpMenu);
//        Set MenuBar on Frame
        setJMenuBar(menuBar);
//        Made TextArea
        area = new JTextArea();
        area.setFont(new Font("SAN-SERIF",Font.PLAIN,18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
//        For Scrolling
        JScrollPane pane = new JScrollPane(area);
        add(pane);

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
//    OnClick Functions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")){
        area.setText("");
    } else if (e.getActionCommand().equals("open")) {
           JFileChooser fileChooser = new JFileChooser();
           fileChooser.setAcceptAllFileFilterUsed(false);
           FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .txt Files","txt");
           fileChooser.addChoosableFileFilter(filter);
           int action = fileChooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = fileChooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader,null);
            }catch (Exception ae){
                ae.printStackTrace();
            }
        } else if (e.getActionCommand().equals("save")) {
            JFileChooser save= new JFileChooser();
            save.setApproveButtonText("save");
            int action = save.showOpenDialog(this);
            if (action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            File fileForSave = new File(save.getSelectedFile()+".txt");
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(fileForSave));
                area.write(writer);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        } else if (e.getActionCommand().equals("print")) {
            try {
                area.print();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }else if (e.getActionCommand().equals("exit")) {
            try {
                System.exit(0);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
//        ------------------------------------
        else if (e.getActionCommand().equals("copy")) {
            text= area.getSelectedText();
        } else if (e.getActionCommand().equals("paste")) {
            area.insert(text,area.getCaretPosition());
        } else if (e.getActionCommand().equals("cut")) {
            text=area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        } else if (e.getActionCommand().equals("select all")) {
            area.selectAll();
        }
//        --------------------------------------
        else if (e.getActionCommand().equals("About NotePad")) {
            new About().setVisible(true);
        }
//        -------------------------------------------

    }
    public static void main(String[] args) {
//        Notepad notepad = new Notepad(); --->object
        new  Notepad();
    }
}
