package com.wanghua3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SeparateSocket extends Thread {
	Socket socket;
    byte[] inputByte = null;
    int length = 0;
    long i=0;
    DataInputStream dis = null;
    FileOutputStream fos = null;
	public SeparateSocket(Socket s) {
		this.socket = s;
	}

	public void in() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
			socket.getInputStream()));
			String msg = null;
			while ((msg = reader.readLine()) != null) {
				receiveFile();
				System.out.println(msg);
			}
		} catch (IOException e) {
			
		}
	}

	public void receiveFile() {
		try {
            try {
                dis = new DataInputStream(socket.getInputStream());
                File fileDir = new File("E:/data");//������·����File����
                if(!fileDir.exists()){
                    fileDir.mkdir();//���·�������ھ��ȴ���·��
                }
                File file = new File(fileDir,"data"+i+".txt");//Ȼ���ٴ���·�����ļ���File����
                if(!file.exists()) {
                    file.createNewFile();
                }
                else{
                    file.delete();
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                //inputByte = new byte[100];
                String str="\n";
                    while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
                        fos.write(inputByte, 0, length);
                        fos.write(str.getBytes(),0,length);
                        fos.flush();
                    }
            } finally {
                if (fos != null)
                    fos.close();
                if (dis != null)
                    dis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void run() {
		in();
	}

}