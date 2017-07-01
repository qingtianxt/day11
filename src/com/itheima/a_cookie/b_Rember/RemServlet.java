package com.itheima.a_cookie.b_Rember;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemServlet
 */
public class RemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.���ñ���
		response.setContentType("text/html;charset=utf-8");
		PrintWriter w = response.getWriter();
		
		//1.��ȡָ�����Ƶ�cookie
		Cookie c=getCookieByName("lastTime",request.getCookies());
		
		//2.�ж�cookie�Ƿ�Ϊ��
		if(c==null){
			//cookieΪ�գ���ʾ��һ�η���
			w.print("���ǵ�һ�η���");
		}else
		{
			//cookie��Ϊ�գ���ȡvalue չʾ��һ�η��ʵ�ʱ��
			String value=c.getValue();//lastTime=123213123
			long time=Long.parseLong(value);
			Date date = new Date(time);
			w.println("����һ�η��ʵ�ʱ�䣺"+date.toLocaleString());
		}
		
		//3.����ǰ����ʱ���¼
		//3.1 ����cookie
		c=new Cookie("lastTime",new Date().getTime()+"");
		
		//�־û�cookie
		c.setMaxAge(3600);
		//����·��
		c.setPath(request.getContextPath()+"/");
		//3.2д�������
		response.addCookie(c);
	}
/**
 * ͨ��������cookie�����ȡָ����cookie
 * @param name cookie����
 * @param cookies cookie����
 * @return
 */
	private Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//ͨ�����ƻ�ȡ
				if(name.equals(c.getName())){
					//����
					return c;
				}
			}
		}
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
