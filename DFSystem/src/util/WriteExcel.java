package util;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import dao.InGradesDatabaseDao;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExcel {
	public WriteExcel() {
		InGradesDatabaseDao igdd = new InGradesDatabaseDao();
		String title[] = {"id", "����", "ѧ��", "�γ�", "���", "��ɫ", "������", "����", "̬��", "����", "����", "������", "�ܷ�", "�����"};
		String[][] context = null;
		ArrayList<ArrayList<String>> listAll = igdd.setContent();
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"d:\\���ڳɼ���.xls"));
			WritableSheet sheet = book.createSheet("���ڴ��", 0);
			for(int i = 0; i < 14; i++)
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