package com.QuizQuestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Quiz {

	public static void main(String[] args) throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "root");

			Statement stcr = con.createStatement();
			Statement st1 = con.createStatement();
			String s1 = "select * from quiztable";
			ResultSet r = st1.executeQuery(s1);
//			while (r.next()) {
			// }else {
			String insert1 = "insert into quiztable(QName,A,B,C,D,Answer) values (' Number of primitive data types in Java are?','6','7','8','9','8')";
			String insert2 = "insert into quiztable(QName,A,B,C,D,Answer) values (' What is the size of float and double in java?','32 and 64','32 and 32','64 and 64','64 and 32','32 and 64')";
			String insert3 = "insert into quiztable(QName,A,B,C,D,Answer) values (' Automatic type conversion is possible in which of the possible cases?','byte to int','int to long','long to int','short to int','int to long')";
			String insert4 = "insert into quiztable(QName,A,B,C,D,Answer) values (' Which of the following is a reserved keyword in Java?','object','strictfp','main','Dynamic binding between objects','strictfp')";
			String insert5 = "insert into quiztable(QName,A,B,C,D,Answer) values (' Which of the following is not a Java features?','Dynamic','Architecture Neutral','use of pointer','Multithreaded','use of pointer')";
			String insert6 = "insert into quiztable(QName,A,B,C,D,Answer) values (' What is the return type of the hashCode() method in the Object class?','object','int','long','void','int')";
			String insert7 = "insert into quiztable(QName,A,B,C,D,Answer) values (' _____ is used to find and fix bugs in the Java programs.?','JVM','JDK','JRE','JDB','JDB')";
			String insert8 = "insert into quiztable(QName,A,B,C,D,Answer) values (' Which of the following is a valid long literal?','ABH8097','L990023','904423','0xnf029L','0xnf029L')";
			String insert9 = "insert into quiztable(QName,A,B,C,D,Answer) values (' What does the expression float a = 35 / 0 return?','0','nothing','infinity','null','infinity')";
			String insert10 = "insert into quiztable(QName,A,B,C,D,Answer) values (' Which of the following is an immediate subclass of the Panel class?','Applet class','Window class','Frame class','Dialog class','Applet class')";

			stcr.addBatch(insert1);
			stcr.addBatch(insert2);
			stcr.addBatch(insert3);
			stcr.addBatch(insert4);
			stcr.addBatch(insert5);
			stcr.addBatch(insert6);
			stcr.addBatch(insert7);
			stcr.addBatch(insert8);
			stcr.addBatch(insert9);
			stcr.addBatch(insert10);

			stcr.executeBatch();
			// }

			// System.out.println("Insertion done");

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number of student giving the exam");
			int no_of_student = sc.nextInt();

			while (no_of_student > 0) {
				int score = 0;
				String gradeobtained = "";
				System.out.println("Enter the student name : -  ");
				String studentName = sc.next();

				// Statement stmt1 = con.createStatement();

				String sql = "select QName,A,B,C,D,Answer from quiztable where Qid=?";

				PreparedStatement stmt1 = con.prepareStatement(sql);

				// generating random value
				Random randomgenerator = new Random();

				int randomOrigin = 1;
				int randombound = 11;

				int size = 10;

				int[] unique = randomgenerator.ints(randomOrigin, randombound).distinct().limit(size).toArray();

				for (int m = 0; m < 10; m++) {

					String p = Integer.toString(unique[m]);
					stmt1.setString(1, p);

					ResultSet rs = stmt1.executeQuery();

					while (rs.next()) {
						System.out.println(
								"Que " + (m + 1) + " " + rs.getString(1) + "\n(A) " + rs.getString(2) + "\t (B) "
										+ rs.getString(3) + "\t (C) " + rs.getString(4) + " \t(D) " + rs.getString(5));

					}

					System.out.println("Submit your Answer :- ");
					String ans = sc.next();

					ResultSet rs1 = stmt1.executeQuery();

					while (rs1.next()) {

						if ((rs1.getString(ans)).equals(rs1.getString(6))) {

							score++;
						}

					}

					if (score >= 8 && score <= 10) {
						gradeobtained = "Class A";
					} else if (score >= 6 && score <= 7) {
						gradeobtained = "Class B";
					} else if (score == 5) {
						gradeobtained = "Class C";
					} else {
						gradeobtained = "Class D";

					}

				}
				PreparedStatement stmt3 = con
						.prepareStatement("insert into studentresult(StudentName,Score,Grade)values(?,?,?)");

				stmt3.setString(1, studentName);
				stmt3.setLong(2, score);
				stmt3.setString(3, gradeobtained);

				int j = stmt3.executeUpdate();
				System.out.println("Marks obtained for " + studentName + " is " + score + " and Grade obtained is >> "
						+ gradeobtained);

				no_of_student--;
			}

			DescOrder obj = new DescOrder();
			obj.getorder();

			// get the data from particular id
			fetch_data obj1 = new fetch_data();
			obj1.getData();

			con.close();
			stcr.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}
}
