package katalon.utility

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

public class ExcelHelper {
	String path;
	XSSFWorkbook workbook;

	public ExcelHelper(String path) {
		this.path = path;
	}

	public void openWorkbook(String path) {
		try {
			this.workbook = new XSSFWorkbook(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeWorkbook(){
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			this.workbook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeWorkbook(){
		try {
			this.workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public XSSFSheet copySheet(String originalSheetName, String newSheetName){
		openWorkbook(path);

		// Check sheet existed -> delete
		if(workbook.getSheetIndex(newSheetName)>0)
			workbook.removeSheetAt((workbook.getSheetIndex(newSheetName)));

		XSSFSheet newSheet = workbook.cloneSheet(workbook.getSheetIndex(originalSheetName), newSheetName);

		writeWorkbook();
		closeWorkbook();

		return newSheet;
	}
}
