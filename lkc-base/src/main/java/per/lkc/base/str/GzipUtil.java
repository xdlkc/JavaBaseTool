package per.lkc.base.str;

import lombok.extern.slf4j.Slf4j;
import per.lkc.base.constant.CommonConstant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class GzipUtil {
    public static String compress(String source) {
        return compress(source, CommonConstant.ISO_8859);
    }

    public static String compress(String source, String charSet) {
        try {
            if (source == null || source.length() == 0) {
                return null;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = null;
            gzip = new GZIPOutputStream(out);
            gzip.write(source.getBytes());
            gzip.close();

            return out.toString(charSet);
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
            byte[] bytes = source.getBytes(charSet);
            if (bytes.length == 0) {
                return null;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            GZIPInputStream ungzip = null;
            ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString();
        } catch (Exception e) {
            log.error("decompress str err,str:{}", source, e);
            return source;
        }

    }

}