package util;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import dao.OutGradesDatabaseDao;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExcelout {
	public WriteExcelout() {
		OutGradesDatabaseDao ogdd = new OutGradesDatabaseDao();
		String title[] = {"id", "姓名", "学号", "课程", "班级", "组别", "工作量", "创新", "技术", "美观", "进步", "总分", "打分人"};
		String[][] context = null;
		ArrayList<ArrayList<String>> listAll = ogdd.setContent();
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"d:\\组间成绩表.xls"));
			WritableSheet sheet = book.createSheet("组间打分", 0);

			for(int i = 0; i < 13; i++)
				sheet.addCell(new Label(i, 0, title[i]));
			
			for(int i = 0;i < listAll.size();i++){
			    for(int j = 0; j < listAll.get(i).size(); j++)
			    {
//			    	System.out.println(listAll.get(i).get(j));
			    	sheet.addCell(new Label(j, i+1, listAll.get(i).get(j)));
			    }
//			    System.out.println("11111111111111111111111111111111111111");
			}
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}