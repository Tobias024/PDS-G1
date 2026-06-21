package com.gudboy.exportacion;

import com.gudboy.animal.FichaMedica;

// Strategy: encapsula el algoritmo de exportacion de la ficha medica.
public interface IExportadorStrategy {
    void exportar(FichaMedica ficha);

    String getFormato();
}
