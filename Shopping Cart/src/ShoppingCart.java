import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCart  extends JFrame implements ActionListener{
	
	int sum=0;
	JPanel panel1, panel2, panel3;
	JLabel label_name, label_price, label_count, label_sum, label_productnum,  label_sumprice;
	JCheckBox checkbox[]= new JCheckBox[3];
	JTextField textfield[] = new JTextField[10],	textfield_productnum, textfield_sumprice;
	ShoppingCart()
	{
		Container f=getContentPane();
		f.setLayout(new GridLayout(3, 0));
		
		panel1 = new JPanel(new GridLayout(0, 4));
		label_name = new JLabel("商品名称");
		label_price = new JLabel("单价");
		label_count = new JLabel("数量");
		label_sum = new JLabel("小计(元)");
		panel1.add(label_name);
		panel1.add(label_price);
		panel1.add(label_count);
		panel1.add(label_sum);
		
		panel2 = new JPanel(new GridLayout(3, 4));
		checkbox[0] = new JCheckBox("牛仔裤");
		checkbox[1] = new JCheckBox("T恤衫");
		checkbox[2] = new JCheckBox("牛仔短裤");
		
		for(int i=0; i<10; i++)
		{
			textfield[i] = new JTextField(10);
		}
		textfield[1].setEditable(false);
		textfield[4].setEditable(false);
		textfield[7].setEditable(false);
		textfield[3].setEditable(false);
		textfield[6].setEditable(false);
		textfield[9].setEditable(false);
		textfield[1].setText("62");
		textfield[4].setText("42");
		textfield[7].setText("65");

		
		panel2.add(checkbox[0]);
		panel2.add(textfield[1]);
		panel2.add(textfield[2]);
		panel2.add(textfield[3]);
		
		panel2.add(checkbox[1]);
		panel2.add(textfield[4]);
		panel2.add(textfield[5]);
		panel2.add(textfield[6]);
		
		panel2.add(checkbox[2]);
		panel2.add(textfield[7]);
		panel2.add(textfield[8]);
		panel2.add(textfield[9]);
		
		panel3 = new JPanel(new GridLayout(0, 5));
		label_productnum = new JLabel("合计件数:");
		label_sumprice = new JLabel("商品总价(不含运费):");
		textfield_productnum= new JTextField(10);
		textfield_sumprice= new JTextField(10);
		textfield_productnum.setEditable(false);
		textfield_sumprice.setEditable(false);
		JButton button_B1 =new JButton("结算");
		panel3.add(label_productnum);
		panel3.add(textfield_productnum);
		panel3.add(label_sumprice);
		panel3.add(textfield_sumprice);
		panel3.add(button_B1);
		
		f.add(panel1);
		f.add(panel2);
		f.add(panel3);
		
		button_B1.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		sum=0;
		int a, flag=0;
		
		if(checkbox[0].isSelected() && !textfield[2].equals(""))
		{
			a=Integer.parseInt(textfield[1].getText())   * Integer.parseInt(textfield[2].getText());
			textfield[3].setText(Integer.toString(a));
			sum+=a;
		}
		if(checkbox[1].isSelected() && !textfield[5].equals(""))
		{
			a=Integer.parseInt(textfield[4].getText())   * Integer.parseInt(textfield[5].getText());
			textfield[6].setText(Integer.toString(a));
			sum+=a;
		}
		if(checkbox[2].isSelected() && !textfield[8].equals(""))
		{
			a=Integer.parseInt(textfield[7].getText())   * Integer.parseInt(textfield[8].getText());
			textfield[9].setText(Integer.toString(a));
			sum+=a;
		}
		for(int i=0; i<3; i++)
		{
			if(checkbox[i].isSelected())
				flag++;
		}
		textfield_productnum.setText(Integer.toString(flag));
		textfield_sumprice.setText(Integer.toString(sum));
	}
	
	public static void main(String args[])
	{
		ShoppingCart S1=new ShoppingCart();
		S1.setVisible(true);
		S1.setBounds(300,200,600,300);
	}

}
