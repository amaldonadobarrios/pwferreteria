/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
//import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 *
 * @author Farid
 */
public class Util {

    final static Logger logger = Logger.getLogger(Util.class);

    private static Util utilClass;

    public Util() {
    }

    public static Util getInstance() {

        if (utilClass == null) {
            utilClass = new Util();
            return utilClass;
        } else {
            return utilClass;
        }
    }

    public String parseGSON(String dato) {

        logger.info("HIBPER : Convirtiendo a GSON : " + dato);

        Gson gson = new Gson();
        return gson.toJson(dato);
    }

    public static String getClientIpAddr(HttpServletRequest request) {

        logger.info("HIBPER : getClientIpAddr");

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        logger.info("HIBPER : IP : " + ip);

        return ip;
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    public static void confProxy() {

        logger.info("HIBPER : INICIO == configuacion proxy");
        System.setProperty("http.proxyHost", "172.31.1.26");
        System.setProperty("http.proxyPort", "80");
        // IP RENIEC NO USAR PROXY

        System.setProperty("http.nonProxyHosts",
                "192.168.10.120|10.201.254.3|10.201.254.4|192.168.153.1|192.168.10.120|190.116.46.23|191.98.143.101");
        logger.info("HIBPER : " + System.getProperty("http.nonProxyHosts"));
        logger.info("HIBPER : FIN == configuacion proxy");
    }

    public static <E> int verificaClase(List<E> list, Class<? extends E> clazz) {
        // for (E e : list) {
        for (int i = 0; i < list.size(); i++) {
            try {
                List<E> list2 = (List<E>) list.get(i);
                for (E list21 : list2) {
                    if (clazz.isInstance(list21)) {
                        return i;
                    }
                    break;
                }
            } catch (Exception q) {
                if (clazz.isInstance(list.get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Replace ALL occurrences of [old value] with [new value]<br>
     * This method was written by Carlos Pineda.
     *
     * @param source String to manipulate
     * @param pattern Old value
     * @param newText New value
     * @return String with replaced text
     */
    public static String replace(String source, String pattern, String newText) {

        StringBuilder buf = new StringBuilder();

        int len = pattern.length();
        int previndex = 0;
        int index = 0;
        while (true) {
            index = source.indexOf(pattern, previndex);
            if (index == -1) {
                buf.append(source.substring(previndex));
                break;
            }
            buf.append(source.substring(previndex, index)).append(newText);
            previndex = index + len;
        }
        return buf.toString();
    }

    /**
     * Load a text resource relative to the class location
     *
     * @param path Pathname of the resource, if it is a filename then the
     * resource location is assumed in the same path as the class otherwise may
     * be a sybdirectory relative to the class directory.
     * @return A String containing the resource
     * @throws Throwable
     */
    public String getLocalResource(String path) throws Throwable {

        StringBuilder buf = new StringBuilder(5000);
        // StringBuilder buf = new StringBuilder(5000);
        byte[] data = new byte[5000];

        InputStream in;

        in = this.getClass().getResourceAsStream(path);

        try {
            if (in != null) {
                while (true) {
                    int len = in.read(data);
                    if (len != -1) {
                        buf.append(new String(data, 0, len));
                    } else {
                        break;
                    }
                }
                return buf.toString();
            } else {
                throw new Throwable("Invalid path to resource: " + path);
            }

        } catch (Throwable e) {
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

    }

    /**
     * Read a properties file from the classpath and return a Properties object
     *
     * @param filename
     * @return
     * @throws IOException
     */
    static public Properties readProperties(String filename) throws IOException {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        props.load(stream);
        return props;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    protected String getCookieValue(HttpServletRequest req, String cookieName) {
        String value = null;
        Cookie c[] = req.getCookies();
        if (c != null) {
            for (Cookie c1 : c) {
                if (c1.getName().equalsIgnoreCase(cookieName)) {
                    value = c1.getValue();
                    break;
                }
            }
        }
        return value;
    }

    public Date parseStringToDate(String dateInString) {

        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {

            date = formatter1.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter2.format(date));

        } catch (ParseException e) {
            logger.error("HIBPER : parseStringToDate : " + e);
        }
        return date;
    }

    public String parseDatepicker(String dateInString) {

        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

        String result = null;
        try {

            Date date = formatter1.parse(dateInString);
            result = formatter2.format(date);

        } catch (ParseException e) {
            logger.error("HIBPER : parseDatepicker : " + e);
        }
        return result;

    }

    public void setHttpProxy(boolean isNeedProxy) {
        if (isNeedProxy) {
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", "");
            System.setProperty("http.proxyPort", "");
        } else {
            System.clearProperty("http.proxySet");
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
        }
    }

    public String getRutaFoto(String vCIP) {

        String rutaCompleta;

        if (vCIP.length() == 8) {
            String carpeta = vCIP.substring(7);
            String foto = vCIP + ".jpg";
            rutaCompleta = "https://aguila6.pnp.gob.pe/FotosTit/" + carpeta
                    + "/" + foto;
            logger.info("HIBPER : getRutaFoto : " + rutaCompleta);

        } else {
            String carpeta = vCIP.substring(5);
            String foto = vCIP + ".jpg";
            rutaCompleta = "https://aguila6.pnp.gob.pe/FotosTit/" + carpeta
                    + "/" + foto;
            logger.info("HIBPER : getRutaFoto : " + rutaCompleta);
        }
        return rutaCompleta;
    }

    // Este es el método calcularEdad que se manda a llamar en el main
    public static Integer calcularEdad(String fecha) {
        Date fechaNac = null;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (Exception ex) {
            logger.error("HIBPER : calcularEdad : " + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        // Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        // Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        // Se restan la fecha actual y la fecha de nacimiento
        int anio = fechaActual.get(Calendar.YEAR)
                - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH)
                - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)
                - fechaNacimiento.get(Calendar.DATE);
        // Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            anio--;
        }
        // Regresa la edad en base a la fecha de nacimiento
        return anio;
    }

    public static String fechaHora() {

        // Fecha actual en formato completo:
        // Tue Sep 23 01:18:48 CEST 2014
        Date fechaActual = new Date();

        // Formateando la fecha:
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Son las: " + formatoHora.format(fechaActual)
                + " de fecha: " + formatoFecha.format(fechaActual));

        String fechaHora = formatoFecha.format(fechaActual) + " -- "
                + formatoHora.format(fechaActual);

        return fechaHora;
    }
//
//    public static String SHA1(String text) {
//        MessageDigest md;
//        byte[] sha1hash = null;
//
//        try {
//            md = MessageDigest.getInstance("SHA-1");
//            // md.update(text.getBytes("iso-8859-1"), 0, text.length());
//            md.update(text.getBytes("utf-8"), 0, text.length());
//            sha1hash = md.digest();
//
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return Base64.encode(sha1hash);
//    }

    public static synchronized int diferenciasDeFechas(Date fechaInicial,
            Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);

        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String fechaFinalString = df.format(fechaFinal);

        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    public java.util.Date fechaMas(java.util.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    public static java.util.Date dateSqlToUtil(java.sql.Date sqlDate) {

        java.util.Date javaDate = null;

        if (sqlDate != null) {

            javaDate = new Date(sqlDate.getTime());

        }
        return javaDate;
    }

    public static java.sql.Date dateUtilToSql(java.util.Date sqlUtil) {

        java.sql.Date javaDate = null;

        if (sqlUtil != null) {

            javaDate = new java.sql.Date(sqlUtil.getTime());
        }
        return javaDate;
    }

}
