package com.au.example.fileupload.servlet;

import com.au.example.fileupload.service.FileStorageService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by Ayhan.Ugurlu on 18/10/2018
 */
@WebServlet(
        name = "FileUploadServlet",
        urlPatterns = {"/fileUploadServlet"}
)
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Hello World!</p>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        WebApplicationContext ctx =
                WebApplicationContextUtils
                        .getWebApplicationContext(getServletContext());
        FileStorageService fileStorageService = ctx.getBean("fileStorageService", FileStorageService.class);


        System.out.println("test");
        if (ServletFileUpload.isMultipartContent(request)) {

            ServletFileUpload upload = new ServletFileUpload();
            // Parse the request
            FileItemIterator iter = null;
            try {
                iter = upload.getItemIterator(request);

                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    String name = item.getFieldName();
                    InputStream stream = item.openStream();
                    if (item.isFormField()) {
                        System.out.println("Form field " + name + " with value "
                                + Streams.asString(stream) + " detected.");
                    } else {
                        System.out.println("File field " + name + " with file name "
                                + item.getName() + " detected.");
                        // Process the input stream

                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

        }
    }
}