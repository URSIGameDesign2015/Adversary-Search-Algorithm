package regex.matcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class NFAFrame extends BaseFrame {
        private static final long serialVersionUID = 5082873609992360750L;
   
        private BufferedImage graphImage;
        private NFA nfa;
        private String title = "";

    public NFAFrame(NFA nfa, BufferedImage graph, String title) {
        graphImage = graph;
        this.nfa = nfa;
        this.title = title;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel titleLabel;
        if(title == "") {
                titleLabel = new JLabel("Nondeterministic Finite State Automaton of "+re);
        } else {
                titleLabel = new JLabel(title);
        }
        ImagePanel imagePane = new ImagePanel(graphImage);
       
        NFAActionPanel actionPanel = new NFAActionPanel();
           
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        getContentPane().add(titleLabel);
        getContentPane().add(imagePane);
        getContentPane().add(actionPanel);
        
        pack();
        pack();
    }

        private class NFAActionPanel extends JPanel {
                private static final long serialVersionUID = 1L;

                public NFAActionPanel() {
                        initComponents();
                }
                
                protected void initComponents() {
                        JButton convertButton = new JButton("Convert to DFA");
                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        handleConvert(evt);
                    }
                });
                add(convertButton);
                add(new ApproximateAutomatonActionPanel(nfa));
                }
            
                private void handleConvert(ActionEvent evt) {
                DotAdapter da = new DotAdapter();
                DFA dfa = NFAToDFAConverter.convert(nfa);
                        BufferedImage img = da.executeDot(dfa.toDot());
                        new DFAFrame(dfa,img).setVisible(true);

                }
                
        }

}
