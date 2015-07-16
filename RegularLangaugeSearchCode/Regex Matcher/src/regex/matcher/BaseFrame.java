package regex.matcher;

import java.awt.Dimension;

import javax.swing.JFrame;

public class BaseFrame extends JFrame {
        private static final long serialVersionUID = 1L;

        protected static RegularExpression re;
        
        protected int errors = 0;
        
        public BaseFrame() {
                setBounds ( 100 , 100 , 800 , 600 );
                setMaximumSize(new Dimension(800,600));
                setSize(new Dimension(800,600));
                setPreferredSize(new Dimension(800,600));
        }

}
