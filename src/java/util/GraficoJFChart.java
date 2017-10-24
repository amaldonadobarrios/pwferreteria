/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import model.dto.ComprobanteCompra;
import model.dto.ComprobanteVenta;
import model.dto.Ganancia;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author 31424836
 */
public class GraficoJFChart {

    private GraficoJFChart() {
    }

    public static GraficoJFChart getInstance() {
        return GraficoJFChartHolder.INSTANCE;
    }

    private static class GraficoJFChartHolder {

        private static final GraficoJFChart INSTANCE = new GraficoJFChart();
    }

    public String B64graficoLineaxmesxyear(List<ComprobanteVenta> lista) throws IOException, NoSuchFieldException {
        String b64 = null;
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        if (lista != null) {
            if (lista.size() > 0) {
                for (ComprobanteVenta list : lista) {
                    line_chart_dataset.addValue(list.getTotal(), "VENTAS", list.getEstado());
                }
            }
        }
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("TOTAL DE VENTAS(S/.)",
                "Mes", "VENTAS", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);
        byte[] img = ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500));
        Base64.Encoder code = Base64.getEncoder();
        b64 = code.encodeToString(img);
        return b64;
    }
     public String B64graficoLineaxmesxyearCompra(List<ComprobanteCompra> lista) throws IOException, NoSuchFieldException {
        String b64 = null;
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        if (lista != null) {
            if (lista.size() > 0) {
                for (ComprobanteCompra list : lista) {
                    line_chart_dataset.addValue(list.getTotal(), "COMPRAS", list.getEstado());
                }
            }
        }
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("TOTAL DE COMPRAS(S/.)",
                "Mes", "COMPRAS", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);
        byte[] img = ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500));
        Base64.Encoder code = Base64.getEncoder();
        b64 = code.encodeToString(img);
        return b64;
    }
     
     public String B64graficoLineaxmesxyearGanancia(List<Ganancia> lista) throws IOException, NoSuchFieldException {
        String b64 = null;
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        if (lista != null) {
            if (lista.size() > 0) {
                for (Ganancia list : lista) {
                    line_chart_dataset.addValue(list.getGanancia(), "GANANCIA", list.getFecha());
                }
            }
        }
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("TOTAL DE GANANCIA(S/.)",
                "Mes", "GANANCIA", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);
        byte[] img = ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500));
        Base64.Encoder code = Base64.getEncoder();
        b64 = code.encodeToString(img);
        return b64;
    }
       public String B64graficoLineaxyearGanancia(List<Ganancia> lista) throws IOException, NoSuchFieldException {
        String b64 = null;
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        if (lista != null) {
            if (lista.size() > 0) {
                for (Ganancia list : lista) {
                    line_chart_dataset.addValue(list.getGanancia(), "GANANCIA", list.getFecha());
                }
            }
        }
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("TOTAL DE GANANCIA(S/.)",
                "AÑO", "GANANCIA", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);
        byte[] img = ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500));
        Base64.Encoder code = Base64.getEncoder();
        b64 = code.encodeToString(img);
        return b64;
    }
        public String B64graficoBarraxyearGanancia(List<Ganancia> lista) throws IOException, NoSuchFieldException {
        String b64 = null;
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (lista != null) {
            if (lista.size() > 0) {
                for (Ganancia list : lista) {
                    dataset.setValue(list.getGanancia(), "GANANCIA", list.getFecha());
                }
            }
        }
        // Creando el Grafico
         JFreeChart chart = ChartFactory.createBarChart3D("Ganancias Anuales","Año", "Ganancia (S/)",
        dataset, PlotOrientation.VERTICAL, true,true, false);
        byte[] img = ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500));
        Base64.Encoder code = Base64.getEncoder();
        b64 = code.encodeToString(img);
        return b64;
    }
         public String B64graficoBarraxmesxyearGanancia(List<Ganancia> lista) throws IOException, NoSuchFieldException {
        String b64 = null;
        // Fuente de Datos
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (lista != null) {
            if (lista.size() > 0) {
                for (Ganancia list : lista) {
                    dataset.setValue(list.getGanancia(), "GANANCIA", list.getFecha());
                }
            }
        }
        // Creando el Grafico
         JFreeChart chart = ChartFactory.createBarChart3D("Ganancias Mensuales","Mes", "Ganancia (S/)",
        dataset, PlotOrientation.VERTICAL, true,true, false);
        byte[] img = ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500));
        Base64.Encoder code = Base64.getEncoder();
        b64 = code.encodeToString(img);
        return b64;
    }

}
