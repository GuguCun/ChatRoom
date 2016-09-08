package chat_room.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chat.common.User;
import chat_room.client.backstage.ClienManage;
import chat_room.client.backstage.ClientConServer;
import chat_room.client.tools.ClientThreadCollection;
import chat_room.client.tools.ManageClientCollction;
import chat_room.client.tools.Tools;
/**
 * ��½�Ľ���
 * @author Administrator
 *
 */
public class Login_Frame extends JFrame {
	//������Ҫ�����
	private JLabel jl1,jl2;	//��ǩ
	private JTextField jtf;	//�ı���
	private JPasswordField jpf;	//�����ı���
	private JButton jb1,jb2,jb3;	//��ť
	private JFrame jf;
	public Login_Frame(){
		super("��¼����");
		this.jf = this;
		this.setLayout(null);
		this.setSize(400,300);
		Container c = this.getContentPane();
		jl1 = new JLabel("�û���");
		jl1.setBounds(100, 60, 50, 20);
		c.add(jl1);
		
		jtf = new JTextField();
		//��ù��
		jtf.grabFocus();
		jtf.setBounds(160, 60, 120, 20);
		c.add(jtf);
		
		jl2 = new JLabel("����");
		jl2.setBounds(100, 140, 50, 20);
		c.add(jl2);
		
		jpf = new JPasswordField();
		jpf.setBounds(160, 140, 120, 20);
		c.add(jpf);
		
		jb1 = new JButton("��¼");
		jb1.setBounds(90, 210, 60, 20);
		c.add(jb1);
		 //��½��Ť����¼�����
        jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String Name = jtf.getText();
				String PassWord = new String(jpf.getPassword());
				if(Name.equals("")){
					JOptionPane.showMessageDialog(jf, "�û�������Ϊ��");
				}else if(PassWord.equals("")){
					JOptionPane.showMessageDialog(jf, "���벻��Ϊ��");
				}else{
					User user = new User(Name, PassWord);
					ClienManage cm = new ClienManage();
					if(cm.IsConnect()){
						if(!cm.Check_isLogin(user)){
							if(cm.Login()){
								JOptionPane.showMessageDialog(jf, "��½�ɹ�");
								jf.dispose();
								Client_Frame client = new Client_Frame(user.getName(),cm);
								//����������
								String name = user.getName();
								//�����������漯��
								ManageClientCollction.addClientCollction(name, client);
								ClientConServer clientConServer = new ClientConServer(cm,client);
								//�����̶߳�������߳��򼯺�
								ClientThreadCollection.addClientThreadCollection(user.getName(), clientConServer);
								//�����ͻ��˼������������ͨ�ŵĺ�̨�߳�
								Thread t = new Thread(clientConServer);
								t.start();
							}else{
								JOptionPane.showMessageDialog(jf, "�û������������");
								return ;
							}
						}else{
							JOptionPane.showMessageDialog(jf, "�û��Ѿ���½��");
							return ;
						}
					}else{
						JOptionPane.showMessageDialog(jf,"���Ӳ���������");
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
				jpf.setText("");
				//��ù��
				jtf.grabFocus();
			}
		});
		jb3 = new JButton("ע��");
		jb3.setBounds(250, 210, 60, 20);
		c.add(jb3);
	    //ע�ᰴŤ����¼�����
	    jb3.addActionListener(new ActionListener() {
				
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//����ע�����
				Register_Frame register = new Register_Frame();
				jf.dispose();
			}
		});
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tools.setFrameCenter(this);
		this.setVisible(true);
	}
	
	
}
