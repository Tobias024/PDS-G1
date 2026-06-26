package com.gudboy.notificacion;

public abstract class NotificadorDecorator implements INotificador {

    protected final INotificador wrapped;

    protected NotificadorDecorator(INotificador wrapped) {
        this.wrapped = wrapped;
    }
}
