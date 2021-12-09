# Calculadora - Rest

_**Calculadora:** es una **Api Rest** en la cual podemos realizar las 4 operaciones básicas de aritmetica (Suma, Multiplicación, División y Resta)._

_Para hacer uso de los servicios expuestos en la Calculadora debera obtener un **Token**, el cual sera proporcionado al autenticarse exitosamente, para este ejercicio con el fin de simular un loguin podra hacerlo con cualquier Usuario y Contraseña._

## Comenzando 🚀

_Estas instrucciones te permitirán tener el proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

_Para ejecutar de manera exitosa la **Calculadora** en tu maquina local es necesario tener instalado **Java** o en su defecto un **JRE** y sus debidas variables de entorno configuradas: (Java y Gradle), si desconoces como hacerlo puedes consultar la documentación donde se explica paso a paso de como hacerlo_

* [Guia de configuración](https://www.java.com/es/download/help/path_es.html) - Variable de entorno Java.

* [Guia de configuración](https://docs.gradle.org/current/userguide/installation.html) - Variable de entorno Gradle.

_Para verificar si se instaló y configuró de manera correcta tanto Java como Gradle puedes abrir una consola de comandos y ejecutar los siguientes comandos._

```
java -version
gradle -v
```
## Ejecutando las pruebas ⚙️

_Explica como ejecutar las pruebas automatizadas para este sistema_

## Despliegue 📦

_Para tu mayor comodidad se anexa el 'ejecutable' de la aplicación en el repositorio, para ello puedes clonar o descargar de la rama Develop y en la carpeta ./calculatorRest/exe está adjunto el archivo: **calculadora-web-0.0.2.jar** el cual puedes desplegar ejecutando el siguiente comando._

```
java -jar calculadora-web-0.0.2.jar
```

## Construido con 🛠️

_La **Calculadora** se ha construido con_

* [SpringBoot](https://spring.io/projects/spring-boot) - El framework web usado
* [Gradle](https://gradle.org/) - Manejador de dependencias
* [JWT](https://jwt.io/) - Usado para generar Token de seguridad y poder consumir los endpoints que exponen las funcionalidades de la calculadora

## Probando la funcionalidad 🖇️

Para poder probar la funcionalidad es recomendable utilizar un cliente HTTP como **Postman**; por medio del cual debes loguearte en la aplicación para poder hacer uso de sus funcionalidades, para ello necesitas hacer una petición **HTTP POST** en [Loguin](http://localhost:8080/user/login)  y enviar los parametros: **user** y **password** con cualquier valor como se muestra en la imagen.

![Image Loguin](https://raw.githubusercontent.com/rigesco90/static/master/static/img/Loguin.png)

En la respuesta podemos observar que nos entrega un Token, el cual debemos usar para hacer las demas peticiones (Agregar Operando y realizar una Operación)

### Agregar operando

_Para agregar los operandos debes hacer una peticion **HTTP POST** [Add Operand](http://localhost:8080/calculator/sendOperand) con un **Header** Authorization: Token:_

```
Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIkFETUlOXCJ9XSIsInN1YiI6InJjYW1wbyIsImV4cCI6MTYzOTAyMDUxN30.PaHZVC_vDOk4QuHSd9N1m047CwdWq7tAl-RgNp1ftnmXEX2peZdupDxJ3X5yLJgFJG3PGfi8r3_jFX6JMNaPIQ
```

EJEMPLO:

![Image Token](https://raw.githubusercontent.com/rigesco90/static/master/static/img/token.png)

_Y un body con el atributo "operand" y su respectivo valor:_
```json
{
    "operand": 3
}
```
_Puedes enviar varios operandos para realizar la respectiva operación_

EJEMPLO:

![Image Add Operand](https://raw.githubusercontent.com/rigesco90/static/master/static/img/addOperand.png)

### Probando Operaciones

_SUMA:_

Se debe consumir el endpoint de las operaciones el cual es una petición **HTTP GET** [Operación](http://localhost:8080/calculator/operation?operation=Sum)

**Valores Aceptados como parametro:**

```
Sum
Multiply
Substraction
Division
```

**Authorization Operation**
![Image Add Operand](https://raw.githubusercontent.com/rigesco90/static/master/static/img/autorizacionSuma.png)

**Consumo Operation**
![Image Add Operand](https://raw.githubusercontent.com/rigesco90/static/master/static/img/Sum.png)

**Resultado Operation**
![Image Add Operand](https://raw.githubusercontent.com/rigesco90/static/master/static/img/ResultadoSuma.png)

**Excepción operación enviada no válida**
![Image Add Operand](https://raw.githubusercontent.com/rigesco90/static/master/static/img/ExceptioOperation.png)


## Conclusión ✒️
_La apliacación Calculadora Api Rest fue diseñada con una arquitectura totalmente escalable, diseñada con N capas, las cuales tienen su respectiva responsabilidad; exponer servicios rest, logica de negocio y seguridad, enfocada a ser un microservicio el cual tiene como objetivo ser solo una calculadora
## Autor ✒️

* **Rigo Esleider Campo Ordoñez** - *Trabajo Inicial* - [Rigo Campo](https://github.com/rigesco90)
