package per.lkc.base.str;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import per.lkc.base.constant.CommonConstant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class Bzip2Util {
    public static String compress(String source) {
        return compress(source, CommonConstant.ISO_8859);
    }

    public static String compress(String source, String charSet) {
        try {
            byte[] srcBytes = source.getBytes();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(out);
            bcos.write(srcBytes);
            bcos.close();
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
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(in);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString();
        } catch (IOException e) {
            log.error("decompress str err,str:{}", source, e);
            return source;
        }

    }

}