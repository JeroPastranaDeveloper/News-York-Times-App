# Developed by Jero Pastrana<br>
www.linkedin.com/in/jero-pastrana<br>
https://github.com/JeroPastranaDeveloper

This New York Times app demonstrates modern Android development with Jetpack Compose, KOIN, Coroutines, Flow, ROOM, and Material Design based on a hybrid architecture between MVI and MVVM.

For the application to load news, you need an API Key from the New York Times website (https://developer.nytimes.com), the API Key obtained has to be in the local.properties file.
Example:<br>
![image](https://github.com/user-attachments/assets/d7b3b1ab-b43e-4ce9-8fb8-93b40cd40632)

# Modularization
This application has been modularized thinking about:
- Resource optimization. 
- Each module can be executed in parallel to reduce compilation time.
- Each module has a unique function, thus respecting the single responsibility principle.

# Architecture:
![image](https://github.com/user-attachments/assets/48be2189-afb3-45d9-9985-9d1414cf57c0)

# App screenshots

![image](https://github.com/user-attachments/assets/44b5c5c4-668f-47aa-866f-914ebb79f7fa)  ![image](https://github.com/user-attachments/assets/89d4fd72-c596-4b49-a31d-e4e16125b5f8)
