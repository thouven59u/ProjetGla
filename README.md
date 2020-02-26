# ProjetGLA

Gauthier BRIN - Quentin THOUVENOT - Julien BAILLY - Benoît HOLZER

## Prérequis : 
 - Glassfish Server 5 ou plus
 - NetBeans 8.2 ou plus
 - Java 8

---
## Création des ressources Glassfish

Rendez vous sur la console glassfish, par défaut http://localhost:4848 .

Créez une JDBC Connection Pools :
  Resources -> JDBC -> JDBC Connection Pools -> New
  Appelez la "GLAPool"
  Resource Type: javax.sql.DataSource
  
  Additional Properties :
    URL : jdbc.derby://localhost:1527/gla
    TraceFileAppend : false
    SecurityMechanism : 0
    User : root
    DatabaseName : gla
    Ssl : off
    RetrieveMessageText : true
    LoginTimeout : 0
    ServerName : localhost
    TraceLevel : -1
    PortNumber : 1527
    Password : root

---

Créez une JDBC Resources :
  Resources -> JDBC -> JDBC Resources -> New
  JNDI Name : jdbc/gla
  Pool Name : GLAPool
  Deployment Order : 100
  
---

Créez les JMS Destination Resources :
  Resources -> Resources -> JMS Resources -> Destination Resources
  
  Il faut en créer 4 différents :
  
    1)
    JNDI Name : jms/FacturationQueue
    Physical Destination Name : Facturation
    Resource Type : javax.jms.Queue
    
    2)
    JNDI Name : jms/OrderQueue
    Physical Destination Name : Order
    Resource Type : javax.jms.Queue
    
    3)
    JNDI Name : jms/RecepLivraison
    Physical Destination Name : RecepLivraison
    Resource Type : javax.jms.Queue
    
    4)
    JNDI Name : jms/RecepFacture
    Physical Destination Name : RecepFacture
    Resource Type : javax.jms.Queue

---

## Exécution du projet :

Importez les sources fournis dans NetBeans.

Clean&Build ProjetGLA-part2

Run ProjetGLA-part2

Clean&Build ProjetGLA

Run ProjetGLA

