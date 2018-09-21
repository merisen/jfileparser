package ru.merisen.javafileparser;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            frame wframe = new frame(readDataFile("your path to file"));
            wframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            wframe.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static String[] readDataFile(String name) throws FileNotFoundException, IOException {
        try {
            Reader sym = new InputStreamReader(new FileInputStream(name), "CP1251");
            char c[] = new char[(int) (new File(name)).length()];
            String a[] = null;
            int i = 0;
            String str;
            sym.read(c);
            str = String.copyValueOf(c);
            sym.close();
            return str.split("\r\n");
        } catch (Exception e) {
            System.out.println("Неверное название");
        }
        return null;
    }
    
}


class frame extends JFrame {
    private int columns = 90;

    public frame(String[] s) {
        this.setSize(690, 890);
        this.setResizable(true);
        JPanel panel = new JPanel();
        JTextField text = new JTextField();
        text.setEditable(false);
        text.setColumns(columns);
        text.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent ce) {
                int len = text.getDocument().getLength();
                if (len > columns)
                    text.setColumns(++columns);
                else {
                    if (--columns != 0)
                        text.setColumns(columns);
                    else {
                        columns = 50;
                        text.setColumns(columns);
                    }
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setSize(1500, 1500);

        panel.add(textArea);
        add(panel);


        String s1 = "";
        for (int i = 0; i < s.length; i++)
            s1 = s1 + s[i];
        textArea.setText(s1);
    }

}