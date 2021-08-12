// This file is required by RunApp.java

package IdealGasLawGraphGenerator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;

class GUI_Calculator extends JPanel implements ListSelectionListener, ActionListener {
   JList<String> allXVariables;
   JList<String> allYVariables;
   JLabel xLabel;
   JLabel yLabel;
   JLabel cLabel;
   JScrollPane xVariables;
   JScrollPane yVariables;
   int xIndex = -1;
   int yIndex = -1;
   int state;
   String variables[];
   String xAxis, yAxis;

   public GUI_Calculator() {
      setLayout(new FlowLayout());
      setBackground(Color.BLUE);

      variables = new String[] { "Pressure", "Volume", "Temperature" };
      allXVariables = new JList<String>(variables);
      allYVariables = new JList<String>(variables);
      allXVariables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      allYVariables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      xVariables = new JScrollPane(allXVariables);
      yVariables = new JScrollPane(allYVariables);
      xVariables.setPreferredSize(new Dimension(120, 60));
      yVariables.setPreferredSize(new Dimension(120, 60));

      xLabel = new JLabel("Independent Variable: ");
      yLabel = new JLabel("Dependent Variable: ");
      cLabel = new JLabel("All the other variables are kept constant.");
      JButton jBtn = new JButton("Enter");

      allXVariables.addListSelectionListener(this);
      allYVariables.addListSelectionListener(this);
      jBtn.addActionListener(this);

      add(xLabel);
      add(xVariables);
      add(yLabel);
      add(yVariables);
      add(jBtn);
      add(cLabel);

   }

   public void valueChanged(ListSelectionEvent le) {
      xIndex = allXVariables.getSelectedIndex();
      yIndex = allYVariables.getSelectedIndex();

      if (xIndex != -1)
         xLabel.setText("Independent Variable: " + variables[xIndex]);
      else
         xLabel.setText("Independent Variable: ");

      if (yIndex != -1 && yIndex != xIndex)
         yLabel.setText("Dependent Variable: " + variables[yIndex]);
      else if (yIndex == xIndex)
         yLabel.setText("DV matches IV. Choose another variable: ");
      else
         yLabel.setText("Dependent Variable: ");
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Enter") && !(xIndex == -1 || yIndex == -1 || xIndex == yIndex)) {
         if ((xIndex == 0 && yIndex == 1) || (xIndex == 1 && yIndex == 0))
            this.state = 1;
         else
            this.state = 2;

         this.xAxis = variables[this.xIndex];
         this.yAxis = variables[this.yIndex];
      }
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      paintBase(g2d);

      if (this.state == 1) {
         QuadCurve2D qc = new QuadCurve2D.Double();
         qc.setCurve(275, 200, 275, 375, 550, 375);
         g2d.draw(qc);
         paintAxis(g2d, this.xAxis, this.yAxis);
         repaint();
      } else if (this.state == 2) {
         g2d.draw(new Line2D.Double(250, 400, 550, 200));
         paintAxis(g2d, this.xAxis, this.yAxis);
         repaint();
      }
   }

   private void paintBase(Graphics2D g) {
      g.setColor(Color.WHITE);
      g.fillOval(200, 100, 400, 400);
      g.fillOval(100, 150, 600, 300);
      g.fillOval(100, 450, 150, 75);
      g.fillOval(65, 550, 90, 45);
      g.fillOval(35, 620, 50, 25);

      g.setFont(new Font("Calibri", Font.PLAIN, 30));
      g.drawString("Ideal Gas Law Graph Generator", 300, 600);

      g.setColor(Color.BLACK);
      g.draw(new Line2D.Double(250, 400, 550, 400));
      g.draw(new Line2D.Double(250, 200, 250, 400));
   }

   private void paintAxis(Graphics2D g, String x, String y) {
      g.setFont(new Font("Calibri", Font.PLAIN, 18));
      g.drawString(x, 350, 425);
      g.drawString(y, 150, 300);
   }

}