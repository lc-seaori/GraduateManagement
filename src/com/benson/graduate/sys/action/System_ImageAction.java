package com.benson.graduate.sys.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;

/**
 * 
 * @author benson
 */
@Controller("createImageAction")
@Scope("prototype")
public class System_ImageAction extends BaseAction {

	private static final long serialVersionUID = 5489202342301898919L;
	
	private static int WIDTH = 80;
	private static int HEIGHT = 30;

    private InputStream imageStream;
    
    public static String createRandom(){
        String str = "0123456789qwertyuiopasdfghjklzxcvbnm";
        char[] rands = new char[4];
        Random random = new Random();

        for (int i = 0; i < 4; i++){
            rands[i] = str.charAt(random.nextInt(36));
        }
        return new String(rands);
    }

    public void drawBackground(Graphics g){
        // 画背景
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 随机产生 120 个干扰点
        for (int i = 0; i < 120; i++){
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }	
    }

    public void drawRands(Graphics g, String rands){
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

        // 在不同的高度上输出验证码的每个字符
        g.drawString("" + rands.charAt(0), 3, 20);
        g.drawString("" + rands.charAt(1), 25, 22);
        g.drawString("" + rands.charAt(2), 43, 19);
        g.drawString("" + rands.charAt(3), 68, 25);
        System.out.println("rand    :"+rands);
    }

    public InputStream getImageStream() throws Exception{
    	
    	System.out.println("getImageStream()   生成验证码中...");
        // 设置浏览器不要缓存此图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String rands = createRandom();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // 产生图像
        drawBackground(g);
        drawRands(g, rands);
        // 结束图像 的绘制 过程， 完成图像
        g.dispose();
        
        httpSession.setAttribute("checkCode", rands);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        imageStream = new ByteArrayInputStream(outputStream.toByteArray());
        System.out.println("imageStream   :"+imageStream);
        outputStream.flush();
        outputStream.close();
        
        return imageStream;
    }
    
    public String doNotNeedSession_getCodeImage(){
    	System.out.println("getCodeImage()");
    	return "success";
    }
}
