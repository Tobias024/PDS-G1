package com.gudboy.exportacion;

import com.gudboy.animal.FichaMedica;
import com.gudboy.animal.RegistroAtencion;

public class ExportadorPDF implements IExportadorStrategy {

    @Override
    public void exportar(FichaMedica ficha) {
        System.out.println(construir(ficha));
    }

    @Override
    public String getFormato() {
        return "PDF";
    }

    private String construir(FichaMedica ficha) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficha Medica [formato ").append(getFormato()).append("]\n");
        sb.append("Altura: ").append(ficha.getAltura()).append("\n");
        sb.append("Peso: ").append(ficha.getPeso()).append("\n");
        sb.append("Edad: ").append(ficha.getEdad()).append("\n");
        sb.append("Condicion medica: ").append(ficha.getCondicionMedica()).append("\n");
        sb.append("Registros de atencion: ").append(ficha.getRegistros().size());
        for (RegistroAtencion registro : ficha.getRegistros()) {
            sb.append("\n - ").append(registro.getFecha());
        }
        return sb.toString();
    }
}
