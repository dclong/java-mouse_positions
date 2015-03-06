package lisa;

import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class RunMousePosition {
	public static void main(String[] args) {
        // Determine if the GraphicsDevice supports translucency.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
            System.err.println("Translucency is not supported");
            System.exit(0);
        }
        
        JFrame.setDefaultLookAndFeelDecorated(true);

        MousePosition tw = new MousePosition();
        tw.setVisible(true);
    }

}
