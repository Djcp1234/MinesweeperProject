import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);
			Insets myInsetsR = myFrameR.getInsets();
			int xR1 = myInsetsR.left;
			int yR1 = myInsetsR.top;
			e.translatePoint(-xR1, -yR1);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			myPanelR.mouseDownGridX = myPanelR.getGridX(xR, yR);
			myPanelR.mouseDownGridY = myPanelR.getGridY(xR, yR);
			myPanelR.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
						} else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;
							if (myPanel.numberArray[gridX][gridY] == -1) {newColor = Color.BLACK; }
							else if (myPanel.numberArray[gridX][gridY] == 1){ newColor = Color.BLUE; }
							else if (myPanel.numberArray[gridX][gridY] == 2){ newColor = Color.GREEN; }	
							else if (myPanel.numberArray[gridX][gridY] == 3){ newColor = Color.CYAN; }
							else if (myPanel.numberArray[gridX][gridY] >= 4){ newColor = Color.YELLOW; }
							else {newColor = Color.GRAY;
								if (gridX < MyPanel.TOTAL_COLUMNS-1 && myPanel.numberArray[gridX+1][gridY] == 0){ myPanel.colorArray[myPanel.mouseDownGridX+1][myPanel.mouseDownGridY] = newColor ;}
								if (gridX < MyPanel.TOTAL_COLUMNS-1 && gridY < MyPanel.TOTAL_ROWS-1 && myPanel.numberArray[gridX+1][gridY+1] == 0) {myPanel.colorArray[myPanel.mouseDownGridX+1][myPanel.mouseDownGridY+1] = newColor ;
								if (gridY < MyPanel.TOTAL_ROWS-2 && myPanel.numberArray[gridX][gridY+1] == 0) {myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY+1] = newColor ;}
								if (gridX > 1 && gridY < MyPanel.TOTAL_ROWS-2 && myPanel.numberArray[gridX-1][gridY+1] == 0) {myPanel.colorArray[myPanel.mouseDownGridX-1][myPanel.mouseDownGridY+1] = newColor ;}
								if (gridX > 1 && myPanel.numberArray[gridX-1][gridY] == 0){myPanel.colorArray[myPanel.mouseDownGridX-1][myPanel.mouseDownGridY] = newColor ;}
								if (gridX > 1 && gridY > 1 && myPanel.numberArray[gridX-1][gridY-1] == 0) {myPanel.colorArray[myPanel.mouseDownGridX-1][myPanel.mouseDownGridY-1] = newColor;}
								if (gridY > 1 && myPanel.numberArray[gridX][gridY-1] == 0) {myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY-1] = newColor;}
								if (gridX < MyPanel.TOTAL_COLUMNS-1 && gridY > 1 && myPanel.numberArray[gridX+1][gridY-1] == 0) {myPanel.colorArray[myPanel.mouseDownGridX+1][myPanel.mouseDownGridY-1] = newColor;}
								}
							}

							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}
			myPanel.repaint();
			break;
			
		case 3:		//Right mouse button
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame)cR;
			MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsR = myFrameR.getInsets();
			int xR1 = myInsetsR.left;
			int yR1 = myInsetsR.top;
			e.translatePoint(-xR1, -yR1);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			int gridXR = myPanelR.getGridX(xR, yR);
			int gridYR = myPanelR.getGridY(xR, yR);
			if ((myPanelR.mouseDownGridX == -1) || (myPanelR.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridXR == -1) || (gridYR == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanelR.mouseDownGridX != gridXR) || (myPanelR.mouseDownGridY != gridYR)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridXR == 0) || (gridYR == 0)) {
							//On the left column and on the top row... do nothing
						} else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;
							if(myPanelR.colorArray[myPanelR.mouseDownGridX][myPanelR.mouseDownGridY] == Color.RED){
							newColor = Color.WHITE;
							}
							else if(myPanelR.colorArray[myPanelR.mouseDownGridX][myPanelR.mouseDownGridY] == Color.WHITE){
							newColor = Color.RED;
							}
							else{ break;}
								
							
							myPanelR.colorArray[myPanelR.mouseDownGridX][myPanelR.mouseDownGridY] = newColor;
							myPanelR.repaint();
						}
					}
				}
			}
			myPanelR.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}