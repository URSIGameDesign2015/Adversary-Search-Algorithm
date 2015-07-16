package regex.matcher;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        ImageIcon icon;

        public ImagePanel(BufferedImage img) {
                this.icon = new ImageIcon(img);
                setLayout(new BorderLayout());

                JPanel jp = new JPanel();
                jp.setLayout(new GridLayout(20, 20));
                jp.add(new JLabel(icon));

                // Add panel to a scroll pane.
                int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
                int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
                JScrollPane jsp = new JScrollPane(jp, v, h);

                // Add scroll pane to the content pane.
                add(jsp, BorderLayout.CENTER);

        }
}
