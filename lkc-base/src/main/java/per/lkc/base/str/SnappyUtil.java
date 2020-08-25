package per.lkc.base.str;

import lombok.extern.slf4j.Slf4j;
import org.xerial.snappy.Snappy;
import per.lkc.base.constant.CommonConstant;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class SnappyUtil {
    public static String compress(String source) {
        return compress(source, CommonConstant.ISO_8859);
    }

    public static String compress(String source, String charSet) {
        try {
            return new String(Snappy.compress(source, charSet));
        } catch (IOException e) {
            log.error("compress str err,str:{}", source, e);
            return source;
        }

    }

    public static String decompress(String source) {
        return decompress(source, CommonConstant.ISO_8859);
    }

    public static String decompress(String source, String charSet) {
        try {
            return Snappy.uncompressString(source.getBytes(), Charset.forName(charSet));
        } catch (IOException e) {
            log.error("decompress str err,str:{}", source, e);
            return source;
        }
    }
}