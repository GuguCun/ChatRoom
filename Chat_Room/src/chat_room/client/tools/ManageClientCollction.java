package chat_room.client.tools;

import java.util.HashMap;

import chat_room.client.view.Client_Frame;

/**
 * ����Ⱥ�Ľ���ļ���
 * @author Administrator
 *
 */
public class ManageClientCollction {
	private	static HashMap<String, Client_Frame> hm = new HashMap<String, Client_Frame>();
	
	/**
	 * ��ӹ��������ҽ���ļ���
	 * @param user �û�
	 * @param client_Frame �����ҽ���
	 */
	public static void addClientCollction(String user,Client_Frame client_Frame){
		hm.put(user, client_Frame);
	}
	/**
	 * �����û����������ҽ���
	 * @param user �û�
	 */
	public static Client_Frame GetClient_Frame(String user){
		return hm.get(user);
	}
}
