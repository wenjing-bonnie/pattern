package com.android.pattern.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by wenjing.liu on 2021/5/20 in J1.
 * <p>
 * Java中的IO的装饰者模式体现
 *
 * @author wenjing.liu
 */
public class JavaIoMain {

    public static void main(String[] args) {
        BufferedInputStream bufferedInputStream = null;
        byte[] buffer = new byte[1024];
        try {
            //TODO 该路径应该是一个具体的文件路径
            String filePath = "";
            System.out.println(filePath);
            //将具体的被装饰类传入到具体的装饰类中
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
            bufferedInputStream.read(buffer);
            System.out.println(new String(buffer));
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
