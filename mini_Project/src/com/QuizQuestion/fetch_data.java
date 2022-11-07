package com.QuizQuestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class fetch_data {

	public void getData() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "root");
			System.out.println("enter id which u want to fetch data");

			PreparedStatement stmt4 = con
					.prepareStatement("Select studentname,score,grade from studentresult where studentid=?");
			Scanner sc = new Scanner(System.in);
			String id1 = Integer.toString(sc.nextInt());
			stmt4.setString(1, id1);

			ResultSet rs4 = stmt4.executeQuery();

			while (rs4.next()) {
				System.out.println("name >> " + rs4.getString(1));
				System.out.println("score >> " + rs4.getString(2));
				System.out.println("grade >> " + rs4.getString(3));

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
