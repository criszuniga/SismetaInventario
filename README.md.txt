# Sistema de Control de Inventario

**Estudiante:** Cristopher Zuñiga Araya
**Curso:** Programación IV
**Profesor:** José Andrés Jiménez Zamora
**Universidad Internacional San Isidro Labrador**
**Proyecto de Reposición — Valor 5%**

## Descripción del sistema

Sistema de escritorio desarrollado en Java Swing que permite administrar un inventario básico de productos. Permite registrar, editar, eliminar, buscar, filtrar por categoría, ordenar y exportar productos a un archivo CSV. La información se almacena en memoria utilizando Collections (List, Set, Map y Stack), y el sistema aplica programación por capas, manejo de excepciones personalizadas y validaciones de datos.

## Requisitos para ejecutarlo

- Java JDK 17 o superior
- NetBeans IDE
- Sistema operativo Windows

## Componentes Swing utilizados

- JFrame (ventana principal)
- JMenuBar, JMenu, JMenuItem (menús Archivo, Herramientas, Ayuda)
- JToolBar (barra de herramientas con accesos rápidos)
- JTabbedPane (pestañas: Registro producto, Lista de productos, Estadísticas)
- JTextField (código, nombre, cantidad, precio, búsqueda)
- JComboBox (categoría, filtro por categoría)
- JCheckBox (disponible)
- JRadioButton + ButtonGroup (tipo de producto)
- JTextArea (descripción, estadísticas)
- JTable (lista de productos)
- JButton (guardar, editar, eliminar, ordenar, exportar, buscar, filtrar)
- JOptionPane (mensajes, confirmaciones y errores)
- JFileChooser (exportación de archivos)
- JScrollPane (contenedores con scroll)

## Collections utilizadas

- **List\<Producto\>**: almacena todos los productos registrados en el sistema (en `ProductoRepositorio`).
- **Set\<String\>**: controla los códigos de producto ya registrados, para evitar duplicados (en `ProductoRepositorio`).
- **Map\<String, Integer\>**: contabiliza cuántos productos hay por cada categoría (en `ProductoNegocio`).
- **Stack\<String\>**: guarda el historial de acciones realizadas (registrar, editar, eliminar), en orden LIFO (en `ProductoNegocio`).

## Excepciones personalizadas

- **DatoInvalidoException**: se lanza cuando los datos ingresados no cumplen las validaciones (código vacío, nombre muy corto, precio o cantidad inválidos, categoría vacía, etc.).
- **ProductoDuplicadoException**: se lanza al intentar registrar un producto con un código que ya existe.
- **ArchivoException**: se lanza cuando ocurre un error al exportar el inventario a un archivo.

## Estructura del proyecto
src
├── modelo
│   └── Producto.java
├── repositorio
│   └── ProductoRepositorio.java
├── negocio
│   └── ProductoNegocio.java
├── excepciones
│   ├── DatoInvalidoException.java
│   ├── ProductoDuplicadoException.java
│   └── ArchivoException.java
├── presentacion
│   └── Mainframe.java
└── util
└── ArchivoUtil.java

## Instrucciones de uso

1. **Registrar un producto**: ir a la pestaña "Registro producto", llenar los campos y presionar "Guardar".
2. **Editar un producto**: en "Lista de productos", seleccionar una fila (los datos se cargan automáticamente en el formulario), modificar y presionar "Editar".
3. **Eliminar un producto**: seleccionar una fila y presionar "Eliminar"; el sistema pedirá confirmación.
4. **Buscar productos**: escribir un código o nombre en el buscador de "Lista de productos" y presionar "Buscar".
5. **Filtrar por categoría**: seleccionar una categoría en el combo de filtro y presionar "Filtrar".
6. **Ordenar productos**: presionar "Ordenar" y elegir el criterio (nombre, precio o cantidad).
7. **Exportar inventario**: presionar "Exportar", elegir ubicación y guardar en formato CSV.
8. **Ver estadísticas**: ir a la pestaña "Estadísticas".
9. **Ver historial de acciones**: menú Herramientas → "Ver historial".

## Capturas de la aplicación

_(Ver carpeta `documentacion/`)_