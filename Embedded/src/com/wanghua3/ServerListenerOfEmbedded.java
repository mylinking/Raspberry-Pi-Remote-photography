package com.wanghua3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListenerOfEmbedded extends Thread {
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket=new ServerSocket(12345);
			System.out.println("�������Ѿ�����......");
			while (true) {
				//����
				Socket socket=serverSocket.accept();
				//��������
				JOptionPane.showMessageDialog(null,"�пͻ������ӵ��˱�����12345�˿�");
				System.out.println("������");
				//��socket���ݸ��µ��߳�
				new SeparateSocket(socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
