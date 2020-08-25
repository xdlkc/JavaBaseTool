package per.lkc.base.util;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/3/23.
 *
 * @author zhangjunbo.
 */
public class ThumbnailUtil {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/ZhangJunbo/Downloads/a.jpg");
        File docFile = new File("/Users/ZhangJunbo/Downloads/a.xlsx");
        process(docFile);
        // try (FileOutputStream fos = new FileOutputStream(file)) {
        //     fos.write(process(docFile));
        // }
    }

    public static byte[] read() throws Exception {
        //读取图片
        byte[] content = null;
        File file = new File("/Users/ZhangJunbo/Downloads/a.pdf");
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            bos.write(b, 0, n);
        }
        in.close();
        bos.close();
        content = bos.toByteArray();
        return content;
    }

    public static String gen(byte[] content) throws Exception {

        InputStream in = new ByteArrayInputStream(content, 0, content.length);//数组，起始位置，结束位置

        BufferedImage bImage = ImageIO.read(in);//将inputSteam读取成bufferdImage

        int height = bImage.getHeight() / 4;//获取转化后的高度
        int width = bImage.getWidth() / 4;//获取转化后的宽度

        //声明一个新的BufferdImage
        BufferedImage newBImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //设置newBImage的参数
        newBImage.getGraphics().drawImage(bImage, 0, 0, width, height, null);
        FileOutputStream out = new FileOutputStream("/Users/ZhangJunbo/Downloads/a.jpg");
        ImageIO.write(newBImage, "jpg", out);
        return "";
    }

    public static void process(File docFile) throws Exception {
        XWPFDocument wordDocument = new XWPFDocument(new FileInputStream(docFile));
        POIXMLProperties props = wordDocument.getProperties();

        String thumbnail = props.getThumbnailFilename();
        if (thumbnail == null) {
            // No thumbnail
        } else {
            FileOutputStream fos = new FileOutputStream("/Users/ZhangJunbo/Downloads/"+thumbnail);
            IOUtils.copy(props.getThumbnailImage(), fos);
        }
    }
}
