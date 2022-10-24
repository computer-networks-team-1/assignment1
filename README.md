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

successivamente è possibile trovare i jar all'interno della cartella target

## Tasks: 

- [ ] far funzionare messaggistica in LAN  
- [x] al client deve esser chiesto il nome  
- [x] il server si deve tenere i nomi di tutti i client connessi (getClientName method della Connessione)  
- [x] il server salva in un log tutto cio' che succede
- [x] implementare broadcasting system  
- [x] connessione e disconnessione del client devono essere comunicati
- [ ] far chiedere insieme al nome anche l'indirizzo ip al quale connettersi (forse anche la porta?)
- [ ] mostrare schermata di errore in caso di non riuscita alla connessione
- [ ] fixare che /quit fa realmente chiudere il processo
- [ ] documentazione (da vedere divisione)  
- [x] GUI 
- [ ] documentare un minimo il codice
- [ ] emoticon

## Report:

1. *Scrivere i requisiti di ingegneria del software per il sistema, includendo ogni
diagramma necessario, oppure in alternativa un codice sorgente ben scritto
(struttura chiara, nomi di identificatori significativi, ecc,) e correttamente
commentato (struttura generale, scopo delle classi e dei metodi, eventuale
concorrenza delle classi e dei metodi, o thread).
*

2. *Descrivere la progettazione dell’algoritmo, mostrando quali interazioni avvengono tra i client e il server.*

3. *Discutere brevemente una possibile implementazione alternativa che sfrutta
il protocollo UDP, facendo un confronto critico tra le due.*
