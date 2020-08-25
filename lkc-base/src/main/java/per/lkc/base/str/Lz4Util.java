package per.lkc.base.str;

import lombok.extern.slf4j.Slf4j;
import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import per.lkc.base.constant.CommonConstant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Slf4j
public class Lz4Util {
    public static String compress(String source) {
        return compress(source, CommonConstant.ISO_8859);
    }

    public static String compress(String source, String charSet) {
        try {
            byte[] srcBytes = source.getBytes();
            LZ4Factory factory = LZ4Factory.fastestInstance();
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            LZ4Compressor compressor = factory.fastCompressor();
            LZ4BlockOutputStream compressedOutput = new LZ4BlockOutputStream(
                    byteOutput, 2048, compressor);
            compressedOutput.write(srcBytes);
            compressedOutput.close();
            return byteOutput.toString(charSet);
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
            LZ4Factory factory = LZ4Factory.fastestInstance();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            LZ4FastDecompressor decompresser = factory.fastDecompressor();
            LZ4BlockInputStream lzis = new LZ4BlockInputStream(
                    new ByteArrayInputStream(bytes), decompresser);
            int count;
            byte[] buffer = new byte[2048];
            while ((count = lzis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            lzis.close();
            return baos.toString();
        } catch (Exception e) {
            log.error("decompress str err,str:{}", source, e);
            return source;
        }
    }

}