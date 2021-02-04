package cn.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "FindAllServlet",urlPatterns = "/findall")
public class FindAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取连接
        try (Connection conn=DBUtils.getConn()){
            String sql="select id,name,type,money from hero";
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw=response.getWriter();
            pw.print("<table border='4'>");
            pw.print("<caption>英雄表</caption>");
            pw.print("<tr><th>id</th><th>名字</th><th>类型</th><th>价格</th><th>操作</th></tr>");
            while (rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                String type=rs.getString(3);
                int money = rs.getInt(4);
                pw.print("<tr>");
                pw.print("<td>"+id+"</td>");
                pw.print("<td>"+name+"</td>");
                pw.print("<td>"+type+"</td>");
                pw.print("<td>"+money+"</td>");
                pw.print("<td><a href='delete?id="+id+"'>删除</a></td>");
                pw.print("</tr>");
            }
            pw.print("</table>");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
