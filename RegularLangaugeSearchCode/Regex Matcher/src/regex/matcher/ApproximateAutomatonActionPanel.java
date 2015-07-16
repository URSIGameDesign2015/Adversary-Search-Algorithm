package regex.matcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Action panel for creating automata allowing errors 
 * 
 */
public class ApproximateAutomatonActionPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        
        IntField errorField;
        
        Automaton automaton;

        public ApproximateAutomatonActionPanel(Automaton automaton) {
                initComponents();
                this.automaton = automaton;
        }
        
        protected void initComponents() {
                JLabel errorLabel = new JLabel("Errors allowed: ");
                errorField = new IntField(0,6);
                errorField.setSize(100, (int) errorField.getSize().getHeight());
                JButton convertButton = new JButton("Convert to approximate automaton");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handleConvert(evt);
            }
        });
        
        add(errorLabel);
        add(errorField);
        add(convertButton);
        }
 
        private void handleConvert(ActionEvent evt) {
                String text = errorField.getText();
                int errors = Integer.valueOf(text);
                Automaton approximate = ApproximateAutomatonConverter.convert(automaton, errors);
                BufferedImage img = new DotAdapter().executeDot(approximate.toDot());
                new NFAFrame((NFA)approximate,img,"Approximate automaton allowing "+errors+" errors.").setVisible(true);

        }
        
        

        class IntField extends JTextField {
                private static final long serialVersionUID = 1L;
        public IntField(int defval, int size) {
            super("" + defval, size);
          }

          protected Document createDefaultModel() {
            return new IntTextDocument();
          }

          public boolean isValid() {
            try {
              Integer.parseInt(getText());
              return true;
            } catch (Exception e) {
              return false;
            }
          }

          public int getValue() {
            try {
              return Integer.parseInt(getText());
            } catch (Exception e) {
              return 0;
            }
          }
          class IntTextDocument extends PlainDocument {
                private static final long serialVersionUID = 1L;

                public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
              if (str == null)
                return;
              String oldString = getText(0, getLength());
              String newString = oldString.substring(0, offs) + str
                  + oldString.substring(offs);
              try {
                Integer.parseInt(newString + "0");
                super.insertString(offs, str, a);
              } catch (Exception e) {
              }
            }
          }

        }
        
}
