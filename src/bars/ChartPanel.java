package bars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChartPanel extends JPanel {
	
  private double[] values;
  private String[] names;
  private String title;

  public ChartPanel(double[] v, String[] n, String t) {
    names = n;
    values = v;
    title = t;
  }

  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (values == null || values.length == 0)
      return;
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < values.length; i++) {
      if (minValue > values[i])
        minValue = values[i];
      if (maxValue < values[i])
        maxValue = values[i];
    }

    Dimension d = getSize();
    int clientWidth = d.width;
    int clientHeight = d.height;
    int barWidth = clientWidth / values.length;

    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

    int titleWidth = titleFontMetrics.stringWidth(title);
    int y = titleFontMetrics.getAscent();
    int x = (clientWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, x, y);

    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue)
      return;
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    y = clientHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);

    for (int i = 0; i < values.length; i++) {
      int valueX = i * barWidth + 1;
      int valueY = top;
      int height = (int) (values[i] * scale);
      if (values[i] >= 0)
        valueY += (int) ((maxValue - values[i]) * scale);
      else {
        valueY += (int) (maxValue * scale);
        height = -height;
      }

      g.setColor(Color.blue);
      g.fillRect(valueX, valueY, barWidth - 12, height);
      g.setColor(Color.black);
      g.drawRect(valueX, valueY, barWidth - 12, height);
      int labelWidth = labelFontMetrics.stringWidth(names[i]);
      x = i * barWidth + (barWidth - labelWidth) / 2;
      g.drawString(names[i], x, y);
    }
  }
  
  	public static void main(String[] argv) {
  		JFrame fpart1 = new JFrame("Statistics Part 1");
  		JFrame fpart2 = new JFrame("Statistics Part 2");
  		JFrame fJoker = new JFrame("Statistics Joker");
    	fpart1.setSize(1800, 700);
    	fpart2.setSize(1800, 650);
    	fJoker.setSize(1500, 500);
    	double[] values = new double[4];
    	
    	String[] names = new String[45];
       	String[] names1 = new String[22];
    	String[] names2 = new String[23];
    	
    	int a[]={4, 9, 13, 19, 28, 34};
		int l[]=new int[5],jokerl;
		int sum[]=new int[45];
		int sum1[]=new int[20];
		double freq45[]=new double[45];
		
		double freq1[]=new double[22];
		double freq2[]=new double[23];
		
		String[] namesJoker = new String[20];
		double freqjoker[]=new double[20];
		
		int joker=14;
		int i;
		boolean ok;
		int countlotteries=0;
		boolean found;
		found=false;
		
		for(i=0;i<45;i++){
			sum[i]=0;
		}
		
		while (!found){
			Random r=new Random();
			l[0]=r.nextInt(45)+1;
			ok=false;
			while (!ok){
				r=new Random();
				l[1]=r.nextInt(45)+1;
				if (l[1]!=l[0])
					ok=true;
			}
			ok=false;
			while(!ok){
				r=new Random();
                l[2]=r.nextInt(45)+1;
                if(l[2]!=l[0]&&l[2]!=l[1])
                    ok=true;
            } 
			ok=false;
			while(!ok){
				r=new Random();
				l[3]=r.nextInt(45)+1;
	                if(l[3]!=l[0]&&l[3]!=l[1]&&l[3]!=l[2])
	                    ok=true;
	            } 
			ok=false;
			while(!ok){
				r=new Random();
                l[4]=r.nextInt(45)+1;
                ok=true;
                for (i=0; i<=3; i++){
                    if(l[4]==l[i])
                        ok=false;
                }
            } 
			
			Arrays.sort(l);
			
			r=new Random();
			jokerl=r.nextInt(20)+1;
			found=true;
			 for(i=0; i<5; i++) {
		            if(l[i]!=a[i])
		                found=false;
		        }
			 
			if(jokerl!=joker)
	           found=false;			 		
			 countlotteries++;
			 
			 //Print the combination every million attempts.
			 if (countlotteries%1000000==0){			
				 System.out.println("Attempts per million : " + countlotteries/(1000000));	 			
			 }
			 
			 for(i=0; i<5; i++){
				int k=l[i];
				sum[k-1]++;
			 }
			 int k=jokerl;
			 sum1[k-1]++;
			 
		}  //BIG while ///////////////////////////////////////////////////////////////////////////
	
	
	for(i=0;i<45;i++){
		freq45[i]=sum[i]/(double) countlotteries*100.0;
	}
	for(i=0;i<20;i++){
		freqjoker[i]=sum1[i]/(double) countlotteries*100.0;
	}
	
	
	for(i=0;i<45;i++){
		names[i]= "N:" + (i+1)+":"+new DecimalFormat("#0.00").format(freq45[i]);
	}
	for(i=0;i<20;i++){
		namesJoker[i]="N:"+(i+1)+":"+new DecimalFormat("#0.0000").format(freqjoker[i]);
	}
    
	for(i=0;i<=21;i++){
		freq1[i]=freq45[i];
		names1[i]=names[i];
	}
	for(i=22;i<45;i++){
		freq2[i-22]=freq45[i];
		names2[i-22]=names[i];
	}
	
	fpart1.getContentPane().add(new ChartPanel(freq1, names1, "Total Lotteries: "+ countlotteries));
	fpart2.getContentPane().add(new ChartPanel(freq2, names2, "Total Lotteries: "+ countlotteries));
	fJoker.getContentPane().add(new ChartPanel(freqjoker, namesJoker, "Total Lotteries: "+ countlotteries));
	
	fpart1.dispatchEvent(new WindowEvent(fpart1, WindowEvent.WINDOW_CLOSING));
    fpart1.setVisible(true);
    
    
      fpart2.dispatchEvent(new WindowEvent(fpart2, WindowEvent.WINDOW_CLOSING));
      fpart2.setVisible(true);
      
      fJoker.dispatchEvent(new WindowEvent(fJoker, WindowEvent.WINDOW_CLOSING));
        fJoker.setVisible(true);
  }
}
