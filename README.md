# Prog3Proyecto

## Proyecto de programación III por Lander Gallastegi y Andoni Torres.

### Idea general del proyecto:

El proyecto consiste en un juego de puzles en tres dimensiones y con cámara en primera persona con tres pruebas diferentes (si da tiempo meteremos más pruebas). El jugador podrá moverse libremente por varios escenarios tridimensionales y por diversos espacios no euclidianos mientras interactúa con ciertos objetos del entorno para solucionar los puzles que se plantean.

El jugador puede agarrar objetos, soltarlos, y los objetos interactúan entre ellos según como sean posicionados. 

El primer puzzle es una biblioteca colosal de la que el jugador tiene que escapar, para ello tendrá que fijarse en la alfombra, hay tres símbolos ahí, un círculo un cuadrado y un triángulo, hay que encontrar tres libros, cada uno con uno de los símbolos del suelo, cuando los pongas en sus respectivos sitios en la alfombra se abrirá la puerta para continuar. La biblioteca será generada aleatoriamente así que en cada partida los libros y las estanterías estarán en lugares diferentes añadiendo un factor de rejugabilidad al juego.
La aleatoriedad tendría 3 condiciones: Los libros estarán lejos de la entrada del laberinto, los libros estarán lejos entre ellos y las estanterías no pueden bloquear el acceso a los libros.

El segundo puzzle es la habitación del triple fuego que estará muy oscura y consistirá en tres pequeños puzzles que hay que completar para abrir una puerta. La sala consistirá en dos paneles 3x3 con fuegos, una puerta con rombos sin iluminar, cada vez que superemos un puzzle se iluminará un rombo, en los paneles podrás interactuar con los fuegos para apagarlos y encenderlos. En uno de los paneles veremos que los fuegos están encendidos y apagados de forma aleatoria, tendremos que encontrar un cartel en el que veamos una configuración de fuegos específica, tendremos que replicarla en el panel encendiendo y apagando los fuegos hasta que coincidan con los del cartel. En cada partida tanto el cartel como los fuegos tendrán una configuración aleatoria pero nunca coincidirán. Para el siguiente puzzle habrá que jugar al tres en raya con los fuegos, tú encenderás el primero y luego una IA encenderá otro fuego de otro color. La IA será imposible de vencer de manera convencional y tendrás que hacer trampas y apagar sus fuegos para ganar. El último rombo seguirá apagado y para encenderlo no tendremos que superar otro puzzle, tendremos que encontrarlo tirado en algún lugar de la habitación.

El tercer puzzle será un espacio amplio con un vacío a un lado, el espacio amplio tendrá varias baterías de tres tamaños diferentes dispersadas por el suelo y habrá una plataforma que sobresale con un agujero para meter las baterías. En el vacío habrá una plataforma flotante que saldrá a una distancia diferente cada partida. Tienes que ir metiendo las baterías a la plataforma del espacio amplio y luego darle a un botón para propulsada e intentar que caiga en la plataforma flotante. Cuando hayas calculado cuántas baterías necesitas tendrás que subirte encima y propulsarla para llegar a la plataforma flotante y terminar el juego. Si caes al vacío tendrás que empezar el juego desde el principio así que deberás asegurarte de calcular bien las baterías que uses, el número de estas será limitado y si te quedas sin ellas le tendrás que dar a un botón de reset que te dará más baterías pero cambiará la posición de la plataforma flotante así que tendrás que calcular de cero las baterías necesarias para que tu plataforma llegue a la flotante.

El tiempo que ha tardado cada usuario en pasarse cada puzzle y el tiempo total de juego se almacenará en una base de datos que podrá ser consultada en el menú del juego en forma de tabla.

![vs](https://user-images.githubusercontent.com/71408357/139401839-34f0cc98-3c2e-4fe0-89b2-7acb60a3989e.PNG)

![eg](https://user-images.githubusercontent.com/71408357/139401965-0f8016dc-f637-4f18-aa21-2c4e86ab9d93.PNG)


