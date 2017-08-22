package jdbcCore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsingDBWorker {
	public void usingDBWorker() {
		DBWorker db = DBWorker.getInstance();
		ResultSet result = db.getDBData("Select * from new;");
		try {
			while (result.next()) {
				System.out.println(result.getString("ID"));
				System.out.println(result.getString("NAME"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
