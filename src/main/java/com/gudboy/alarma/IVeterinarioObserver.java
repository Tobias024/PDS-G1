package com.gudboy.alarma;

// Observer: los veterinarios se suscriben a las alarmas y son notificados al dispararse.
public interface IVeterinarioObserver {
    void notificar(Alarma alarma);
}
