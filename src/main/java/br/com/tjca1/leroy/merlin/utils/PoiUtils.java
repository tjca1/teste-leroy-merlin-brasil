package br.com.tjca1.leroy.merlin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.tjca1.leroy.merlin.orm.ORM;

@Component
public class PoiUtils {

	@SuppressWarnings("resource")
	public List<ORM> extractProducts(InputStream sheetFileInpuStream) throws IOException {

		Workbook workbook = new XSSFWorkbook(sheetFileInpuStream);
		Sheet sheet = workbook.getSheetAt(0);

		Row firstRow = sheet.getRow(0);
		Cell cell = firstRow.getCell(1);

		Long categoryId = cellToLong(cell);

		List<ORM> l = new ArrayList<ORM>();

		Row row = null;
		for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);

			ORM o = new ORM();
			o.setCode(cellToLong(row.getCell(0)));
			o.setProductName(row.getCell(1).getStringCellValue());
			
			
			o.setFreeShipping(cellToString(row.getCell(2)).equals("1") ? true : false);
			o.setProductDesc(row.getCell(3).getStringCellValue());
			o.setProductPrice(new BigDecimal(row.getCell(4).getStringCellValue()));
			o.setCategoryId(categoryId);
			l.add(o);

		}

		return l;
	}

	private Long cellToLong(Cell cell) {
		if (cell.getCellType().equals(CellType.NUMERIC)) {
			return Double.valueOf(cell.getNumericCellValue()).longValue();
		} else {
			return Long.valueOf(cell.getStringCellValue());
		}
	}

	private String cellToString(Cell cell) {
		if (cell.getCellType().equals(CellType.NUMERIC)) {
			return new BigDecimal(cell.getNumericCellValue()).toBigInteger().toString();
		} else {
			return cell.getStringCellValue();
		}
	}

}
