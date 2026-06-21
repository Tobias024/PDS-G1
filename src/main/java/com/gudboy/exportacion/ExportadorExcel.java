package com.gudboy.exportacion;

import com.gudboy.animal.FichaMedica;

public class ExportadorExcel implements IExportadorStrategy {

    @Override
    public void exportar(FichaMedica ficha) {
        System.out.println(construir(ficha));
    }

    @Override
    public String getFormato() {
        return "EXCEL";
    }

    private String construir(FichaMedica ficha) {
        return String.join(";",
                "formato=" + getFormato(),
                "altura=" + ficha.getAltura(),
                "peso=" + ficha.getPeso(),
                "edad=" + ficha.getEdad(),
                "condicion=" + ficha.getCondicionMedica(),
                "registros=" + ficha.getRegistros().size());
    }
}
