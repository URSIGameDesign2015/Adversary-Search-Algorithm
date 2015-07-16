package regex.matcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DFAFrame extends BaseFrame {
        private static final long serialVersionUID = 5082873609992360750L;
   
        private BufferedImage graphImage;
        private DFA dfa;

    public DFAFrame(DFA dfa, BufferedImage graph) {
        graphImage = graph;
        this.dfa = dfa;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel titleLabel = new JLabel("Deterministic Finite State Automaton of "+re);
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
                        JButton convertButton = new JButton("Minimize");
                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        handleConvert(evt);
                    }
                });
                add(convertButton);
                add(new ApproximateAutomatonActionPanel(dfa));
                }
            
                private void handleConvert(ActionEvent evt) {
                DotAdapter da = new DotAdapter();
                DFA minimizedDFA = DFAMinimizer.minimize(dfa);
                        BufferedImage img = da.executeDot(minimizedDFA.toDot());
                        new MinimizedDFAFrame(minimizedDFA,img).setVisible(true);

                }
                
        }

}
