package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Movie;
import bean.Type;
import biz.MovieBiz;
import biz.impl.MovieBizImpl;

public class AddMovieSer extends HttpServlet {
	public AddMovieSer() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收时设置的编码
		request.setCharacterEncoding("utf-8");
		// 转发时设置的编码
		response.setCharacterEncoding("utf-8");
		// 以 超文本格式 方式转发
		response.setContentType("text/html");
		// 获取了一个输出流
		PrintWriter out = response.getWriter();
		// 显示下拉菜单
		MovieBiz movieBiz = new MovieBizImpl();
		List<Type> types = movieBiz.getAllType();
		request.setAttribute("types", types);
		//获取要添加的电影内容
		String titile = request.getParameter("titile");
		String typeidStr = request.getParameter("typeid");
		String actor = request.getParameter("actor");
		String director = request.getParameter("director");
		String priceStr = request.getParameter("price");
		if (titile != null && typeidStr != null && priceStr != null) {
			int typeid = Integer.parseInt(typeidStr);
			double price = Double.parseDouble(priceStr);
			Type type = movieBiz.getTypeById(typeid);
			//添加电影
			Movie movie = new Movie(titile, director, actor, price, type);
			movieBiz.addMovie(movie);
			out.print("<script>alert('添加电影成功');location.href='index';</script>");  
		}else{
			request.getRequestDispatcher("addMovie.jsp").forward(request, response);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
	}
}
