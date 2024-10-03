se prueba que el codigo .yaml este correcto haciendo uso de la pagina
https://www.yamllint.com
![image](https://github.com/user-attachments/assets/1de5fcc7-5c2e-45cd-a2fe-1b3cb9211390)


¿se puede lograr que se ejecute sin necesidad de compilar el proyecto?

No, porque el proceso de `verify` en Maven requiere primero la compilación del código.

¿Donde puedes ver el mensaje de error de la aplicación o logs?

Si la aplicación no funciona correctamente, puedes revisar los logs en el servicio de App Service.

Ve a tu App Service en el portal de Azure.
En el menú de la izquierda, selecciona Diagnóstico y resolución de problemas o Logs de diagnóstico.
Habilita los logs para ver el historial de errores.