package regex.matcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

public class RegexTreeFrame extends BaseFrame {
        private static final long serialVersionUID = 5082873609992360750L;
   
        private BufferedImage graphImage;
        
        private int strategy = 0;

    public RegexTreeFrame(BufferedImage graph, RegularExpression re) {
        BaseFrame.re = re;
        graphImage = graph;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JLabel titleLabel = new JLabel("Regular expression tree of"+re);
        
        ImagePanel imagePane = new ImagePanel(graphImage);
       
        RegexTreeActionPanel actionPanel = new RegexTreeActionPanel();
           
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        getContentPane().add(titleLabel);
        getContentPane().add(imagePane);
        getContentPane().add(actionPanel);
        
        pack();
        pack();
    }

        private class RegexTreeActionPanel extends JPanel {
                private static final long serialVersionUID = 1L;

                public RegexTreeActionPanel() {
                        initComponents();
                }
                
                protected void initComponents() {
                
                    JRadioButton thompsonRadioButton = new JRadioButton("Thompson");
                thompsonRadioButton.addActionListener(new ActionListener() {
                                
                                public void actionPerformed(ActionEvent e) {
                                        handleThompson(e);
                                }
                });
                    JRadioButton glushkovRadioButton = new JRadioButton("Glushkov");
                glushkovRadioButton.addActionListener(new ActionListener() {
                        
                    public void actionPerformed(ActionEvent evt) {
                                handleGlushkov(evt);
                    }
                });
                
                ButtonGroup strategyGroup = new ButtonGroup();
                strategyGroup.add(thompsonRadioButton);
                strategyGroup.add(glushkovRadioButton);
                
                        JButton convertButton = new JButton("Convert");
                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        handleConvert(evt);
                    }
                });
                
                add(thompsonRadioButton);
                add(glushkovRadioButton);
                add(convertButton);
                }
                
            private void handleThompson(ActionEvent evt) {                                                    
                strategy = 1;
            }   
            
            private void handleGlushkov(ActionEvent evt) {                                                    
                strategy = 2;
            }    
            
                private void handleConvert(ActionEvent evt) {
                DotAdapter da = new DotAdapter();
                NFA nfa = null;
                        if(strategy == 1) {
                                nfa = re.toAutomaton(RegexToNFAStrategy.THOMPSON);
                        }
                        else if(strategy == 2) {
                                nfa = re.toAutomaton(RegexToNFAStrategy.GLUSHKOV);
                        }
                        else {
                                return;
                        }
                        BufferedImage img = da.executeDot(nfa.toDot());
                        new NFAFrame(nfa,img,"").setVisible(true);

                }
                
        }

}