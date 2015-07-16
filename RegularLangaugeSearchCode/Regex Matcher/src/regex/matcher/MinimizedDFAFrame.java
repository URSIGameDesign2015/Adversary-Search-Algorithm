package regex.matcher;

import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MinimizedDFAFrame extends BaseFrame {
        private static final long serialVersionUID = 5082873609992360750L;
   
        private BufferedImage graphImage;
        private DFA dfa;

    public MinimizedDFAFrame(DFA dfa, BufferedImage graph) {
        graphImage = graph;
        this.dfa = dfa;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel titleLabel = new JLabel("Minimized Deterministic Finite State Automaton of "+re);
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
                add(new ApproximateAutomatonActionPanel(dfa));
                }
        }

}
