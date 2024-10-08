import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

public class TextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    JMenuBar menuBar;

    TextEditor() {
        frame = new JFrame("Awesome Text Editor");
        textArea = new JTextArea();
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        JMenu editMenu = new JMenu("Edit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new TextEditor();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentItem = e.getActionCommand();

        if (currentItem.equals("Cut")) {
            textArea.cut();
        } else if (currentItem.equals("Copy")) {
            textArea.copy();
        } else if (currentItem.equals("Paste")) {
            textArea.paste();
        } else if (currentItem.equals("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter fileWriter = new FileWriter(file, false);

                    BufferedWriter bWriter = new BufferedWriter(fileWriter);

                    bWriter.write(textArea.getText());
                    bWriter.flush();
                    bWriter.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (currentItem.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());


                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String line = "", fileContent = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        fileContent = fileContent + "\n" + line;
                    }

                    textArea.setText(fileContent);

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        } else if (currentItem.equals("New")) {
            textArea.setText("");
        }
    }

}
