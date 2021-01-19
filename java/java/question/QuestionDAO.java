package question;

import java.sql.*;
import java.util.ArrayList;

public class QuestionDAO {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public QuestionDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/topcore?serverTimezone=UTC";
			String dbID = "topcore";
			String dbPassword = "TOPcore1357!";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNext() {
		String SQL = "SELECT questionID FROM question ORDER BY questionID DESC";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1) + 1;
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 새로 추가된 list 불러오는 내용
	public ArrayList<Question> getList(int pageNumber) {
		String SQL = "SELECT * FROM question WHERE questionAvailable > 0 ORDER BY questionID DESC LIMIT ?,10";
		ArrayList<Question> list = new ArrayList<>();

		try {
			int nextNumber = getNext();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, (pageNumber - 1) * 10);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Question question = new Question();
				question.setQuestionID(resultSet.getInt(1));
				question.setQuestionName(resultSet.getString(2));
				question.setQuestionDate(resultSet.getString(3));
				question.setQuestionEmail(resultSet.getString(4));
				question.setQuestionPhone(resultSet.getString(5));
				question.setQuestionContents(resultSet.getString(6));
				question.setQuestionAvailable(resultSet.getInt(7));
				question.setQuestionSelect(resultSet.getString(8));
				question.setQuestionPrice(resultSet.getString(9));
				question.setQuestionCompany(resultSet.getString(10));
				question.setQuestionAgree(resultSet.getInt(11));
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM question WHERE questionID < ? AND questionAvailable > 0 ORDER BY questionID DESC LIMIT 10";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, getNext() - (pageNumber - 1) * 10);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Question getQuestion(int questionID) {
		String SQL = "SELECT * FROM question WHERE questionID = ?";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, questionID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Question question = new Question();
				question.setQuestionID(resultSet.getInt(1));
				question.setQuestionName(resultSet.getString(2));
				question.setQuestionDate(resultSet.getString(3));
				question.setQuestionEmail(resultSet.getString(4));
				question.setQuestionPhone(resultSet.getString(5));
				question.setQuestionContents(resultSet.getString(6));
				question.setQuestionAvailable(resultSet.getInt(7));
				question.setQuestionSelect(resultSet.getString(8));
				question.setQuestionPrice(resultSet.getString(9));
				question.setQuestionCompany(resultSet.getString(10));
				question.setQuestionAgree(resultSet.getInt(11));
				return question;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getDate() {
		String SQL = "SELECT NOW()";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public int write(String questionName, String questionEmail, String questionPhone, String questionContents,
			String questionSelect, String questionPrice, String questionCompany) {
		String SQL = "INSERT INTO question (questionID, questionName, questionDate, questionEmail, questionPhone, questionContents, questionAvailable, questionSelect, questionPrice, questionCompany, questionAgree) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		int next = getNext();
		String date = getDate();

		try {
			preparedStatement = connection.prepareStatement(SQL);

			preparedStatement.setInt(1, next);
			preparedStatement.setString(2, questionName);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, questionEmail);
			preparedStatement.setString(5, questionPhone);
			preparedStatement.setString(6, questionContents);
			preparedStatement.setInt(7, 2);
			preparedStatement.setString(8, questionSelect);
			preparedStatement.setString(9, questionPrice);
			preparedStatement.setString(10, questionCompany);
			preparedStatement.setInt(11, 1);

			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int delete(int questionID) {
		String SQL = "UPDATE question SET questionAvailable = 0 WHERE questionID = ?";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, questionID);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int questionCount() { // 전체 게시글 수 (삭제 되지않은 )
		String SQL = "SELECT count(*) FROM question WHERE questionAvailable > 0";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
