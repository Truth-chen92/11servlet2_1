package cn.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "AddServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数 在控制台输出
        //获取连接 把参数保存到数据库
        //返回客户端添加完成
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String money = request.getParameter("money");
        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("insert into hero value(null,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setInt(3, Integer.parseInt(money));
            ps.executeUpdate();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println("添加成功！");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
