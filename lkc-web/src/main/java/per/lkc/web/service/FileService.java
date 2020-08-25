package per.lkc.web.service;

import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/8/17.
 *
 * @author zhangjunbo.
 */
@Service
public class FileService {
    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("/Users/ZhangJunbo/Downloads/音频测试/01丨OAuth2.m4a");
        sendVideo(request, response, file, "oatuh.m4a");
    }

    private void sendVideo(HttpServletRequest request, HttpServletResponse response, File file, String fileName) throws FileNotFoundException, IOException {
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");//只读模式
        long contentLength = randomFile.length();
        String range = request.getHeader("Range");
        int start = 0, end = 0;
        if(range != null && range.startsWith("bytes=")){
            String[] values = range.split("=")[1].split("-");
            start = Integer.parseInt(values[0]);
            if(values.length > 1){
                end = Integer.parseInt(values[1]);
            }
        }
        int requestSize = 0;
        if(end != 0 && end > start){
            requestSize = end - start + 1;
        } else {
            requestSize = Integer.MAX_VALUE;
        }

        byte[] buffer = new byte[4096];
        response.setContentType("video/mp4");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", fileName);
        response.setHeader("Last-Modified", new Date().toString());
        //第一次请求只返回content length来让客户端请求多次实际数据
        if(range == null){
            response.setHeader("Content-length", contentLength + "");
        }else{
            //以后的多次以断点续传的方式来返回视频数据
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
            long requestStart = 0, requestEnd = 0;
            String[] ranges = range.split("=");
            if(ranges.length > 1){
                String[] rangeDatas = ranges[1].split("-");
                requestStart = Integer.parseInt(rangeDatas[0]);
                if(rangeDatas.length > 1){
                    requestEnd = Integer.parseInt(rangeDatas[1]);
                }
            }
            long length = 0;
            if(requestEnd > 0){
                length = requestEnd - requestStart + 1;
                response.setHeader("Content-length", "" + length);
                response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
            }else{
                length = contentLength - requestStart;
                response.setHeader("Content-length", "" + length);
                response.setHeader("Content-Range", "bytes "+ requestStart + "-" + (contentLength - 1) + "/" + contentLength);
            }
        }
        ServletOutputStream out = response.getOutputStream();
        int needSize = requestSize;
        randomFile.seek(start);
        while(needSize > 0){
            int len = randomFile.read(buffer);
            if(needSize < buffer.length){
                out.write(buffer, 0, needSize);
            } else {
                out.write(buffer, 0, len);
                if(len < buffer.length){
                    break;
                }
            }
            needSize -= buffer.length;
        }
        randomFile.close();
        out.close();

    }
}
