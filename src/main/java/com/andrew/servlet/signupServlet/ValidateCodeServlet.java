package com.andrew.servlet.signupServlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ValidateCodeServlet Class
 *
 * @author andrew
 * @date 2019/7/19
 */
@WebServlet("/ValidateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        String path=request.getServletContext().getRealPath("/img");
        path=path+"/validateCodeBackground.jpg";

        File file=new File(path);
        BufferedImage image= ImageIO.read(file);

        String code=Code();
        draw(image,code);

        HttpSession session=request.getSession();
        session.setAttribute("validateCode", code);

        String v=(String) session.getAttribute("validateCode");
        System.out.println("----"+v);

        OutputStream out=response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        out.close();
    }

    /**
     * 获得随机的四位的数字和字母并返回字符串
    */
    private String Code() {
        String vc="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code="";
        Random random=new Random();
        for(int i=0;i<4;i++) {
            code=code+vc.charAt(random.nextInt(vc.length()));
        }
        return code;
    }

    private void draw(BufferedImage image, String code) {
        Graphics g=image.getGraphics();
        g.setFont(new Font("AR CENA",Font.BOLD,25));
        g.setColor(new Color(0,0,0));
        for(int i=0;i<code.length();i++) {
            String c=String.valueOf(code.charAt(i));
            Random r=new Random();
            int y=r.nextInt(10);
            g.drawString(c, 13*(i+1), 28+y);
        }
    }

}
