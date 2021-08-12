// Once the user has chosen two variables
// the program will display a graph that tells the relationship
// between the two variables
// based on the Ideal Gas Law PV = nRT.
// *I wish I could use this program during my chemistry tests*

package IdealGasLawGraphGenerator;

import java.awt.*;
import javax.swing.*;

public class RunApp {
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new RunApp();
         }
      });
   }

   public RunApp() {
      JFrame myFrame = new JFrame("GUI_Calculator");
      myFrame.setSize(800, 700);
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.add(new GUI_Calculator());
      myFrame.setVisible(true);
   }
}