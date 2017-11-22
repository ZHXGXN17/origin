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

public class IpService {
	
	public static void main(String[] args) throws IOException {
//		readFile("C:\\Users\\robin\\Desktop\\新建文件夹\\银联\\excel\\银联\\IP.xlsx");
//		readFile("C:\\Users\\robin\\Desktop\\新建文件夹\\银联\\excel\\银联\\卡号.xlsx");
		readFile("C:\\Users\\robin\\Desktop\\新建文件夹\\银联\\excel\\银联\\手机号.xlsx");
	}
	
	/* 读取excel文件方法，按行、列遍历  */
	public static void readFile(String path) throws IOException {
		/* 读取文件 */
		InputStream stream = new FileInputStream(path);
		
		/* excel */
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(stream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		
		int rowStart = xssfSheet.getFirstRowNum();
		int rowEnd = xssfSheet.getLastRowNum();
		int j = 27133;
		
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
			RosterValueDao dao = new RosterValueDao();
			
			/* 遍历excel表列数据 */
			for(int k = cellStart;k < cellEnd;k++) {
				XSSFCell cell = row.getCell(k);
				if(cell == null) {
					continue;
				}
				cell.setCellType(cell.CELL_TYPE_STRING);
//				System.out.println("ss:" + cell.getStringCellValue());
				if(k == 0) {
					String str1 = cell.getStringCellValue();
					if(str1.contains(".")) {
						str1 = str1.replace('.', '-');
						try {
							Date date = new SimpleDateFormat("yyyyMMdd").parse(str1);
							String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
							date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);  
							long enableTime = date.getTime();
							r1.setEnableTime(enableTime);
						}catch(ParseException e) {
							e.printStackTrace();
						}
					}else {
						try {
							Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(cell.getStringCellValue());
							String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
							date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);  
							long enableTime = date.getTime();
							r1.setEnableTime(enableTime);
						}catch(ParseException e) {
							e.printStackTrace();
						}
					}
				}else if(k == 1) {
					r1.setRosterValue(MD5Util.getMD5Hex16(cell.getStringCellValue().trim()));
				}else if(k == 3) {
					r1.setRemark(cell.getStringCellValue().trim());
				}
			}
			r1.setRosterId(7);
			r1.setRostervalueId(j);
			r1.setCreateTime(1510213094189L);
			System.out.println(r1);
			dao.save(r1);
		}
	}

}
