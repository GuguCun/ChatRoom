package chat_room.client.backstage;

import java.io.ObjectOutputStream;
import java.util.Set;

import javax.swing.JOptionPane;

import chat.common.Message;
import chat.common.MessageType;
import chat_room.client.tools.ManageClientCollction;
import chat_room.client.tools.ManageClientPersonCollection;
import chat_room.client.view.ClientFrame;
import chat_room.client.view.Client_Frame;

/**
 * �ͻ��˼������������ͨ�ŵĺ�̨�߳���
 * @author Administrator
 *	Client_Continue_Connect_Server_Thread
 */
public class ClientConServer implements Runnable{
	private ClienManage cm;	//��̨�������
	private ClientFrame client;//��������������
	private Client_Frame cf;//Ⱥ�Ľ������
	

	public ClientConServer(ClienManage cm,Client_Frame cf){
		this.cm = cm;
		this.cf = cf;
	}
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		while(true){
			//��ͣ�Ľ�����Ϣ
			Message mess = cm.ReciveMessage();
			if(cm.IsConnect()){
				//���ݲ�ͬ����Ϣ���ʹ�����Ϣ
				if(mess!=null){
					if(mess.getMessageType().equals(MessageType.Common_Message_ToAll)){
						//���ݻ���߻��Ⱥ�Ĵ���
						Client_Frame Client = ManageClientCollction.GetClient_Frame(mess.getGetter());
						//����Ϣ��ʾ��Ⱥ�Ĵ���
						Client.showMessageToAll(mess);
					}else if(mess.getMessageType().equals(MessageType.Send_Online)){
						//�����ʾ���������û�
						//��������û�
						String string = mess.getContent();
						//���ݻ���߻��Ⱥ�Ĵ���
						Client_Frame Client = ManageClientCollction.GetClient_Frame(mess.getGetter());
						//��ʾ�����û�
						Client.SetOnLline(string);
					}else if(mess.getMessageType().equals(MessageType.System_Messages)){
						//�����ʾ��ϵͳ��Ϣ
						//���ݻ���߻��Ⱥ�Ĵ���
						Client_Frame ClientFrame = ManageClientCollction.GetClient_Frame(mess.getGetter());
						//����Ϣ��ʾ��Ⱥ�Ĵ���
						ClientFrame.ShowSystemMessage(mess);
					}else if(mess.getMessageType().equals(MessageType.Common_Message_ToPerson)){
						//�����ʾ���͸�����
						//�����ַ����ҵ�ָ���ĸ����������
						String str = mess.getGetter()+" "+mess.getSender();
						client = ManageClientPersonCollection.getClientPerson(str);
						//���û�ҵ��ʹ���һ�������������
						if(client==null){
							client = new ClientFrame(mess.getGetter(), mess.getSender(), cm);
							//���������������Ľ��漯��
							ManageClientPersonCollection.addClientPersonCollection(str, client);
						}
						//��ʾ��Ϣ
						client.ShowMessage(mess);
					}else if(mess.getMessageType().equals(MessageType.Send_FileToAll)){
						//�����ļ���������
						cm.ReciveFile(mess);
					
					}else if(mess.getMessageType().equals(MessageType.Send_FileToPerson)){
						//�����ļ�������
						cm.ReciveFile(mess);
					}
				}
			}else{
				//�����Ի��򲢹رմ���
				if(cf!=null){
					String Name = cf.getName();
					cf.ShowMessageDialog("���ӷ������ж�");
					//�ر����и������촰��
					ClosePersonClient(Name);
					//�ر���Դ
					cm.CloseResource();
				}
				break;
			}
		}
	}
	
	
	/**
	 * �ر������и������촰��
	 * @param Name ��Ⱥ�Ĵ��ڵ�����
	 */
	public void ClosePersonClient(String Name){
		String[] strings;
		Set<String> set = ManageClientPersonCollection.getClientPersonSet();
		for(String s : set){
			strings = s.split(" ");
			if(strings[0].equals(Name)){
				ClientFrame cf = ManageClientPersonCollection.getClientPerson(s);
				//�رո������촰��
				cf.dispose();
				//�Ƴ��������촰��
				ManageClientPersonCollection.removeClientPerson(s);
			}
		}
	}

}
