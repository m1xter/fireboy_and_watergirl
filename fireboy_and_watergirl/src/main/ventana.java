package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class ventana {
	private JFrame jframe;

	public ventana(panel panel) {            
                            jframe = new JFrame();
                            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                        
                            jframe.add(panel);                            
                            jframe.setResizable(false);
                            jframe.pack();
                            jframe.setLocationRelativeTo(null);
                            jframe.setVisible(true);                         
                            
		
	}
        
        
            
        

}
