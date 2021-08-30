package com.kolosensei.springboottooltemplate.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/3/10 00:56
 * @description:
 */
public class NioTemplate {

    public static void main(String[] args) throws IOException {

        String s = "hello world";
        FileOutputStream fo = new FileOutputStream("basic.txt");
        FileChannel fileChannel = fo.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put(s.getBytes(StandardCharsets.UTF_8));
        allocate.flip();
        fileChannel.write(allocate);
        fo.close();
    }
}
