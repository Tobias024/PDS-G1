# Puppies - Refugio Gud Boy

Implementacion en Java de la capa de dominio del sistema de seguimiento y control de
animales del refugio Gud Boy (Trabajo Practico de Programacion con Objetos / Analisis y
Diseno Orientado a Objetos).

El alcance es unicamente el back-end de dominio: no se desarrolla interfaz de usuario.
El correcto funcionamiento de los requerimientos principales se demuestra mediante tests
unitarios con JUnit 5.

## Requerimientos cubiertos

- Ficha tecnica del animal (tipo domestico/salvaje, altura, peso, edad, condicion medica).
- Animales salvajes y animales en tratamiento no pueden ser adoptados.
- Exportacion de la ficha medica a distintos formatos (PDF y Excel) de forma extensible.
- Alarmas configurables por animal con periodicidad y grupo de acciones.
- Notificacion (push) a los veterinarios y atencion de la alarma con registro en la ficha.
- Seguimiento de adopciones con visitas, encuesta y recordatorios por SMS / WhatsApp / Email.
- Maximo de 2 adopciones por cliente.
- Integracion con un modulo externo de autenticacion no modificable.

## Patrones de diseno aplicados

- **State** (`animal.estado`): el estado del animal (`Sano`, `EnTratamiento`, `Recuperado`)
  resuelve si es adoptable y encapsula las transiciones (`iniciarTratamiento()`,
  `finalizarTratamiento()`): es cada estado quien decide a que estado pasar, evitando
  condicionales por estado en `Animal`.
- **Strategy** (`exportacion`): `IExportadorStrategy` encapsula el algoritmo de exportacion.
  Agregar un formato nuevo solo requiere una clase nueva, sin modificar `FichaMedica`.
- **Strategy** (`notificacion`): `INotificador` permite cambiar el medio de envio del
  recordatorio (SMS, WhatsApp, Email) en tiempo de ejecucion segun la preferencia del cliente.
- **Strategy** (`alarma`): `IPeriodicidad` (`PeriodicidadDiaria`, `PeriodicidadSemanal`,
  `PeriodicidadMensual`) encapsula el calculo de la proxima fecha de disparo; agregar una
  frecuencia nueva no modifica `Alarma`.
- **Observer** (`alarma`): `Alarma` es el sujeto y los `Veterinario` son los observadores.
  Al dispararse, la alarma notifica a todos los veterinarios suscriptos.
- **Adapter** (`autenticacion`): `AdaptadorAutenticacion` adapta el `AuthServiceExterno`
  (no modificable) a la interfaz `IAutenticacion` que espera el sistema.
- **Template Method** (`alarma`): `AccionAlarma.realizar(comentario)` es el metodo plantilla
  que define el algoritmo fijo: invocar el hook abstracto `ejecutar()` y luego marcar la
  accion como `completada()`. Las subclases (`Vacunar`, `ControlParasitos`, etc.) solo
  sobreescriben `ejecutar()`; el orden y la logica de cierre son invariantes y viven en la
  clase abstracta, eliminando la responsabilidad del llamador de conocer esos pasos.
- **Decorator** (`notificacion`): `NotificadorDecorator` envuelve cualquier `INotificador`
  y permite agregar comportamiento en capas sin modificar los notificadores existentes.
  `NotificadorConLog` registra cada envio, `NotificadorConPrefijo` antepone un texto al
  mensaje y `NotificadorConReintento` reintenta el envio ante fallos. Los decoradores son
  apilables entre si y con cualquier canal concreto (SMS, WhatsApp, Email).
- **Polimorfismo / GRASP**: `esAdoptable()` es polimorfico (`AnimalSalvaje` siempre
  retorna false; `AnimalDomestico` delega en el estado). `FichaMedica` es Experto de sus
  registros y `Alarma` es Creator de sus acciones (composicion).

## Estructura

```
src/main/java/com/gudboy
  animal          Animal, AnimalDomestico, AnimalSalvaje, FichaMedica, RegistroAtencion
  animal/estado   EstadoAnimal (State) y sus implementaciones
  alarma          Alarma (Observer), AccionAlarma y acciones concretas
  usuario         Usuario, Veterinario, Visitador
  exportacion     IExportadorStrategy (Strategy) y exportadores PDF / Excel
  notificacion    INotificador (Strategy), notificadores SMS / WhatsApp / Email y decoradores
  adopcion        Cliente, Adopcion, Seguimiento, Visita, Encuesta, Cadencia
  autenticacion   IAutenticacion, AdaptadorAutenticacion (Adapter), AuthServiceExterno
```

## Compilar y ejecutar los tests

```
mvn test
```

Requiere JDK 21 y Maven 3.9 o superior.
