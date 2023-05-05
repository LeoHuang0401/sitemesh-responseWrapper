package sitemesh.example.sitemeshfilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
    
    /**
     * ByteArrayOutputStream
     */
    private ByteArrayOutputStream buffer;
    /**
     * ServletOutputStream
     */
    private ServletOutputStream out;
    /**
     * writer
     */
    private PrintWriter writer;

    public ResponseWrapper(HttpServletResponse res) throws IOException {
        super(res);
        // 儲存數據
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }

    public String getContent() throws IOException {
     // 把out、writer中的資料寫到WapperedResponse的buffer里面，否則取不到值
        flushBuffer();
        return new String(buffer.toByteArray());
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    public void reset() {
        buffer.reset();
    }

    private class WrapperOutputStream extends ServletOutputStream {
        /**
         * ByteArrayOutputStream
         */
        private ByteArrayOutputStream bos;

        /**
         * WrapperOutputStream
         */
        WrapperOutputStream(ByteArrayOutputStream stream) {
            bos = stream;
        }

        @Override
        public void write(int b) {
            bos.write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}
