package cn.com.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.dao.RosterValueDao;
import cn.com.entity.RosterValue;
import cn.com.util.MD5Util;

public class RosterService {
	
	public static void main(String[] args) throws IOException {
//		String path = "E:\\智卓创新\\数据\\fraud_data.zip\\光大数据\\2015年";
		String path = "C:\\Users\\robin\\Desktop\\新建文件夹\\银联\\excel\\银联\\IP.xlsx";
		readFile(path);
		
		/* 遍历文件夹下所有excel文件 */
//		File file = new File(path);
		// 判断文件是否存在
//		if(!file.exists()) {
//			System.out.println("path:" + "not exists");
//			return;
//		}
//		File[] files = file.listFiles();
//		for(int i = 0;i < files.length;i++) {
//			File f = files[i];
//			if(f.isDirectory()) {
//				System.out.println(f.getName() + ":is directory");
//			}else if(f.isFile()) {
//				System.out.println(f.getPath() + ":is File");
//				readFile(f.getPath());
//			}
//		}
	}
	
	public static void readFile(String path) throws IOException {
		/* 读取文件 */
		InputStream stream = new FileInputStream(path);
		
		/* excel */
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(stream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		
		int rowStart = xssfSheet.getFirstRowNum();
		int rowEnd = xssfSheet.getLastRowNum();
		int j = 5988;
		
		/* 循环遍历excel文件行数据 */
		for(int i = rowStart;i < rowEnd;i++) {
			j++;
			XSSFRow row = xssfSheet.getRow(i);
			if(null == row) {
				continue;
			}
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();
			RosterValue r1 = new RosterValue();
			RosterValueDao rDao = new RosterValueDao();
			
			/* 遍历excel表列数据 */
			for(int k = cellStart;k < cellEnd;k++) {
				XSSFCell cell = row.getCell(k);
				if(cell == null) {
					continue;
				}
				if(k == 0) {
					System.out.println("date:" + cell.getStringCellValue());
					try {
						Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(cell.getStringCellValue());
						String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
						date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);  
						long enableTime = date.getTime();
						r1.setEnableTime(enableTime);
					}catch(ParseException e) {
						e.printStackTrace();
					}
			}else if(k == 6) {
					r1.setRosterValue(MD5Util.getMD5Hex16(cell.getStringCellValue().trim()));
				}else if(k == 13) {
//					r1.setRosterValue(MD5Util.getMD5Hex16(cell.getStringCellValue().trim()));
				}else if(k == 14) {
					r1.setRemark(cell.getStringCellValue().trim());
				}
			}
			r1.setRosterId(2);
			r1.setRostervalueId(j);
			r1.setCreateTime(1510213094189L);
			System.out.println(r1);
			rDao.save(r1);
		}
	}

}

















