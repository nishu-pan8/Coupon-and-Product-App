package com.nishu.cnp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import com.nishu.cnp.dao.CouponDAO;
import com.nishu.cnp.dao.ProductDAO;
import com.nishu.cnp.model.Coupon;
import com.nishu.cnp.model.Product;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	CouponDAO couponDAO = new CouponDAO();
	ProductDAO productDAO = new ProductDAO();
	private static final long serialVersionUID = 1L;

	public ProductController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String couponCode = request.getParameter("couponCode");

		try {
			Coupon coupon = couponDAO.findByCode(couponCode);
			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setPrice(new BigDecimal(price).subtract(coupon.getDiscount()));
			productDAO.save(product);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b>Product Created!</b>");
			out.print("<b><a href='/CouponAndProductApp'>Home</a>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
