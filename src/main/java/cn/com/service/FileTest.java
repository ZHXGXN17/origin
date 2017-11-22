package cn.com.service;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		String path = "E:\\智卓创新\\数据\\fraud_data.zip\\光大数据\\2016年";
		File file = new File(path);
		// 判断文件是否存在
		if(!file.exists()) {
			System.out.println("path:" + "not exists");
			return;
		}
		File[] files = file.listFiles();
		for(int i = 0;i < files.length;i++) {
			File f = files[i];
			if(f.isDirectory()) {
				System.out.println(f.getName() + ":is directory");
			}else if(f.isFile()) {
				System.out.println(f.getPath() + ":is File");
			}
		}
		
	}

}
