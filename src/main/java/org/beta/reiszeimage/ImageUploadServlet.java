/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beta.reiszeimage;

import org.apache.commons.codec.binary.Base64;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.google.common.io.Files;
/**
 *
 * @author z.benrhouma
 */
@MultipartConfig
@WebServlet(name = "ImageUploadServlet", urlPatterns = {"/upload/image"})
public class ImageUploadServlet extends HttpServlet {
    private String outputDirectory ="e:\\aze.jpg";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
       
        deccode(extractParts(request));
       
        out.print("ok");
        out.flush();
        out.close();

    }

    private File extractParts(HttpServletRequest request) {
        try {
            final File fl = new File(outputDirectory);
            if (!fl.exists()) {
                fl.createNewFile();
            }
            final Part part = request.getPart("img");
            final InputStream input = part.getInputStream();

            byte[] buffer = new byte[1024];
            int noOfBytes = 0;
            final FileOutputStream fos = new FileOutputStream(fl);
            while ((noOfBytes = input.read(buffer)) != -1) {
                fos.write(buffer, 0, noOfBytes);
            }
            fos.flush();
            fos.close();
            return fl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deccode(File file) throws IOException{
        byte[] content = Base64.decodeBase64(Files.toByteArray(file));
        final FileOutputStream fos = new FileOutputStream(file);
        fos.write(content);
        fos.flush();
        fos.close();
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
