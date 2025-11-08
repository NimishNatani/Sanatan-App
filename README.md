🌼 Sanatan App

A cross-platform devotional application built using Kotlin Multiplatform and Compose Multiplatform, offering a unified spiritual experience across Android and iOS. The backend is powered by Spring Boot and MongoDB with a clean Controller–Service–Repository architecture.

🌟 Features
✅ Devotional Content Modules

Aarti

Darshan

Granth

Bhajans

Kathas

✅ Accessibility First

Large fonts

High contrast UI

Simple navigation

Tailored for elderly users

✅ App–Backend Integration

Secure REST API endpoints built using Spring Boot

Kotlin Multiplatform network layer consuming backend APIs

Optimized caching and smooth navigation across platforms

🏗️ Tech Stack
Frontend

Kotlin Multiplatform

Compose Multiplatform

Shared ViewModels

Coroutines + Flow

Backend

Spring Boot 3

REST API (Controller–Service–Repository arch)

MongoDB

Dependency Injection (Spring)

Tools

IntelliJ IDEA

Android Studio

Gradle KMP

Postman

📐 Architecture
Backend
Controller → Service → Repository → MongoDB

Frontend
Kotlin Multiplatform Shared Logic
↓
Compose Multiplatform UI for Android + iOS
↓
REST API integration with shared network layer

📸 Screenshots

(Add images when available)

📦 Repository

https://github.com/NimishNatani/Sanatan-App

🛠️ Setup Instructions
✅ Frontend

Clone the repository

Open in Android Studio or IntelliJ

Run the shared module and platform modules

✅ Backend

Configure application.properties with MongoDB URI

Run Spring Boot application

Backend will be available at http://localhost:8080/
