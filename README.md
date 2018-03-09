# microservice-validation

Proyecto motor de validacion:

1. Se envia el proyecto como proyecto de eclipse y maven}
2. lleva en la raiz el .jar para ejecutar en la JVM
3. Ejecutar en la raiz del proyecto: # java -Dlogback.configurationFile=./logback.xml -jar microservice-engine-1.0-SNAPSHOT.jar
4. En el navegador colocar http://localhost:8443/index.html
5. colocar la ruta de la CARPETA donde se encuentren todos los archivos XPDL
5. Seleccionar el boton validar, y debajo mostrara de cada archivo las validaciones realizadas en formato Array JSON
6. La clase XmlValidation.java se puede ingresar cualquier validation style o bpmn siguiiendo la estructura el metodo debe comenzar con Style o Bpmn.
