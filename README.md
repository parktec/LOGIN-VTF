# Tarea - Login con Base de Datos Local (Room)

Proyecto base: https://github.com/Enrique17/dummy_android.git

## Qué hace la app

- Pantalla de Login (usuario y contraseña), valida que no estén vacíos y que existan en la base de datos.
- Pantalla de Registro para crear un usuario nuevo.
- Todo se guarda en una base de datos local con Room (tabla `users`).
- Si el login es correcto, entra al Dashboard con un mensaje de bienvenida.

## Cambios que hice respecto al ejemplo

1. **Base de datos real con Room**: el ejemplo original tenía el login simulado (un usuario y contraseña quemados en el código con un delay). Le agregué Room de verdad: una entidad `UserEntity`, un `UserDao` con las consultas de login/registro, y la clase `AppDatabase`. El repositorio (`AuthRepositoryImpl`) ahora consulta la base en vez de comparar strings a mano.

2. **Pantalla de registro nueva**: en el repo base no existía forma de crear usuarios, solo el login. Agregué `RegisterScreen` para poder registrar un usuario nuevo antes de loguearse.

3. **Campo extra en el registro**: además de usuario y contraseña, pedí también el **nombre completo**, que se guarda en la tabla y después se muestra en el mensaje de bienvenida del dashboard ("¡Hola, [nombre]!").

4. **Rediseño de las pantallas**: cambié colores, íconos (usuario, candado, mostrar/ocultar contraseña) y el layout general del login (logo, tarjeta centrada, etc.), en vez de dejar los TextField simples del ejemplo.

5. **Algo extra en el dashboard**: agregué una lista simple de notas/pendientes donde se puede escribir, agregar y borrar (no se guarda en la base, es solo para mostrar algo funcional en esa pantalla).

## Cómo probarlo

1. Abrir el proyecto en Android Studio y sincronizar Gradle (necesita bajar las dependencias de Room).
2. Correr la app.
3. Como la base está vacía al principio, primero hay que registrar un usuario desde "¿No tienes cuenta? Regístrate".
4. Con ese usuario y contraseña, hacer login y debería mandar al dashboard.

## Capturas

(pendiente: agregar 2-3 capturas del login, registro y dashboard después de correr la app en el emulador)
