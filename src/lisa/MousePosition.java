package lisa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

public class MousePosition extends JFrame implements MouseListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3995393692266844204L;
	private ArrayList<Point> positions;
	private boolean translucent = false;
	private JPopupMenu popup;
	
	public MousePosition() {
        super("TranslucentWindow");
        setLayout(new GridBagLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        addMouseListener(this);
        // popup menu
        popup = new JPopupMenu();
        // translucent
        JMenuItem menuItem1 = new JMenuItem(new AbstractAction("Translucent") {
			private static final long serialVersionUID = 1773030144295218649L;
			public void actionPerformed(ActionEvent e) {
				translucent = true;
                setOpacity(0.01f);
            }
        });
        // opacity
        JMenuItem menuItem2 = new JMenuItem(new AbstractAction("Opaque") {
			private static final long serialVersionUID = 1773030144295218649L;
			public void actionPerformed(ActionEvent e) {
                translucent = false;
				setOpacity(1f);
            }
        });
        // write to a file 
        JMenuItem menuItem3 = new JMenuItem(new AbstractAction("Write to file...") {
			private static final long serialVersionUID = 1773030144295218649L;
			public void actionPerformed(ActionEvent e) {
				writePositions(false);
            }
        });
        // append to a file
        JMenuItem menuItem4 = new JMenuItem(new AbstractAction("Append to file...") {
			private static final long serialVersionUID = 1773030144295218649L;
			public void actionPerformed(ActionEvent e) {
				writePositions(true);
            }
        });
        popup.add(menuItem1);
        popup.add(menuItem2);
        popup.add(menuItem3);
        popup.add(menuItem4);
        //-------------------------------------------------------
        positions = new ArrayList<Point>();
    }

	private void writePositions(boolean append){
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(MousePosition.this);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, append)))) {
				int size = positions.size();
				for(int i=0; i<size; ++i){
					Point point = positions.get(i);
					out.println(point.x + " " + point.y);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(translucent){
			setOpacity(0.01f);
		}
	}
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(SwingUtilities.isLeftMouseButton(arg0)){
			positions.add(MouseInfo.getPointerInfo().getLocation());
		}else if(SwingUtilities.isRightMouseButton(arg0)){
			// unhide the window so that the popup menu is visible
			setOpacity(1f);
			popup.show(this, arg0.getX(), arg0.getY());
		} 	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
