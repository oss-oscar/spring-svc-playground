Utilizo un fichero TOML para la organización de las dependencias del proyecto,

He creado un @UseCase para extender la semantica del inyector de depencendias de Spring, así además de las ya conocidas como @Repository @Service etc, sigo añadiendo nuevos tipos para poder tener más granularidad
tratando así de evitar usar el @Component que es algo más generico

Utilizo OpenFeign como client HTTP, que esta inspirado en Retrofit (https://github.com/OpenFeign/feign)

Estamos usando Swagger con este repo https://springdoc.org/