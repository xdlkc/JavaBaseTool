package per.lkc.base.str;

import lombok.extern.slf4j.Slf4j;
import per.lkc.base.constant.CommonConstant;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author ZhangJunbo
 */
@Slf4j
public class DeflaterUtil {
    public static String compress(String source) {
        return compress(source, CommonConstant.ISO_8859);
    }

    public static String compress(String source, String charSet) {
        try {
            byte[] input = source.getBytes();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Deflater compressor = new Deflater(1);
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
            return bos.toString(charSet);
        } catch (Exception e) {
            log.error("compress str err,str:{}", source, e);
            return source;
        }
    }

    public static String decompress(String source) {
        return decompress(source, CommonConstant.ISO_8859);
    }

    public static String decompress(String source, String charSet) {
        try {
            byte[] input = source.getBytes(charSet);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Inflater decompressor = new Inflater();
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
            decompressor.end();
            return bos.toString();
        } catch (Exception e) {
            log.error("decompress str err,str:{}", source, e);
            return source;
        }

    }
}