package user;

import java.sql.*;

public class UserDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/topcore";
            String dbID = "root";
            String dbPassword = "wlsghk3385";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbID, dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int login(String userID, String userPassword) {
        String SQL = "SELECT userPassword FROM user WHERE userID = '?'";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, userID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString(1).equals(userPassword)) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2;
    }

    public int join(String userID, String userPassword, String userName, String userEmail, String userPhone) {
        String SQL = "INSERT INTO user VALUES (?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(2, userEmail);
            preparedStatement.setString(3, userPhone);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}