/**
 * 
 */
package DataProvider;

import org.testng.annotations.DataProvider;

/**
 * @author 
 *
 */
public class DataProviders {

	Utils.NewExcelLibrary obj = new Utils.NewExcelLibrary();

//Class --> LoginPageTest Test Case--> loginTest

	@DataProvider(name = "credentials")
	public Object[][] getCredentials() {
		// Totals rows count
		int rows = obj.getRowCount("Credentials");
		// Total Columns
		int column = obj.getColumnCount("Credentials");
		int actRows = rows - 1;

		Object[][] data = new Object[actRows][column];

		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < column; j++) {
				data[i][j] = obj.getCellData("Credentials", j, i + 2);
			}
		}
		return data;
	}


}
