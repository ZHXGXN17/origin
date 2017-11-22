package cn.com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.dao.RosterDao;
import cn.com.entity.Roster;

public class ReadUtil {
	public static void main(String[] args) throws IOException {
		/* 读取文件 */
		InputStream stream = new FileInputStream("E:\\智卓创新\\数据\\fraud_data.zip\\光大数据\\1月后半月数据.xlsx");
		
		/* excel */
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(stream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		
		int rowStart = xssfSheet.getFirstRowNum();
		int rowEnd = xssfSheet.getLastRowNum();
		
		/* 循环遍历excel文件行数据 */
		for(int i = rowStart;i < rowEnd;i++) {
			XSSFRow row = xssfSheet.getRow(i);
			if(null == row) {
				continue;
			}
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();
			Roster roster = new Roster();
			RosterDao dao = new RosterDao();
			
			/* 遍历excel表列数据 */
			for(int k = cellStart;k < cellEnd;k++) {
				XSSFCell cell = row.getCell(k);
				if(cell == null) {
					continue;
				}
				if(k == 3) {
					System.out.println("ss:" + cell.getStringCellValue());
					try {
						Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(cell.getStringCellValue());
						String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
						date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);  
						long enableTime = date.getTime() / 1000;
						roster.setEnableTime(enableTime);
					}catch(ParseException e) {
						e.printStackTrace();
					}
				}else if(k == 6 ) {
					roster.setPayAcc(cell.getStringCellValue());
				}else if(k == 7) {
					roster.setReyAcc(cell.getStringCellValue());
				}else if(k == 14) {
					roster.setRemark(cell.getStringCellValue());
				}
				switch(cell.getCellType()) {
				// 字符串
				case HSSFCell.CELL_TYPE_STRING: 
//					System.out.println(cell.getStringCellValue() + "\t");
					break;
			    // 数字
				case HSSFCell.CELL_TYPE_NUMERIC:
//					System.out.println(cell.getNumericCellValue() + "\t");
					break;
				// 空值
				case HSSFCell.CELL_TYPE_BLANK:
//					System.out.println("");
					break;
				}
			}
			System.out.println(roster);
			dao.save(roster);
		}
	}

}
