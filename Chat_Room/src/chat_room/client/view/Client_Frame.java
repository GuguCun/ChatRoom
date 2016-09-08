package chat_room.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.common.Message;
import chat.common.MessageType;
import chat_room.client.backstage.ClienManage;
import chat_room.client.backstage.ClientConServer;
import chat_room.client.tools.ManageClientPersonCollection;
import chat_room.client.tools.Tools;
/**
 * Ⱥ�ĵĽ���
 * @author Administrator
 *
 */
public class Client_Frame extends JFrame {
	//������Ҫ�����
	private JLabel jla1;	//��ǩ
	private static JLabel jla2;
	private JTextArea jta1,jta2;	//�ı�����
	private JButton jb1;	//��ť
	private static JButton jb2;//��ť
	public JButton jb3,jb4;//��ť
	private static JList jl;	//�б�
	private JScrollPane jsp1,jsp2,jsp3;	//������
	private  ClienManage cm;	//��̨�������
	private  JFrame jf;
	private static String[] onLine = {""};	//�����û�������
	private ClientFrame client;	//��������������
	private String Name;
	private JFileChooser jfc;	//�ļ�ѡ����
	public Client_Frame(final String Name,final ClienManage cm){
		super("��ӭ"+Name+"����������");
		this.Name = Name;
		this.cm = cm;
		this.jf = this;
		Container c = this.getContentPane();
		//���ÿղ���
		this.setLayout(null);
		//���ô����С
		this.setSize(500,410);
		jla1 = new JLabel("����������");
		jla1.setBounds(365, 2, 70, 20);
		c.add(jla1);
		
		jla2 = new JLabel();
		jla2.setBounds(430, 2, 20, 20);
		c.add(jla2);
		
		jta1 = new JTextArea();
		//���ò��ɱ༭
		jta1.setEditable(false);
		jsp1 = new JScrollPane(jta1);
		jsp1.setBounds(10, 20, 350, 200);
		c.add(jsp1);
		
		jta2 = new JTextArea();
		//��ȡ���
		jta2.grabFocus();
		jsp2 = new JScrollPane(jta2);
		jsp2.setBounds(10, 225, 350, 120);
		c.add(jsp2);
		
		jl = new JList(onLine);
		jsp3 = new JScrollPane(jl);
		jsp3.setBounds(365, 20, 120, 325);
		c.add(jsp3);
		
		jb1 = new JButton("����");
		jb1.setBounds(300, 350, 60, 25);
		c.add(jb1);
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String con = jta2.getText();
	             Message mess = new Message();
	             mess.setSender(Name);
	             mess.setMessageType(MessageType.Common_Message_ToAll);
	             String time = (new Date().toLocaleString());
	             mess.setTime(time);
	             mess.setContent(con);
	             jta1.append(Name+"    "+time+"\r\n"+con+"\r\n");
	             cm.SendMessage(mess);
	             jta2.setText("");
	             //��ȡ���
	     		jta2.grabFocus();
			}
		});
		
		jb2 = new JButton("�������� ");
		jb2.setEnabled(false);
		jb2.setBounds(380, 350, 100, 25);
		//ע�ᵥ�����찴Ť���¼�����
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//���ض�λѡ��������
				int i = jl.getAnchorSelectionIndex();
				if(i==-1){
					JOptionPane.showMessageDialog(jf, "��ѡ��һ��");
				}else{
					if(!Name.equals(onLine[i])){
						String str = Name+" "+onLine[i];
						//����������������ʹ���
						if(!ManageClientPersonCollection.isExist(str)){
							client = new ClientFrame(Name, onLine[i],cm);
						}
						//�����������������뼯����
						ManageClientPersonCollection.addClientPersonCollection(str, client);
					}else{
						JOptionPane.showMessageDialog(jf,"��ѡ�����������û�");
					}
				}
			}
		});
		c.add(jb2);
		
		
		jb3 = new JButton("�����ļ�");
		jb3.setBounds(10, 350, 100, 25);
		//�����ļ���Ťע���¼�����
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc = new JFileChooser();
				jfc.showOpenDialog(jf);
				Message mess = new Message();
				mess.setMessageType(MessageType.Send_FileToAll);
				mess.setSender(Name);
				//�����ļ���
				mess.setContent(jfc.getName(jfc.getSelectedFile()));
				//���·��
				//ѡ���˲��ܷ���
				if(jfc.getSelectedFile().toPath().toString()!=null){
					//������Ϣ����
					cm.SendMessage(mess);
					String path = jfc.getSelectedFile().toPath().toString();
					//�����ļ�
					cm.SendFile(path);
				}
			}
		});
		c.add(jb3);

		jb4 = new JButton("��������¼");
		jb4.setBounds(150, 350, 120, 25);
		//��������¼��Ťע���¼�����
		jb4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jta1.setText("");
			}
		});
		c.add(jb4);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô����С���ɸı�
		this.setResizable(false);
		Tools.setFrameCenter(this);
		this.setVisible(true);
		
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	/**
	 * ����ͨ��Ϣ��ʾ��Ⱥ�Ĵ���
	 * @param mess
	 */
	public void showMessageToAll(Message mess){
		jta1.append(mess.getSender()+"    "+mess.getTime()+"\r\n"+mess.getContent()+"\r\n");
	}
	
	public void ShowSystemMessage(Message mess){
		jta1.append(mess.getContent());
	}
	
	
	
	/**
     * ���������û��ķ���
     * @param strings �����û����ַ���
     */
    public static void SetOnLline(String string){
    	if(string.equals("")){
    		jb2.setEnabled(false);
	    		final String[] strings = {""};
	    		//ͬʱ��ʾ��������
	       	 ShowOnlineNumber("0");
	       	jl.setModel(new AbstractListModel() {
	   		 
	            public int getSize(){ 
	           	 return strings.length; 
	            }
	   		 
	            public Object getElementAt(int i){ 
	           	 return strings[i]; 
	           	 }
	            
	        });
    	}else{
    		jb2.setEnabled(true);
	    	 final String[] strings = string.split(" ");
	    	 onLine = strings;
	    	 //ͬʱ��ʾ��������
	    	 ShowOnlineNumber(new Integer(strings.length).toString());
	    	 jl.setModel(new AbstractListModel() {
	    		 
	             public int getSize(){ 
	            	 return strings.length; 
	             }
	    		 
	             public Object getElementAt(int i){ 
	            	 return strings[i]; 
	            	 }
	             
	         });
    	}
    }
	
    /**
     * ��ʾ�Ի���
     * @param s ��Ϣ
     */
    public void ShowMessageDialog(String s){
    	JOptionPane.showMessageDialog(jf, s);
    	jf.dispose();
    }
    
    
   

	/**
     * ��ʾ���������ķ���
     * @param num ����
     */
    public static void ShowOnlineNumber(String num){
    	jla2.setText(num);
    }

    /**
     * �����û���ѡ�񷵻�ֵ
     * @return
     */
    public int ShowIsRecive(){
    	int i = JOptionPane.showConfirmDialog(jf, "�Ƿ�Ҫ�����ļ�");
    	return i;
    }
    
	
}

