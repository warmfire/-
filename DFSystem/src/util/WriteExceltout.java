package util;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import dao.TeacherGradesDatabaseDao;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExceltout {
	public WriteExceltout() {
		TeacherGradesDatabaseDao tgdd = new TeacherGradesDatabaseDao();
		String title[] = {"id", "课程", "班级", "姓名", "学号", "组别", "总分", "打分人"};
		String[][] context = null;
		ArrayList<ArrayList<String>> listAll = tgdd.setContent();
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"d:\\教师成绩表.xls"));
			WritableSheet sheet = book.createSheet("教师打分", 0);

			for(int i = 0; i < 8; i++)
				sheet.addCell(new Label(i, 0, title[i]));
			
			for(int i = 0;i < listAll.size();i++){
			    for(int j = 0; j < listAll.get(i).size(); j++)
			    {
			    	sheet.addCell(new Label(j, i+1, listAll.get(i).get(j)));
			    }
			}

			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}