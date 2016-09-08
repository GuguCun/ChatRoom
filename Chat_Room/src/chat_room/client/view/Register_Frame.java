package chat_room.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chat.common.User;
import chat_room.client.backstage.ClienManage;
import chat_room.client.tools.Tools;
/**
 * ע��Ľ���
 * @author Administrator
 *
 */
public class Register_Frame extends JFrame {
	//������Ҫ�����
	private JLabel jl1,jl2,jl3;	//��ǩ
	private JTextField jtf;	//�ı���
	private JPasswordField jpf1,jpf2;	//�����ı���
	private JButton jb1,jb2,jb3;	//��ť
	private JFrame jf;
	public Register_Frame(){
		super("ע�����");
		this.jf = this;
		this.setLayout(null);
		this.setSize(400,300);
		Container c = this.getContentPane();
		jl1 = new JLabel("�û���");
		jl1.setBounds(100, 60, 50, 20);
		c.add(jl1);
		
		jtf = new JTextField();
		//��ȡ���
		jtf.grabFocus();
		jtf.setBounds(160, 60, 120, 20);
		c.add(jtf);
		
		jl2 = new JLabel("����");
		jl2.setBounds(100, 110, 50, 20);
		c.add(jl2);
		
		jpf1 = new JPasswordField();
		jpf1.setBounds(160, 110, 120, 20);
		c.add(jpf1);
		
		jl3 = new JLabel("ȷ������");
		jl3.setBounds(100, 162, 70, 20);
		c.add(jl3);
		
		jpf2 = new JPasswordField();
		jpf2.setBounds(160, 162, 120, 20);
		c.add(jpf2);
		
		
		jb1 = new JButton("ע��");
		jb1.setBounds(90, 210, 60, 20);
		c.add(jb1);
		 //ע�ᰴŤ����¼�����
        jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String Name = jtf.getText();
				String PassWord = new String(jpf1.getPassword());
				String RePassWord = new String(jpf2.getPassword());
				if(Name.equals("")||PassWord.equals("")){
					JOptionPane.showMessageDialog(jf, "�û��������벻��Ϊ��");
				}else if(RePassWord.equals("")){
					JOptionPane.showMessageDialog(jf, "��ȷ������");
				}else if(!RePassWord.equals(PassWord)){
					JOptionPane.showMessageDialog(jf, "ȷ���������");
				}else{
					User user = new User(Name, PassWord);
					ClienManage cm = new ClienManage();
					if(cm.IsConnect()){
						if(cm.Register(user)){
							JOptionPane.showMessageDialog(jf, "ע��ɹ�");
							jf.dispose();
							Login_Frame login = new Login_Frame();
						}else{
							JOptionPane.showMessageDialog(jf, "ע��ʧ��");
							jf.dispose();
						}
					}else{
						JOptionPane.showMessageDialog(jf, "���Ӳ���������");
						jf.dispose();
					}
				}
				
			}
		});
		jb2 = new JButton("����");
		jb2.setBounds(170, 210, 60, 20);
		c.add(jb2);
		//���ð�Ťע���¼�����
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf.setText("");
				jpf1.setText("");
				jpf2.setText("");
				//��ȡ���
				jtf.grabFocus();
			}
		});
		
		jb3 = new JButton("ȡ��");
		jb3.setBounds(250, 210, 60, 20);
		c.add(jb3);
		//ȡ����Ťע���¼�����
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.dispose();
				Login_Frame login = new Login_Frame();
			}
		});
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tools.setFrameCenter(this);
		this.setVisible(true);
	}
	
	
}