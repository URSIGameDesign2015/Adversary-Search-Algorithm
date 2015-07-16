package regex.matcher;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * Dot program wrapper.
 */
public class DotAdapter {
        
        private static final String DEFAULT_PATH = "dot";
        private static final String DEFAULT_ARGS = "-Tjpg";

        private String path;
        private String args;

    /**
     * Execute dot on input graph and return generated image.
     * 
     * @param inputGraph input graph string in dot format.
     * @return generated image
     */
    public BufferedImage executeDot(String inputGraph) {
        String dotPath = (path != null) ? path : DEFAULT_PATH;
        String dotArgs = (args != null) ? args : DEFAULT_ARGS;  
        
        try {
            OutputStream stdout = null;
            InputStream stdin = null;
            Process process = Runtime.getRuntime().exec(dotPath+" "+dotArgs);
            stdout = process.getOutputStream();

            stdout.write(inputGraph.getBytes());
            stdout.flush();
            stdout.close();
            stdin = process.getInputStream();
            BufferedImage img = null;
            img = ImageIO.read(stdin);
            stdin.close();
            return img;

        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }

    public void setPath(String path) {
                this.path = path;
        }

        public void setArgs(String args) {
                this.args = args;
        }
    
    
}