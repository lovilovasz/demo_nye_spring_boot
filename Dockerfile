# 1. Alap image kiválasztása
# Használjunk egy Java futtatókörnyezetet az alkalmazás futtatásához
FROM openjdk:23-jdk

# 2. Egy környezeti változó beállítása, opcionális
ENV SPRING_PROFILES_ACTIVE=prod

# 3. Munka directory beállítása a konténeren belül
WORKDIR /app

# 4. Adjuk hozzá az alkalmazás .jar fájlját
# Másoljuk fel a jar fájlt a konténerünkbe
COPY "target/demo_nye_spring_boot-0.0.1-SNAPSHOT.jar" .

# 5. Futtatási parancs megadása a konténer indításakor
CMD ["java", "-jar", "demo_nye_spring_boot-0.0.1-SNAPSHOT.jar"]
