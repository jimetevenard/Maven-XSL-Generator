# Maven-XSL-Generator

Ce plugin Maven permet de lancer des compilations [Generate XSL](https://jimetevenard.github.io/generate-xsl/).

## Utilisation

**Goal** : *generate-xsl*

**Configuration** :
* scenariXmlFilePath :  
   Fichier des scenari de compilation pour Generate-XSL  
   Cf. [Documentation sur ce fichier](https://jimetevenard.github.io/generate-xsl/#fichier-scenari)
* licencedSaxon :  
  *Optionnel* : true si Saxon EE/PE doit être utilisé
* intermediateXslDirPath :  
  *Optionnel* : Sauveguarder, pour débuguer, les XSL intermédiaires.  
  Cf. [Documentation sur le fonctionnement de l'implémentation XSLT de Generate XSL](https://jimetevenard.github.io/generate-xsl/#implem)

## Exemple

```xml
<plugin>
  <groupId>com.jimetevenard.xml</groupId>
  <artifactId>maven-xsl-generator</artifactId>
  <version>1.0.0</version>
  <configuration>
    <scenariXmlFilePath>your/scenari.xml</scenariXmlFilePath>
    <licencedSaxon>false</licencedSaxon>
    <intermediateXslDirPath>some/directory</intermediateXslDirPath>
  </configuration>
  <executions>
    <execution>
      <phase>generate-resources</phase>
      <goals>
        <goal>generate-xsl</goal>
      </goals>
      </execution>
  </executions>
  <dependencies>
    <!-- Saxon 9.x dependency -->
  </dependencies>
</plugin>
```
