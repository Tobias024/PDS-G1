package com.gudboy.exportacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.gudboy.animal.FichaMedica;

class ExportacionStrategyTest {

    private FichaMedica nuevaFicha() {
        return new FichaMedica(1L, 0.6, 12.5, 5, "Apto");
    }

    private String capturarExportacion(FichaMedica ficha) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            ficha.exportar();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }

    @Test
    void exportaEnFormatoPdf() {
        FichaMedica ficha = nuevaFicha();
        ExportadorPDF pdf = new ExportadorPDF();
        ficha.setExportador(pdf);

        assertEquals("PDF", pdf.getFormato());
        assertTrue(capturarExportacion(ficha).contains("PDF"));
    }

    @Test
    void cambiarLaEstrategiaPermiteExportarEnExcelSinTocarLaFicha() {
        FichaMedica ficha = nuevaFicha();

        ficha.setExportador(new ExportadorPDF());
        assertTrue(capturarExportacion(ficha).contains("PDF"));

        ficha.setExportador(new ExportadorExcel());
        assertTrue(capturarExportacion(ficha).contains("EXCEL"));
    }

    @Test
    void exportarSinEstrategiaConfiguradaFalla() {
        FichaMedica ficha = nuevaFicha();

        assertThrows(IllegalStateException.class, ficha::exportar);
    }
}
