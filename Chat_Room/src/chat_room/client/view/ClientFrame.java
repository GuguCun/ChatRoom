package chat_room.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.common.Message;
import chat.common.MessageType;
import chat_room.client.backstage.ClienManage;
import chat_room.client.tools.ManageClientPersonCollection;
import chat_room.client.tools.Tools;

/**
 * ��������Ľ���
 * @author Administrator
 *
 */
public class ClientFrame extends JFrame implements WindowListener{
	private static JTextArea jta1;	//�ı�����
	private JTextArea jta2;
	private JButton jb1,jb2,jb3;	//��Ť
	private JScrollPane jsp1,jsp2;	//������
	private ClienManage cm;	//��̨�������
	private JFileChooser jfc;	//�ļ�ѡ����
	private String Sender;
	private String Getter;
	private JFrame jf;
	public ClientFrame(final String Sender,final String Getter,final ClienManage cm){
		super(Sender+"������"+Getter+"������");
		this.jf = this;
		this.cm = cm;
		this.Sender = Sender;
		this.Getter = Getter;
		Container c = this.getContentPane();
		//���ô�С
		this.setSize(400, 380);
		//���ÿղ���
		this.setLayout(null);
		
		jta1 = new JTextArea();
		//���ò��ɱ༭
		jsp1 = new JScrollPane(jta1);
		jta1.setEditable(false);
		jsp1.setBounds(10, 10, 375, 190);
		c.add(jsp1);
		
		jta2 = new JTextArea();
		jsp2 = new JScrollPane(jta2);
		//��ȡ���
		jta2.grabFocus();
		jsp2.setBounds(10, 205, 375, 110);
		c.add(jsp2);
		
		
		jb1 = new JButton("����");
		jb1.setBounds(320, 320, 60,20);
		//���Ͱ�Ťע���¼�����
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String con = jta2.getText();
				String Time = (new Date()).toLocaleString();
				Message mess = new Message();
				mess.setContent(con);
				mess.setTime(Time);
				mess.setSender(Sender);
				mess.setGetter(Getter);
				mess.setMessageType(MessageType.Common_Message_ToPerson);
				jta1.append(Sender+"    "+Time+"\r\n"+con+"\r\n");
				//������Ϣ
				cm.SendMessage(mess);
				jta2.setText("");
				//��ȡ���
				jta2.grabFocus();
			}
		});
		c.add(jb1);
		
		jb2 = new JButton("�����ļ�");
		jb2.setBounds(10, 320, 100, 20);
		//�����ļ���Ťע���¼�����
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc = new JFileChooser();
				jfc.showOpenDialog(jf);
				Message mess = new Message();
				mess.setMessageType(MessageType.Send_FileToPerson);
				mess.setSender(Sender);
				mess.setGetter(Getter);
				String FileName = jfc.getName(jfc.getSelectedFile());
				//�����ļ���
				mess.setContent(FileName);
				
				if( jfc.getSelectedFile().toPath().toString()!=null){
					//������Ϣ����
					cm.SendMessage(mess);
					//���·��
					String path = jfc.getSelectedFile().toPath().toString();
					//�����ļ�
					cm.SendFile(path);
					Message m = new Message();
					m.setMessageType(MessageType.Common_Message_ToPerson);
					m.setSender(Sender);
					m.setGetter(Getter);
					m.setTime(new Date().toLocaleString());
					m.setContent("�Ҹ��㷢�����ļ���Ϊ��"+FileName+" ���ļ�\r\n");
					ShowMessage(m);
					cm.SendMessage(m);
				}
			}
		});
		c.add(jb2);
		
		jb3 = new JButton("��������¼");
		jb3.setBounds(150, 320, 120,20);
		//��������¼��Ťע���¼�����
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jta1.setText("");
			}
		});
		c.add(jb3);
		
		//ע�ᴰ���¼�����
		this.addWindowListener(this);
		//���ô�С���ɸı�
		this.setResizable(false);
		//��������Ļ�м�
		Tools.setFrameCenter(this);
		this.setVisible(true);
	}
	
	
	/***
	 * ��ʾ��Ϣ�ڸ����������
	 * @param mess
	 */
	public static void ShowMessage(Message mess){
		jta1.append(mess.getSender()+"    "+mess.getTime()+"\r\n"+mess.getContent()+"\r\n");
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		String str = Sender+" "+Getter;
		ManageClientPersonCollection.removeClientPerson(str);
	}


	@Override
	public void windowClosed(WindowEvent e) {
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
