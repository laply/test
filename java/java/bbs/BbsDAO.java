package bbs;

import java.sql.*;
import java.util.ArrayList;

public class BbsDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public BbsDAO() {
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
		String SQL = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";

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

	public ArrayList<Bbs> getList(int pageNumber) {
		// String SQL = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable > 0 ORDER BY
		// bbsID DESC LIMIT 10";
		String SQL = "SELECT * FROM bbs WHERE bbsAvailable > 0 ORDER BY bbsID DESC LIMIT ?,10";
		ArrayList<Bbs> list = new ArrayList<>();

		try {
			int nextNumber = getNext();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, (pageNumber - 1) * 10);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(resultSet.getInt(1));
				bbs.setBbsTitle(resultSet.getString(2));
				bbs.setBbsDate(resultSet.getString(3));
				bbs.setBbsWriter(resultSet.getString(4));
				bbs.setBbsPassword(resultSet.getString(5));
				bbs.setBbsContent(resultSet.getString(6));
				bbs.setBbsPrivate(resultSet.getString(7));
				bbs.setBbsAvailable(resultSet.getInt(8));
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable > 0 ORDER BY bbsID DESC LIMIT 10";

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

	public Bbs getBbs(int bbsID) {
		String SQL = "SELECT * FROM bbs WHERE bbsID = ?";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, bbsID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(resultSet.getInt(1));
				bbs.setBbsTitle(resultSet.getString(2));
				bbs.setBbsDate(resultSet.getString(3));
				bbs.setBbsWriter(resultSet.getString(4));
				bbs.setBbsPassword(resultSet.getString(5));
				bbs.setBbsContent(resultSet.getString(6));
				bbs.setBbsPrivate(resultSet.getString(7));
				bbs.setBbsAvailable(resultSet.getInt(8));
				return bbs;
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

	public int write(String bbsTitle, String bbsWriter, String bbsPassword, String bbsContent, String bbsPrivate) {
		String SQL = "INSERT INTO bbs (bbsID, bbsTitle, bbsDate, bbsWriter, bbsPassword, bbsContent, bbsPrivate, bbsAvailable) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		int next = getNext();
		String date = getDate();

		try {

			preparedStatement = connection.prepareStatement(SQL);

			preparedStatement.setInt(1, next);
			preparedStatement.setString(2, bbsTitle);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, bbsWriter);
			preparedStatement.setString(5, bbsPassword);
			preparedStatement.setString(6, bbsContent);
			preparedStatement.setString(7, bbsPrivate);
			preparedStatement.setInt(8, 2);

			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());

		}
		return -1;
	}

	public int update(int bbsID, String bbsTitle, String bbsContent, String bbsPrivate, String bbsPassword) {
		String SQL = "UPDATE bbs SET bbsTitle = ?, bbsContent = ?, bbsPrivate = ?, bbsPassword = ? WHERE bbsID = ?";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, bbsTitle);
			preparedStatement.setString(2, bbsContent);
			preparedStatement.setString(3, bbsPrivate);
			preparedStatement.setString(4, bbsPassword);
			preparedStatement.setInt(5, bbsID);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int delete(int bbsID) {
		String SQL = "UPDATE bbs SET bbsAvailable = 0 WHERE bbsID = ?";

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, bbsID);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String login() {
		String SQL = "SELECT bbsPassword from bbs where bbsID = 1";
		String bbsPassword = "";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				bbsPassword = resultSet.getString("bbsPassword");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bbsPassword;
	}

	public int bbsCount() { // 전체 게시글 수 (삭제 되지않은 )
		String SQL = "SELECT count(*) FROM bbs WHERE bbsAvailable > 0";

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
