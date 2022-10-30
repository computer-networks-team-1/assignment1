# Assignment 1 - Online Chat

## Run Commands

Avviare per prima cosa il server eseguendo

```
mvn clean compile exec:java@TCPServer 
```

e dopodichè avviare uno o più client su altri terminali eseguendo

```
mvn clean compile exec:java@TCPClientGUI
```

Alternativamente è possibile generare i jar eseguendo

```
mvn clean package
```

successivamente è possibile trovare i jar all'interno della cartella target.
E' anche possibile eseguire i main in modo solito dal proprio IDE.

Se ci dovessero essere dei problemi con gli ultimi due casi, si consiglia di eseguirli con i comandi maven iniziali, siccome è garantita l'inclusione di tutte le dipendenze e librerie in maniera corretta. 

## Generating Javadoc

Per generare la documentazione del progetto è necessario eseguire

```
mvn clean javadoc:javadoc
```

La documentazione completa sarà poi disponibile in target/site/apidocs/index.html
