# 🚌 EduBus

> **Transformando los trayectos en transporte público en una experiencia educativa, entretenida y personalizada.**

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)

## 📄 Descripción del Proyecto
**EduBus** es una aplicación móvil nativa desarrollada como Proyecto Final de Ciclo (2º DAM). Su objetivo es aprovechar los tiempos muertos en el transporte público ofreciendo contenido de **m-learning** (micro-aprendizaje) y entretenimiento adaptado a la duración exacta del viaje.

A diferencia de otras apps, EduBus sigue una filosofía **"Offline First"**, permitiendo su uso en zonas sin cobertura gracias a una base de datos local sincronizada y la precarga de rutas mediante archivos JSON.

## 🚀 Funcionalidades Principales (MVP)
* **📍 Cálculo de Rutas:** Algoritmo que calcula el tiempo disponible de juego basado en paradas reales (Implementado: Línea 18 de Almería).
* **🎮 Gamificación:** Sistema de puntos y recompensas diseñado para motivar el aprendizaje continuo.
* **📶 Modo Offline:** Persistencia de datos local para garantizar el funcionamiento en trayectos con baja cobertura.
* **🧩 Arquitectura Híbrida:** Interfaz moderna construida con **Jetpack Compose** manteniendo compatibilidad con módulos de juegos en XML.
* **🤖 Contenido Adaptativo:** Selección inteligente de actividades (Trivias, Lecturas, Juegos) en función del tiempo estimado de llegada.

## 🛠️ Stack Tecnológico
El proyecto utiliza tecnologías modernas de desarrollo Android:

* **Lenguaje:** Kotlin.
* **UI:** Jetpack Compose (Material Design 3).
* **Arquitectura:** MVVM (Model-View-ViewModel).
* **Datos Locales:** JSON Assets & Room/SQLite.
* **Backend:** Firebase (Auth & Firestore).

## 🔧 Instalación y Pruebas
1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/ualamc158/edubus.git](https://github.com/ualamc158/edubus.git)
