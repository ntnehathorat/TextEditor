import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    //File menu items
    JMenuItem newFile,openFile,saveFile;
    //Edit menu items
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    TextEditor()
    {
        //Initialize a frame
        frame= new JFrame();

        //Initialize a menuBar
        menuBar= new JMenuBar();

        //Initialize a textArea
        textArea = new JTextArea();

        //Initialize a menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize a File menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action listeners to file menu items
        newFile.addActionListener(this); // TextEditor Behaved like a ActionListener
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize a Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Add Action Listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Add textArea to frame
        //frame.add(textArea);  we dont need to add directly to the frame

        //Create Content Panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5 ,5 ,5));
        panel.setLayout(new BorderLayout(0 ,0));

        // Adding text area to the panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add Scroll P{ane To Panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);

        //Set MenuBar to frame
        frame.setJMenuBar(menuBar);

        //Set Dimensions of frame
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == cut)
        {
            //Perform Cut Operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy)
        {
            //Perform Copy Operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste)
        {
            //Perform Paste Operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll)
        {
            //Perform SelectAll Operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close)
        {
            //Perform Close Editor Operation
            System.exit(0);

        }
        if(actionEvent.getSource() == openFile)
        {
            //Open a File Chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //Get the selected File
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filepath = file.getPath();
                try
                {
                    //Initialize File Reader
                    FileReader fileReader = new FileReader(filepath);
                    //Initialize Buffer Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output="";
                    //Read Contents Of Line By Line
                    while((intermediate = bufferedReader.readLine()) != null)
                    {
                        output += intermediate +"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile)
        {  //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //create a new file with chosen directing oath and file name
                File file =new File(fileChooser.getSelectedFile().getAbsolutePath()+"txt");
                try {
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile)
        {
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new  TextEditor();

    }
}