# Assignment 1 - Online Chat

## Run Commands

Avviare per prima cosa il server eseguendo

```
mvn clean compile exec:java@TCPServer 
```

e dopodichè avviare uno o più client su altri terminali eseguendo

```
mvn clean compile exec:java@TCPClient
```

Alternativamente è possibile generare i jar eseguendo

```
mvn clean package
```

successivamente è possibile trovare i jar all'interno della cartella target

## Tasks: 

- [ ] far funzionare messaggistica in LAN  
- [x] al client deve esser chiesto il nome  
- [x] il server si deve tenere i nomi di tutti i client connessi (getClientName method della Connessione)  
- [x] il server salva in un log tutto cio' che succede
- [x] implementare broadcasting system  
- [x] connessione e disconnessione del client devono essere comunicati
- [ ] documentazione (da vedere divisione)  
- [ ] GUI 
- [ ] emoticon
