package jdbcCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {
	
	// ���������� ����� �������, ���������� ��������� ��������.
	private Integer affected_rows = 0;
	
	// �������� ��������������������� ���������� �����, ���������� �����
	// ���������� ����� ������.
	private Integer last_insert_id = 0;

	// ��������� �� ��������� ������.
	private static DBWorker instance = null;
	
	private String path = "jdbc:mysql://localhost/new_schema?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";
	
	// ����� ��� ��������� ���������� ������ (���������� Singleton).
	public static DBWorker getInstance()
	{
		if (instance == null)
		{
			instance = new DBWorker();
		}
	
		return instance;
	}
	
	// "��������", ����� ��������� ������ ������ ���� �������� ��������.
	private DBWorker()
	{
	 // ������ "��������".			
	}
	
	// ���������� �������� �� ������� ������.
	public ResultSet getDBData(String query)
	{
		Statement statement;
		Connection connect;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(path);
			statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("null on getDBData()!");
		return null;

	}
	
	// ���������� �������� �� ����������� ������.
	public Integer changeDBData(String query)
	{
		Statement statement;
		Connection connect;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(path);
			statement = connect.createStatement();
			this.affected_rows = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		
			// �������� last_insert_id() ��� �������� �������.
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
            	this.last_insert_id = rs.getInt(1);
            }
			
			return this.affected_rows;
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("null on changeDBData()!");
		return null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++
	// ������� � �������.
	public Integer getAffectedRowsCount()
	{
		return this.affected_rows;
	}
	
	public Integer getLastInsertId()
	{
		return this.last_insert_id;
	}
	// ������� � �������.
	// -------------------------------------------------
}

