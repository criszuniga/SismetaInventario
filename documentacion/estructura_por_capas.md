# Estructura por Capas — Sistema de Control de Inventario

Este proyecto está organizado siguiendo el patrón de programación por capas, separando responsabilidades en distintos paquetes.

## 1. Capa Modelo (`modelo`)
Contiene la clase `Producto`, que representa los datos de un producto del inventario (id, código, nombre, categoría, cantidad, precio, disponibilidad y descripción). Incluye encapsulamiento (atributos privados con getters/setters), constructor y el método `toString()`.

## 2. Capa Repositorio (`repositorio`)
La clase `ProductoRepositorio` administra el almacenamiento en memoria de los productos, usando una `List<Producto>` para guardarlos y un `Set<String>` para controlar códigos duplicados. Contiene los métodos básicos de acceso a datos: agregar, listar, buscar por código, editar, eliminar y verificar existencia de código.

## 3. Capa Negocio (`negocio`)
La clase `ProductoNegocio` contiene toda la lógica de validaciones (usando `DatoInvalidoException`), las reglas del sistema, los ordenamientos (usando `Comparator`), el cálculo de estadísticas, y el manejo del historial de acciones (`Stack<String>`) y el conteo de productos por categoría (`Map<String, Integer>`). Esta capa actúa como intermediaria entre la interfaz y el repositorio, aplicando todas las reglas de negocio antes de guardar o modificar datos.

## 4. Capa Excepciones (`excepciones`)
Contiene las excepciones personalizadas del sistema: `DatoInvalidoException` (datos inválidos), `ProductoDuplicadoException` (códigos repetidos) y `ArchivoException` (errores al exportar archivos). Estas excepciones permiten manejar errores de forma clara y específica en cada capa.

## 5. Capa Presentación (`presentacion`)
La clase `Mainframe` contiene toda la interfaz gráfica (Swing), incluyendo el JFrame principal, los menús, la barra de herramientas y las tres pestañas del sistema. Esta capa se limita a capturar datos del usuario, mostrar información, y llamar a los métodos correspondientes de la capa de negocio — no contiene validaciones ni accede directamente a las Collections.

## 6. Capa Util (`util`)
La clase `ArchivoUtil` contiene la lógica para exportar el inventario a un archivo CSV, usando `FileWriter` y manejando errores de escritura mediante `ArchivoException`.

## Flujo general de una operación (ejemplo: registrar un producto)

1. El usuario llena el formulario en `Mainframe` (presentación) y presiona "Guardar".
2. `Mainframe` crea un objeto `Producto` con los datos ingresados y llama a `productoNegocio.agregarProducto(producto)`.
3. `ProductoNegocio` valida los datos (capa negocio); si hay un error, lanza `DatoInvalidoException`.
4. Si los datos son válidos, `ProductoNegocio` llama a `repositorio.agregar(producto)`.
5. `ProductoRepositorio` verifica que el código no esté duplicado (usando el `Set`); si ya existe, lanza `ProductoDuplicadoException`. Si no, agrega el producto a la `List` y al `Set`.
6. Si todo sale bien, `Mainframe` muestra un mensaje de éxito y actualiza la tabla.