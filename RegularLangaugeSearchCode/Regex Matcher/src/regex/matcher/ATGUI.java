package regex.matcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Automaton toolbox GUI
 *
 */
public class ATGUI extends JFrame {
        private static final long serialVersionUID = -8880985384177189800L;

    private JButton convertButton;
    private JTextField regexTextField;
    private JLabel regexLabel;
    private JLabel regexTreeLabel;

    public ATGUI() {
        initComponents();
    }
    
    private void initComponents() {
        
        setBounds ( 100 , 100 , 800 , 600 );

        regexTextField = new JTextField();
        regexTextField.setColumns(30);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automaton Toolbox");

        regexLabel = new JLabel();
        regexLabel.setText("Regular expression");

        convertButton = new JButton();
        convertButton.setText("Construct");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        regexTreeLabel = new JLabel();
        regexTreeLabel.setText("Regex tree");
        
        
        JPanel panel = new JPanel();
        panel.add(regexTextField);
        panel.add(convertButton);
        
        getContentPane().add(panel);

        getAccessibleContext().setAccessibleName("Regular expression converter");

        pack();
    }

    private void convertButtonActionPerformed(ActionEvent evt) {   
        try {
                RegularExpression re = new RegularExpression(regexTextField.getText());
                re.parse();
                DotAdapter da = new DotAdapter();
            BufferedImage img = da.executeDot(re.toDot());
            new RegexTreeFrame(img, re).setVisible(true);
        } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(this, "Error was encountered during processing of this regular expression. \n"+e);
        }
    }                                                 
}
