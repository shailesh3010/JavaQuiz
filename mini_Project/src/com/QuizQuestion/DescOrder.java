package com.QuizQuestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DescOrder {

	public void getorder() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "root");
			Statement stmt2 = con.createStatement();
			String sql1 = "select Studentid,StudentName,Score from studentresult order by score DESC ";

			ResultSet rs2 = stmt2.executeQuery(sql1);
			while (rs2.next()) {
				System.out.printf("%-10d%-20s%-10d", rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
				System.out.println();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
