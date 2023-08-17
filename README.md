# Tarea capítulo 03 - Validación y Seguridad - Bootcamp Spring - DevSuperior - bds04 :mortar_board:

Se debe desarrollar las funcionalidades necesarias para que las pruebas del proyecto sean correctas.

---
Se trata de un sistema de eventos y ciudades con una relación N-1 entre ellas.

## Diagrama de clases
<p align="center">
   <img width="80%" height="80%" src="https://github.com/niltonsjr/assets/blob/main/BDS_04/diagrama_de_clases.jpg?raw=true">
</p>

---
En este sistema, solamente las rotas de lectura (GET) y de eventos y ciudades son públicas (no necesitan login). Los usuarios con rol *CLIENT* también pueden añadir (POST) nuevos eventos. Los demás accesos son permitidos solamente a usuarios con rol *ADMIN*.

**Validaciones de clase CITY:**
- Nombre no puede ser vacío

**Validaciones de clase EVENT:**
- Nombre no puede ser vacío
- Fecha no puede ser pasada
- Ciudad no puede ser nula