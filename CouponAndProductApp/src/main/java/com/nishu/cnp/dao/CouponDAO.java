package com.nishu.cnp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.nishu.cnp.model.Coupon;
import com.nishu.cnp.util.ConnectionUtil;

public class CouponDAO {

	public void save(Coupon coupon) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root",
				"7060106098");) {
			PreparedStatement statement = connection
					.prepareStatement("insert into coupon (code,discount,exp_date) values(?,?,?)");
			statement.setString(1, coupon.getCode());
			statement.setBigDecimal(2, coupon.getDiscount());
			statement.setString(3, coupon.getExpDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Coupon findByCode(String code) throws ClassNotFoundException {
		Coupon coupon = new Coupon();
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root",
				"7060106098");) {
			PreparedStatement statement = connection.prepareStatement("select * from coupon where code=?");
			statement.setString(1, code);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				coupon.setId(resultSet.getInt(1));
				coupon.setCode(resultSet.getString(2));
				coupon.setDiscount(resultSet.getBigDecimal(3));
				coupon.setExpDate(resultSet.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coupon;
	}
}
