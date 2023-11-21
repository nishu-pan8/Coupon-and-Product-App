package com.nishu.cnp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import com.nishu.cnp.dao.CouponDAO;
import com.nishu.cnp.model.Coupon;

/**
 * Servlet implementation class CouponController
 */
public class CouponController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponDAO dao = new CouponDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CouponController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action.equals("create")) {
			try {
				createCoupon(request, response);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("find")) {
			try {
				findCoupon(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void createCoupon(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException {
		String couponCode = request.getParameter("couponCode");
		String discount = request.getParameter("discount");
		String expiryDate = request.getParameter("expiryDate");

		Coupon coupon = new Coupon();
		coupon.setCode(couponCode);
		coupon.setDiscount(new BigDecimal(discount));
		coupon.setExpDate(expiryDate);

		dao.save(coupon);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Coupon Created!</b>");
		out.print("<b><a href='/CouponAndProductApp'>Home</a>");
	}

	public void findCoupon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		String couponCode = request.getParameter("couponCode");
		Coupon coupon = dao.findByCode(couponCode);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(coupon);
		out.print("<br/><a href='/CouponAndProductApp'>Home</a>");

	}

}
